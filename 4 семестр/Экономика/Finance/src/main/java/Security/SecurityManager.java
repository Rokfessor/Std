package Security;

import Database.DBHandler;
import Exceptions.SecurityException;
import Subject.Subject;

import java.sql.SQLException;

public class SecurityManager {
    public static boolean login(String login, String password) throws SecurityException {
        try {
            DBHandler dbHandler = DBHandler.getInstance();
            Subject subject = dbHandler.getSubjectByLogin(login);
            boolean res = (subject != null && subject.getPassword().equals(password));
            if (res)
                CurrentSubject.setCurrentSubject(subject);
            return res;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SecurityException("Не удалось подключиться. Попробуйте позже");
        }
    }

    public static void editSubject(Subject subject) throws SecurityException {
        try {
            DBHandler dbHandler = DBHandler.getInstance();
            dbHandler.updateSubject(subject);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SecurityException("Не удалось выполнить операцию. Попробуйте позже");
        }
    }

    public static void registerNewSubject(Subject subject) throws SecurityException {
        try {
            DBHandler.getInstance().newSubject(subject);
        } catch (SQLException throwables) {
            if (throwables.getMessage().contains("SQLITE_CONSTRAINT_UNIQUE")) {
                throw new SecurityException("Данный логин уже используется\nПридумайте другой логин");
            }
            throwables.printStackTrace();
            throw new SecurityException("Не удалось выполнить операцию. Попробуйте позже");
        }
    }
}
