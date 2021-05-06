package GUI.AccountForm;

import Account.Account;
import Account.AccountManager;
import Exceptions.AccountException;
import Exceptions.SecurityException;
import Exceptions.SubjectException;
import Exceptions.TransactionException;
import GUI.Parent;
import Security.CurrentSubject;
import Security.SecurityManager;
import Subject.Subject;
import Subject.SubjectManager;
import Transaction.Transaction;
import Transaction.TransactionManager;
import Utils.Utils;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AccountForm extends JPanel {
    private JTabbedPane tabbedPane;
    private JPanel mainPanel;
    private JComboBox<Account> depositCB;
    private JTextField depositAmountTF;
    private JButton depositButton;
    private JComboBox<String> openCB;
    private JButton openButton;
    private JPanel historyPanel;
    private JPanel accountsManagePanel;
    private JPanel subjectManagePanel;
    private JScrollPane historyScrollPane;
    private JComboBox<Account> closeCB;
    private JComboBox<Account> transCB;
    private JTextField transAccIdTF;
    private JButton closeButton;
    private JButton transButton;
    private JTextField transAmountTF;
    private JComboBox<Account> transTypeCB;
    private JPanel testPanel;
    private JButton сменитьПарольButton;
    private JTextField pass1TF;
    private JTextField pass2TF;
    private JTextField pass3TF;
    private JComboBox<Subject> privateCB;
    private JComboBox<Subject> publicCB;
    private JComboBox<Account> payAccCB;
    private JTextField paySumTF;
    private JButton payTovariButton;
    private JButton payUslugiCB;
    private JComboBox<Account> accCheckCB;
    private JLabel amoLabel;
    private JButton updateButton;
    private JLabel amoOnAccLabel;
    private JComboBox<Account> creditAccCB;
    private JComboBox<Account> spisAcc;
    private JButton внестиДеньгиНаКредитныйButton;
    private JComboBox<Account> nakopCB;
    private JButton nakopButton;
    private JComboBox<Account> deposit_CB;
    private JComboBox<Account> toDeposit;
    private JPanel toPanel;
    private JPanel histNakopPanel;
    private JLabel onNakopLabel;
    private JLabel toNakoLabel;
    private JLabel toTextLabel;

    public AccountForm(Parent parent) {
        add(mainPanel);

        updateComboBoxes0();

        SwingUtilities.invokeLater(() -> toPanel.setVisible(false));

        openButton.addActionListener(e -> {
            String type = (String) openCB.getSelectedItem();
            System.err.println(type);
            switch (type) {
                case "Кредитный" -> type = Account.CREDIT_TYPE;
                case "Дебетовый" -> type = Account.DEBIT_TYPE;
                case "Накопительный" -> type = Account.ACCUMULATION_TYPE;
            }

            try {
                AccountManager.createNewAccount(type);
                JOptionPane.showMessageDialog(this, "Счёт успешно открыт");
                SubjectManager.updateCurrentSubject();
                updateComboBoxes0();
            } catch (AccountException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        closeButton.addActionListener(e -> {
            try {
                AccountManager.removeAccountById(((Account) closeCB.getSelectedItem()).getId());
                updateComboBoxes0();
                JOptionPane.showMessageDialog(this, "Счёт успешно закрыт!");
            } catch (AccountException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        depositButton.addActionListener(e -> {
            try {
                long accountId = ((Account) depositCB.getSelectedItem()).getId();
                double amount = Utils.round(Double.parseDouble(depositAmountTF.getText()));
                TransactionManager.makeTransaction(0, accountId, amount);
                JOptionPane.showMessageDialog(this, "Счёт успешно пополнен!");
            } catch (NumberFormatException | TransactionException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        transButton.addActionListener(e -> {
            try {
                long fromAccountId = ((Account) transCB.getSelectedItem()).getId();
                long toAccountId = Long.parseLong(transAccIdTF.getText());
                double amount = Utils.round(Long.parseLong(transAmountTF.getText()));
                TransactionManager.makeTransaction(fromAccountId, toAccountId, amount);
                JOptionPane.showMessageDialog(this, "Перевод успешно совершен!");
            } catch (NumberFormatException | TransactionException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        transTypeCB.addActionListener(e -> {
            String type = (String) transTypeCB.getSelectedItem();
            if (type != null)
                fillHistoryTable(type);
        });

        сменитьПарольButton.addActionListener(e -> {
            if (pass1TF.getText().compareTo(CurrentSubject.getCurrentSubject().getPassword()) == 0) {
                if (pass2TF.getText().compareTo(pass3TF.getText()) == 0) {
                    CurrentSubject.getCurrentSubject().setPassword(pass1TF.getText());
                    try {
                        SecurityManager.editSubject(CurrentSubject.getCurrentSubject());
                        SubjectManager.updateCurrentSubject();
                        JOptionPane.showMessageDialog(this, "Пароль успешно изменен!");
                    } catch (SecurityException securityException) {
                        securityException.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Новые пароли не совпадают!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Введен неправильный пароль", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        tabbedPane.addChangeListener(e -> {
            int ind = tabbedPane.getSelectedIndex();
            switch (ind) {
                case 0 -> updateComboBoxes0();
                case 1 -> updateComboBoxes1();
                case 4 -> updateComboBoxes4();
                case 5 -> updateComboBoxes5();
            }
        });

        accCheckCB.addActionListener(e -> {
            try {
                long accId = ((Account) accCheckCB.getSelectedItem()).getId();
                List<Account> accList = CurrentSubject.getCurrentSubject().getAccountList();
                for (int i = 0; i < accList.size(); i++) {
                    if (accList.get(i).getId() == accId) {
                        amoLabel.setText(String.valueOf(accList.get(i).getAmount()));
                        break;
                    }
                }
            } catch (NullPointerException ignored) {
            }
        });
        updateButton.addActionListener(accCheckCB.getActionListeners()[0]);

        payAccCB.addActionListener(e -> {
            long accId = ((Account) payAccCB.getSelectedItem()).getId();
            List<Account> accList = CurrentSubject.getCurrentSubject().getAccountList();
            for (int i = 0; i < accList.size(); i++) {
                if (accList.get(i).getId() == accId) {
                    amoOnAccLabel.setText("Сумма на счёте: " + accList.get(i).getAmount());
                    break;
                }
            }
        });

        payTovariButton.addActionListener(e -> {
            long fromAccId = ((Account) payAccCB.getSelectedItem()).getId();
            long toAccId = ((Subject) privateCB.getSelectedItem()).getAccountList().get(0).getId();
            try {
                double amo = Utils.round(Double.parseDouble(paySumTF.getText()));
                TransactionManager.makeTransaction(fromAccId, toAccId, amo);
                JOptionPane.showMessageDialog(this, "Оплата успешно произведена!");
                long accId = (Long) payAccCB.getSelectedItem();
                List<Account> accList = CurrentSubject.getCurrentSubject().getAccountList();
                for (int i = 0; i < accList.size(); i++) {
                    if (accList.get(i).getId() == accId) {
                        amoOnAccLabel.setText("Сумма на счёте: " + accList.get(i).getAmount());
                        break;
                    }
                }
                updateComboBoxes1();
            } catch (TransactionException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        payUslugiCB.addActionListener(e -> {
            long fromAccId = ((Account) payAccCB.getSelectedItem()).getId();
            long toAccId = ((Subject) publicCB.getSelectedItem()).getAccountList().get(0).getId();

            try {
                double amo = Utils.round(Double.parseDouble(paySumTF.getText()));
                TransactionManager.makeTransaction(fromAccId, toAccId, amo);
                JOptionPane.showMessageDialog(this, "Оплата успешно произведена!");
                updateComboBoxes1();
            } catch (TransactionException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        openCB.addActionListener(e -> {
            String type = (String) openCB.getSelectedItem();
            switch (type) {
                case "Кредитный" -> {
                    if (deposit_CB.getSelectedItem().equals("Счёт")) {
                        SwingUtilities.invokeLater(() -> {
                            toTextLabel.setText("Cчёт получения денег");
                            toPanel.setVisible(true);
                        });
                    }
                    deposit_CB.setEnabled(true);
                }
                case "Дебетовый" -> {
                    deposit_CB.setEnabled(true);
                    if (deposit_CB.getSelectedItem().equals("Карта")) {
                        SwingUtilities.invokeLater(() -> {
                                    toTextLabel.setText("Cчёт подключения");
                                    toPanel.setVisible(true);
                                }
                        );
                    }
                }
                case "Накопительный" -> SwingUtilities.invokeLater(() -> {
                    deposit_CB.setSelectedIndex(1);
                    deposit_CB.setEnabled(false);
                    toPanel.setVisible(false);
                });
            }
        });

        nakopButton.addActionListener(e -> {
            try {
                Account account = AccountManager.getAccountById(((Account) nakopCB.getSelectedItem()).getId());
                double amo = Utils.round(account.getAmount() * Math.pow(1 + 0.02, 1) - account.getAmount());
                TransactionManager.makeTransaction(-1, account.getId(), amo);
                SubjectManager.updateCurrentSubject();
                account = (Account) nakopCB.getSelectedItem();
                onNakopLabel.setText("Сумма на счёте : " + account.getAmount());
                toNakoLabel.setText("Сумма к начислению: " + Utils.round((account.getAmount() * Math.pow(1 + 0.02, 1)) - account.getAmount()));
            } catch (AccountException | TransactionException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        nakopCB.addActionListener(e -> {
            try {
                if (nakopCB.getSelectedItem() != null) {
                    Account account = AccountManager.getAccountById(((Account) nakopCB.getSelectedItem()).getId());
                    onNakopLabel.setText("Сумма на счёте : " + account.getAmount());
                    toNakoLabel.setText("Сумма к начислению: " + Utils.round((account.getAmount() * Math.pow(1 + 0.02, 1)) - account.getAmount()));
                }
            } catch (AccountException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

        });
    }

    private void updateComboBoxes0() {
        try {
            List<Account> list = AccountManager.getAccountsBySubjectId(CurrentSubject.getCurrentSubject().getId());
            SwingUtilities.invokeLater(() -> {
                depositCB.removeAllItems();
                transCB.removeAllItems();
                closeCB.removeAllItems();
                toDeposit.removeAllItems();
                accCheckCB.removeAllItems();
                for (Account account : list) {
                    depositCB.addItem(account);
                    transCB.addItem(account);
                    closeCB.addItem(account);
                    accCheckCB.addItem(account);
                    if (account.getType().compareTo(Account.CREDIT_TYPE) != 0) {
                        toDeposit.addItem(account);
                    }
                }
            });
        } catch (AccountException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void updateComboBoxes4() {
        System.err.println("!");
        try {
            List<Account> list = AccountManager.getAccountsBySubjectId(CurrentSubject.getCurrentSubject().getId());
            nakopCB.removeAllItems();

            for (Account a : list) {
                if (a.getType().compareTo(Account.ACCUMULATION_TYPE) == 0)
                    nakopCB.addItem(a);
            }
        } catch (AccountException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void updateComboBoxes5() {
        List<Account> list = CurrentSubject.getCurrentSubject().getAccountList();
        creditAccCB.removeAllItems();
        spisAcc.removeAllItems();
        for (Account a : list) {
            if (a.getType().contains(Account.CREDIT_TYPE)) {
                creditAccCB.addItem(a);
            }
            if (a.getType().contains(Account.DEBIT_TYPE)){
                spisAcc.addItem(a);
            }

        }
    }

    public void updateComboBoxes1() {
        try {
            List<Subject> privList = SubjectManager.getPrivateOrganisations();
            List<Subject> pubList = SubjectManager.getPublicOrganisations();
            List<Account> list = AccountManager.getAccountsBySubjectId(CurrentSubject.getCurrentSubject().getId());
            privateCB.removeAllItems();
            publicCB.removeAllItems();

            for (Subject s : privList) {
                privateCB.addItem(s);
            }

            for (Subject s : pubList) {
                publicCB.addItem(s);
            }

            for (Account a : list) {
                payAccCB.addItem(a);
            }
        } catch (SubjectException | AccountException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void fillHistoryTable(String type) {
        List<Transaction> list = new ArrayList<>();
        try {
            switch (type) {
                case "Входящие":
                    list = TransactionManager.getIncomingTransactions(CurrentSubject.getCurrentSubject());
                    break;
                case "Исходящие":
                    list = TransactionManager.getOutgoingTransactions(CurrentSubject.getCurrentSubject());
                    break;
                case "Все":
                    list = TransactionManager.getAllTransactions(CurrentSubject.getCurrentSubject());
                    break;
            }
        } catch (TransactionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        testPanel.removeAll();
        for (Transaction transaction : list) {
            try {
                JLabel label = new JLabel();
                String builder = "<html>" +
                        "<p>Имя отправителя: " + SubjectManager.getSubjectById(transaction.getFromSubjectId()).getName() +
                        "<p>Имя получателя: " + SubjectManager.getSubjectById(transaction.getToSubjectId()).getName() +
                        "<p>Сумма: " + transaction.getAmount() +
                        "<p>Время: " + new SimpleDateFormat("hh:mm:ss yyyy.MM.dd").format(transaction.getTimestamp()) +
                        "<p>Счёт отправителя: " + (transaction.getFromAccount() != null ? String.valueOf(transaction.getFromAccount().getId()) : " ") +
                        "<p>Счёт получателя: " + transaction.getToAccount().getId() + " " + transaction.getToAccount().getType() +
                        "<p> " +
                        "<p> " +
                        "</html>";

                testPanel.add(label);
                label.setText(builder);
            } catch (SubjectException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }

            testPanel.revalidate();
            testPanel.repaint();
        }
    }

    private void createUIComponents() {
        testPanel = new JPanel();
        testPanel.setLayout(new BoxLayout(testPanel, BoxLayout.Y_AXIS));
    }
}
