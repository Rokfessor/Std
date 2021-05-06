package Subject;

import Account.Account;
import Database.DBHandler;
import Exceptions.SecurityException;
import Exceptions.SubjectException;
import Security.CurrentSubject;
import Security.SecurityManager;

import java.sql.SQLException;
import java.util.List;

public class SubjectManager {
    public static void updateCurrentSubject() {
        try {
            CurrentSubject.setCurrentSubject(getSubjectById(CurrentSubject.getCurrentSubject().getId()));
        } catch (SubjectException e) {
            e.printStackTrace();
        }
    }

    public static Subject getSubjectById(long id) throws SubjectException {
        try {
            Subject subject = DBHandler.getInstance().getSubjectById(id);
            if (subject == null)
                throw new SubjectException("Данного пользователя не существует");

            return subject;
        } catch (SQLException throwables) {
            throw new SubjectException("Не удалось выполнить операцию. Попробуйте позже");
        }
    }

    public static Subject getSubjectByLogin(String login) throws SubjectException {
        try {
            Subject subject = DBHandler.getInstance().getSubjectByLogin(login);
            if (subject == null)
                throw new SubjectException("Данного пользователя не существует");

            return subject;
        } catch (SQLException throwables) {
            throw new SubjectException("Не удалось выполнить операцию. Попробуйте позже");
        }
    }

    public static List<Subject> getPrivateOrganisations() throws SubjectException {
        try {
            List<Subject> privList = DBHandler.getInstance().getPrivateOrganisations();
            if (privList.size() == 0)
                throw new SubjectException("Не найдено частных организаций");

            return privList;
        } catch (SQLException throwables) {
            throw new SubjectException("Не удалось выполнить операцию. Попробуйте позже");
        }
    }

    public static List<Subject> getPublicOrganisations() throws SubjectException {
        try {
            List<Subject> list = DBHandler.getInstance().getPublicOrganisations();
            if (list.size() == 0)
                throw new SubjectException("Не найдено публичных организаций");

            return list;
        } catch (SQLException throwables) {
            throw new SubjectException("Не удалось выполнить операцию. Попробуйте позже");
        }
    }

}
