package transaction;

import utils.TransactionException;
import utils.Utils;
import bankAccount.BankAccount;

import java.io.IOException;
import java.util.ArrayList;

public class TransactionManager {
    public static void makeTransaction(BankAccount from, BankAccount to, long payment) throws TransactionException {
        System.err.println(to);
        if (from == null) {
            Transaction ts = new Transaction();
            ts.toPersonalAccountId = to.personalAccountId;
            ts.payment = payment;
            ts.transactionId = TransactionsStorage.getNewTransactionId();
            TransactionsStorage.transactions.add(ts);

            try {
                to.personalAccount += payment;
                Utils.updateData();
            } catch (IOException e) {
                to.personalAccount -= payment;
                TransactionsStorage.transactions.remove(ts);
                throw new TransactionException("Не удалось записать транзакцию");
            }
        } else if (from.personalAccount >= payment) {
            //Собираем транзакцию
            Transaction ts = new Transaction();
            ts.fromPersonalAccountId = from.personalAccountId;
            ts.toPersonalAccountId = to.personalAccountId;
            ts.payment = payment;
            ts.transactionId = TransactionsStorage.getNewTransactionId();
            TransactionsStorage.transactions.add(ts);

            try {
                to.personalAccount += payment;
                from.personalAccount -= payment;
                Utils.updateData();
            } catch (IOException e) {
                to.personalAccount -= payment;
                from.personalAccount += payment;
                TransactionsStorage.transactions.remove(ts);
                throw new TransactionException("Не удалось записать транзакцию");
            }
        } else
            throw new TransactionException("Не хватает средств на счёте");
    }

    public static ArrayList<Transaction> getInTransactions(BankAccount account) {
        ArrayList<Transaction> list = new ArrayList<>();
        for (Transaction transaction : TransactionsStorage.transactions) {
            if (transaction.fromPersonalAccountId == account.personalAccountId)
                list.add(transaction);
        }
        return list;
    }

    public static ArrayList<Transaction> getOutTransactions(BankAccount account) {
        ArrayList<Transaction> list = new ArrayList<>();
        for (Transaction transaction : TransactionsStorage.transactions) {
            if (transaction.toPersonalAccountId == account.personalAccountId)
                list.add(transaction);
        }
        return list;
    }
}
