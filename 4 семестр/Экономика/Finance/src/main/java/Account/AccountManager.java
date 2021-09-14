package Account;

import Database.DBHandler;
import Exceptions.AccountException;
import Exceptions.SubjectException;
import Security.CurrentSubject;
import Security.SecurityManager;
import Subject.Subject;
import Subject.SubjectManager;

import java.sql.SQLException;
import java.util.List;

public class AccountManager {
    public static void createNewAccount(String type) throws AccountException {
        Account account = new Account();
        account.setType(type);
        account.setOwnerId(CurrentSubject.getCurrentSubject().getId());

        try {
            DBHandler.getInstance().newAccount(account);
            SubjectManager.updateCurrentSubject();
        } catch (SQLException throwables) {
            throw new AccountException("Не удалось выполнить операцию! Попробуйте позже.");
        }
    }

    public static List<Account> getAccountsBySubjectId(long id) throws AccountException {
        try {
            List<Account> list = DBHandler.getInstance().getAccountsBySubjectId(CurrentSubject.getCurrentSubject().getId());
            if (list.size() == 0)
                throw new AccountException("У данного пользователя нет счетов! ");

            return list;
        } catch (SQLException throwables) {
            throw new AccountException("Не удалось выполнить операцию. Попробуйте позже");
        }
    }

    public static Account getAccountById(long id) throws AccountException {
        try {
            Account account = DBHandler.getInstance().getAccountById(id);
            if (account == null)
                throw new AccountException("Счёта не существует! Номер счёта: " + id);
            return account;
        } catch (SQLException throwables) {
            throw new AccountException("Не удалось выполнить операцию! Попробуйте позже.");
        }
    }

    public static void removeAccountById(long id) throws AccountException {
        try {
            Account account = getAccountById(id);
            if (account.getAmount() != 0)
                throw new AccountException("Не удалось выполнить операцию! На счёте присутствует отстаток: " + account.getAmount());
            DBHandler.getInstance().removeAccountById(id);
        } catch (SQLException throwables) {
            throw new AccountException("Не удалось выполнить операцию! Попробуйте позже.");
        }
    }
}
