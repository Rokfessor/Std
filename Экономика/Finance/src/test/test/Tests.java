import Account.Account;
import Card.Card;
import Database.DBHandler;
import Subject.Subject;
import Transaction.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class Tests {
    @BeforeAll
    public static void DBConnectionTest(){
        Assertions.assertDoesNotThrow(DBHandler::getInstance);
    }

    @Test
    public void DBUpdateSubjectTest(){
        try {
            DBHandler dbHandler = DBHandler.getInstance();
            Subject subject = dbHandler.getSubjectById(1);
            Assertions.assertDoesNotThrow(() -> {
                System.err.println(dbHandler.updateSubject(subject));
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void DBUpdateAccountTest(){
        try {
            DBHandler dbHandler = DBHandler.getInstance();
            Account account = new Account();
            account.setOwnerId(dbHandler.getSubjectById(1).getId());
            account.setAmount(1000);
            account.setType(Account.DEBIT_TYPE);
            Assertions.assertDoesNotThrow(() -> {
                System.err.println(dbHandler.updateAccount(account));
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void DBWriteTransactionTest(){
        try {
            DBHandler dbHandler = DBHandler.getInstance();
            Assertions.assertDoesNotThrow(() -> {
                Transaction transaction = new Transaction();
                dbHandler.writeTransaction(transaction);
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void DBNewAccountTest(){
        try {
            DBHandler dbHandler = DBHandler.getInstance();
            Assertions.assertDoesNotThrow(() -> {
                Account account = new Account();
                account.setAmount(100);
                account.setType(Account.DEBIT_TYPE);
                account.setOwnerId(dbHandler.getSubjectById(1).getId());
                dbHandler.newAccount(account);
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void DBGetCardTest() throws SQLException {
        DBHandler dbHandler = DBHandler.getInstance();
        Assertions.assertDoesNotThrow(() -> {
            Card card = dbHandler.getCardById(1);
            System.err.println(card);
        });
    }

    @Test
    public void DBNewCardTest() throws SQLException {
        DBHandler dbHandler = DBHandler.getInstance();
        Assertions.assertDoesNotThrow(() -> {
            Card card = new Card();
            card.setAccount(8);
            card.setOwner(3);
            card.setAmount(135F);
            card.setType("DEBIT");
            dbHandler.newCard(card);
        });
    }

    @Test
    public void DBUpdateCardTest() throws SQLException {
        DBHandler dbHandler = DBHandler.getInstance();
        Assertions.assertDoesNotThrow(() -> {
            Card card = new Card();
            card.setAccount(8);
            card.setId(4);
            card.setOwner(3);
            card.setAmount(222F);
            card.setType("DEBIT");
            dbHandler.updateCard(card);
        });
    }

    @Test
    public void DBRemoveCardTest() throws SQLException {
        DBHandler dbHandler = DBHandler.getInstance();
        Assertions.assertDoesNotThrow(() -> {
            Card card = new Card();
            card.setAccount(8);
            card.setId(4);
            card.setOwner(3);
            card.setAmount(222F);
            card.setType("DEBIT");
            dbHandler.removeCard(card);
        });
    }

    @Test
    public void DBNewSubjectTest(){
        try {
            DBHandler dbHandler = DBHandler.getInstance();
            Assertions.assertDoesNotThrow(() -> {
                Subject subject = new Subject();
                subject.setName("test1");
                subject.setLogin("test1");
                subject.setPassword("test1");
                subject.setSecurityWord("test1");
                subject.setType(Subject.CLIENT_TYPE);
                dbHandler.newSubject(subject);
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void DBNewNotUniqueLoginSubjectTest(){
        try {
            DBHandler dbHandler = DBHandler.getInstance();
            Assertions.assertThrows(SQLException.class, () -> {
                Subject subject = new Subject();
                subject.setName("test1");
                subject.setLogin("DFyodor");
                subject.setPassword("test1");
                subject.setSecurityWord("test1");
                subject.setType(Subject.CLIENT_TYPE);
                dbHandler.newSubject(subject);
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void DBGetAccountsBySubjectIdTest(){
        try {
            DBHandler dbHandler = DBHandler.getInstance();
            Assertions.assertDoesNotThrow(() -> {
                System.err.println(dbHandler.getAccountsBySubjectId(1));
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void DBGetSubjectByLoginTest(){
        try {
            DBHandler dbHandler = DBHandler.getInstance();
            Assertions.assertDoesNotThrow(() -> {
                System.err.println(dbHandler.getSubjectByLogin("IPechkin"));
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void DBGetTransactionsTest(){
        try {
            DBHandler dbHandler = DBHandler.getInstance();
            Assertions.assertDoesNotThrow(() -> {
                System.err.println(dbHandler.getOutgoingTransactions(1));
            });
            Assertions.assertDoesNotThrow(() -> {
                System.err.println(dbHandler.getIncomingTransactions(1));
            });
            Assertions.assertDoesNotThrow(() -> {
                System.err.println(dbHandler.getAllTransactions(1));
            });


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void DBGetOrganisationsTest(){
        try {
            DBHandler dbHandler = DBHandler.getInstance();
            Assertions.assertDoesNotThrow(() -> {
                System.err.println(dbHandler.getPublicOrganisations());
            });
            Assertions.assertDoesNotThrow(() -> {
                System.err.println(dbHandler.getPrivateOrganisations());
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void DBGetSubjectTest(){
        try {
            DBHandler dbHandler = DBHandler.getInstance();
            Assertions.assertDoesNotThrow(() -> {
                System.err.println(dbHandler.getSubjectById(1));
            });
            Assertions.assertDoesNotThrow(() -> {
                System.err.println(dbHandler.getSubjectByLogin("DFyodor"));
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
