package Database;

import Account.Account;
import Card.Card;
import Subject.Subject;
import Transaction.Transaction;
import Utils.Utils;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBHandler {
    private static Connection connection;
    private static DBHandler instance;

    private DBHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Oladushek\\Desktop\\Finance\\src\\main\\resources\\MyBank.db");
    }

    public static DBHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DBHandler();
        return instance;
    }

    public void removeAccountById(long accountId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM " +
                "Accounts WHERE id = ?")) {
            statement.setLong(1, accountId);
            statement.executeUpdate();
        }
    }

    public void newSubject(Subject subject) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO " +
                "Subjects(type, name, login, password, securityWord) VALUES(?,?,?,?,?)")) {
            statement.setString(1, subject.getType());
            statement.setString(2, subject.getName());
            statement.setString(3, subject.getLogin());
            statement.setString(4, subject.getPassword());
            statement.setString(5, subject.getSecurityWord());

            statement.executeUpdate();
        }
    }

    public void newAccount(Account account) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO " +
                "Accounts(amount, owner, type) VALUES(?,?,?)")) {
            statement.setDouble(1, Utils.round(account.getAmount()));
            statement.setDouble(2, account.getOwnerId());
            statement.setString(3, account.getType());

            statement.executeUpdate();
        }
    }

    public int updateAccount(Account account) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE Accounts SET amount = ? WHERE id = ?")) {
            statement.setDouble(1, Utils.round(account.getAmount()));
            statement.setLong(2, account.getId());
            return statement.executeUpdate();
        }
    }

    public int updateSubject(Subject subject) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE Subjects SET" +
                " name = ?, password = ?, securityWord = ? WHERE id = ?")) {
            statement.setString(1, subject.getName());
            statement.setString(2, subject.getPassword());
            statement.setString(3, subject.getSecurityWord());
            statement.setLong(4, subject.getId());
            return statement.executeUpdate();
        }
    }

    public List<Subject> getPublicOrganisations() throws SQLException {
        return getSubject("WHERE type = 'PUBLIC_ORGANISATION'");
    }

    public List<Subject> getPrivateOrganisations() throws SQLException {
        return getSubject("WHERE type = 'PRIVATE_ORGANISATION'");
    }

    public List<Transaction> getOutgoingTransactions(long subjectId) throws SQLException {
        return getTransactions(" WHERE fromSubject = " + subjectId);
    }

    public List<Transaction> getIncomingTransactions(long subjectId) throws SQLException {
        return getTransactions(" WHERE toSubject = " + subjectId);
    }

    public List<Transaction> getAllTransactions(long subjectId) throws SQLException {
        return getTransactions(" WHERE fromSubject = " + subjectId + " OR toSubject = " + subjectId);
    }

    public void writeTransaction(Transaction transaction) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO " +
                "Transactions(amount, timestamp, fromAccount, toAccount, fromSubject, toSubject) VALUES(?,?,?,?,?,?)")) {
            statement.setDouble(1, Utils.round(transaction.getAmount()));
            statement.setLong(2, transaction.getTimestamp());
            statement.setLong(3, transaction.getFromAccount().getId());
            statement.setLong(4, transaction.getToAccount().getId());
            statement.setLong(5, transaction.getFromSubjectId());
            statement.setLong(6, transaction.getToSubjectId());

            statement.executeUpdate();
        }
    }

    private List<Transaction> getTransactions(String whereCondition) throws SQLException {
        List<Transaction> resList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Transactions " + whereCondition + " ORDER BY timestamp DESC");

            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getLong(1));
                transaction.setAmount(Utils.round(resultSet.getDouble(2)));
                transaction.setTimestamp(resultSet.getLong(3));
                transaction.setFromAccount(getAccountById(resultSet.getLong(4)));
                transaction.setToAccount(getAccountById(resultSet.getLong(5)));
                transaction.setFromSubjectId(resultSet.getLong(6));
                transaction.setToSubjectId(resultSet.getLong(7));
                resList.add(transaction);
            }

            return resList;
        }
    }

    public List<Account> getAccountsBySubjectId(long subjectId) throws SQLException {
        return getAccount("WHERE owner = " + subjectId);
    }

    public Account getAccountById(Long accountId) throws SQLException {
        List<Account> list = getAccount("WHERE id = " + accountId);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    private List<Account> getAccount(String whereCondition) throws SQLException {
        List<Account> resList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM Accounts " + whereCondition);
            while (result.next()) {
                Account account = new Account();
                account.setId(result.getLong(1));
                account.setAmount(Utils.round(result.getDouble(2)));
                account.setOwnerId(result.getLong(3));
                account.setType(result.getString(4));
                resList.add(account);
            }
            return resList;
        }
    }

    public List<Card> getCardByOwnerId(long id) throws SQLException {
        List<Card> list = getCard("WHERE OWNER = " + id);
        if (list.size() == 0)
            return null;
        return list;
    }

    public List<Card> getCardByAccount(long id) throws SQLException {
        List<Card> list = getCard("WHERE ACCOUNT = " + id);
        if (list.size() == 0)
            return null;
        return list;
    }

    public Card getCardById(long id) throws SQLException {
        List<Card> list = getCard("WHERE ID = " + id);
        if (list.size() == 0)
            return null;
        return list.get(0);
    }

    public void newCard(Card card) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO " +
                "Cards(owner, account, amount, type) VALUES(?,?,?,?)")) {
            statement.setLong(1, card.getOwner());
            statement.setLong(2, card.getAccount());
            statement.setDouble(3, Utils.round(card.getAmount()));
            statement.setString(4, card.getType());

            statement.executeUpdate();
        }
    }

    public int updateCard(Card card) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE Cards SET amount = ? WHERE id = ?")) {
            statement.setDouble(1, Utils.round(card.getAmount()));
            statement.setLong(2, card.getId());
            return statement.executeUpdate();
        }
    }

    public void removeCard(Card card) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM " +
                "Cards WHERE id = ?")) {
            statement.setLong(1, card.getId());
            statement.executeUpdate();
        }
    }

    private List<Card> getCard(String whereCondition) throws SQLException {
        List<Card> resultList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM Cards " + whereCondition);
            while (result.next()) {
                Card card = new Card();
                card.setId(result.getLong(1));
                card.setOwner(result.getLong(2));
                card.setAccount(result.getLong(3));
                card.setAmount(result.getLong(4));
                card.setType(result.getString(5));
                resultList.add(card);
            }
        }
        return resultList;
    }

    public Subject getSubjectByLogin(String login) throws SQLException {
        List<Subject> list = getSubject("WHERE login = '" + login + "'");
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public Subject getSubjectById(long id) throws SQLException {
        List<Subject> list = getSubject("WHERE id = " + id);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    private List<Subject> getSubject(String whereCondition) throws SQLException {
        List<Subject> resultList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM Subjects " + whereCondition);
            while (result.next()) {
                Subject subject = new Subject();
                subject.setId(result.getLong(1));
                subject.setType(result.getString(2));
                subject.setName(result.getString(3));
                subject.setLogin(result.getString(4));
                subject.setPassword(result.getString(5));
                subject.setSecurityWord(result.getString(6));
                subject.setAccountList(getAccountsBySubjectId(subject.getId()));
                resultList.add(subject);
            }
        }
        return resultList;
    }
}