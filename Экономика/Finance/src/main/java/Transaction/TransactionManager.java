package Transaction;

import Account.Account;
import Database.DBHandler;
import Exceptions.TransactionException;
import Subject.*;

import java.sql.SQLException;
import java.util.List;

public class TransactionManager {
    public static void makeTransaction(long fromAccountId, long toAccountId, double amount) throws TransactionException {
        try {
            if (amount <= 0)
                throw new TransactionException("Сумма не может быть отрицательной! " + amount);

            Transaction transaction = new Transaction();
            DBHandler dbHandler = DBHandler.getInstance();

            Account fromAccount;
            if (fromAccountId == 0) {
                System.err.println(fromAccountId);
                fromAccount = new Account();
                fromAccount.setType("ATM");
                fromAccount.setId(0);
                fromAccount.setOwnerId(0);
                fromAccount.setAmount(amount);
            } else if (fromAccountId == -1){
                fromAccount = new Account();
                fromAccount.setType("Accumulation");
                System.err.println(fromAccount.getType());
                fromAccount.setId(-1);
                fromAccount.setOwnerId(-1);
                fromAccount.setAmount(amount);
            }
                else {
                fromAccount = dbHandler.getAccountById(fromAccountId);
            }

            Account toAccount = dbHandler.getAccountById(toAccountId);

            if (fromAccount == null)
                throw new TransactionException("Счёта не существует! \nНомер счёта: " + fromAccountId);
            if (toAccount == null)
                throw new TransactionException("Счёта не существует! \nНомер счёта: " + toAccountId);
            if (amount > fromAccount.getAmount())
                throw new TransactionException("Недостаточно средств на счёте! \nСумма на счёте: " + fromAccount.getAmount());

            fromAccount.setAmount(fromAccount.getAmount() - amount);
            toAccount.setAmount(toAccount.getAmount() + amount);

            transaction.setFromAccount(fromAccount);
            transaction.setToAccount(toAccount);
            transaction.setFromSubjectId(fromAccount.getOwnerId());
            transaction.setToSubjectId(toAccount.getOwnerId());
            transaction.setTimestamp(System.currentTimeMillis());
            transaction.setAmount(amount);
            if (fromAccountId != 0)
                dbHandler.updateAccount(fromAccount);
            dbHandler.updateAccount(toAccount);
            dbHandler.writeTransaction(transaction);
            SubjectManager.updateCurrentSubject();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new TransactionException("Не удалось выполнить операцию! Попробуйте позже.");
        }
    }

    public static List<Transaction> getIncomingTransactions(Subject subject) throws TransactionException {
        try {
            List<Transaction> list = DBHandler.getInstance().getIncomingTransactions(subject.getId());
            if (list.size() == 0)
                throw new TransactionException("У пользователя " + subject.getName() + " нет входящих транзакций!");
            return list;
        } catch (SQLException throwables) {
            throw new TransactionException("Не удалось выполнить операцию! Попробуйте позже.");
        }
    }

    public static List<Transaction> getOutgoingTransactions(Subject subject) throws TransactionException {
        try {
            List<Transaction> list = DBHandler.getInstance().getOutgoingTransactions(subject.getId());
            if (list.size() == 0)
                throw new TransactionException("У пользователя " + subject.getName() + " нет исходящих транзакций!");
            return list;
        } catch (SQLException throwables) {
            throw new TransactionException("Не удалось выполнить операцию! Попробуйте позже.");
        }
    }

    public static List<Transaction> getAllTransactions(Subject subject) throws TransactionException {
        try {
            List<Transaction> list = DBHandler.getInstance().getAllTransactions(subject.getId());
            if (list.size() == 0)
                throw new TransactionException("У пользователя " + subject.getName() + " нет транзакций!");
            return list;
        } catch (SQLException throwables) {
            throw new TransactionException("Не удалось выполнить операцию! Попробуйте позже.");
        }
    }
}
