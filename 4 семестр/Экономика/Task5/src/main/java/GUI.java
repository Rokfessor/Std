import transaction.Transaction;
import transaction.TransactionManager;
import utils.LogIn;
import utils.TransactionException;
import utils.UserType;

import bankAccount.BankAccount;
import bankAccount.BankAccountStorage;
import bankAccount.CurrentAccount;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    public GUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        drawAuthorizationPage();
    }

    private void drawAuthorizationPage() {
        SwingUtilities.invokeLater(() -> {
            JPanel panel = (JPanel) getContentPane();
            panel.removeAll();

            JTextField loginField = new JTextField();
            JTextField pinField = new JTextField();
            JLabel loginLabel = new JLabel("Login");
            JLabel passwordLabel = new JLabel("Pin");
            JButton loginButton = new JButton("Sign in");

            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            //======
            loginField.setText("100020003000");
            pinField.setText("1234");
            //======

            loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            loginField.setAlignmentX(Component.CENTER_ALIGNMENT);
            passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            pinField.setAlignmentX(Component.CENTER_ALIGNMENT);
            loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);


            loginField.setMaximumSize(new Dimension(100, 20));
            pinField.setMaximumSize(new Dimension(100, 20));

            loginButton.addActionListener(e -> {
                long login = Long.parseLong(loginField.getText());
                int pin = Integer.parseInt(pinField.getText());
                BankAccount bankAccount = LogIn.logIn(login, pin);
                if (bankAccount != null) {
                    CurrentAccount.currentAccount = bankAccount;
                    drawMainPage();
                }
            });

            panel.add(loginLabel);
            panel.add(loginField);
            panel.add(passwordLabel);
            panel.add(pinField);
            panel.add(loginButton);

            repaint();
            revalidate();
        });
    }

    private void drawMainPage() {
        SwingUtilities.invokeLater(() -> {
            JPanel panel = (JPanel) getContentPane();
            panel.removeAll();

            JButton backButton = new JButton("Выход");
            backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            backButton.addActionListener(e -> drawAuthorizationPage());

            JLabel nameLabel = new JLabel(CurrentAccount.currentAccount.name + " "
                    + CurrentAccount.currentAccount.surname);
            JButton topUpButton = new JButton("Пополонить счёт");
            JButton showAccountInfoButton = new JButton("Информация о счёте");
            JButton payTransferButton = new JButton("Перевод на другой счёт");
            JButton payUtilitiesButton = new JButton("Оплата коммунальных услуг");
            JButton payServicesButton = new JButton("Оплата услуг и товаров");
            JButton watchHistoryButton = new JButton("Посмотреть историю счёта");

            showAccountInfoButton.addActionListener(e -> drawAccountInfoPage());
            topUpButton.addActionListener(e -> drawTopUpPage());
            payTransferButton.addActionListener(e -> drawTransferPayPage());
            payUtilitiesButton.addActionListener(e -> drawUtilitiesPayPage());
            payServicesButton.addActionListener(e -> drawServicesPayPage());
            watchHistoryButton.addActionListener(e -> drawHistoryPage());

            showAccountInfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            topUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            payTransferButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            payUtilitiesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            payServicesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            watchHistoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            panel.add(nameLabel);
            panel.add(showAccountInfoButton);
            panel.add(topUpButton);
            panel.add(payTransferButton);
            panel.add(payUtilitiesButton);
            panel.add(payServicesButton);
            panel.add(watchHistoryButton);
            panel.add(backButton);

            repaint();
            revalidate();
        });
    }

    private void drawAccountInfoPage() {
        SwingUtilities.invokeLater(() -> {
            JPanel panel = (JPanel) getContentPane();
            panel.removeAll();

            JButton backButton = new JButton("<-");
            backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            backButton.addActionListener(e -> drawMainPage());

            JLabel nameLabel = new JLabel("Name: " + CurrentAccount.currentAccount.name);
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel surnameLabel = new JLabel("Surname: " + CurrentAccount.currentAccount.surname);
            surnameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel accIdLabel = new JLabel("Account id: " + CurrentAccount.currentAccount.personalAccountId);
            accIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel accLabel = new JLabel("Money: " + CurrentAccount.currentAccount.personalAccount);
            accLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            panel.add(backButton);
            panel.add(nameLabel);
            panel.add(surnameLabel);
            panel.add(accIdLabel);
            panel.add(accLabel);

            repaint();
            revalidate();
        });
    }

    private void drawHistoryPage() {
        SwingUtilities.invokeLater(() -> {
            JPanel panel = (JPanel) getContentPane();
            panel.removeAll();
            panel.setBorder(new EmptyBorder(5, 5, 5, 5));

            JButton backButton = new JButton("<-");
            backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            backButton.addActionListener(e -> drawMainPage());

            JLabel outLabel = new JLabel("Перевод со счёта");
            JLabel inLabel = new JLabel("Перевод на счёт");

            ArrayList<Transaction> inTransactions = TransactionManager.getInTransactions(CurrentAccount.currentAccount);

            String[] inNameArr = new String[]{"transaction.Transaction id", "To Personal Account Id", "Payment"};
            Object[][] inDataArr = new Object[inTransactions.size()][3];

            for (int i = 0; i < inTransactions.size(); i++) {
                inDataArr[i][0] = inTransactions.get(i).transactionId;
                inDataArr[i][1] = inTransactions.get(i).toPersonalAccountId;
                inDataArr[i][2] = inTransactions.get(i).payment;

            }

            JTable inTable = new JTable(inDataArr, inNameArr);
            inTable.setFillsViewportHeight(true);

            ArrayList<Transaction> outTransactions = TransactionManager.getOutTransactions(CurrentAccount.currentAccount);

            String[] outNameArr = new String[]{"transaction.Transaction id", "From Personal Account Id", "Payment"};
            Object[][] outDataArr = new Object[outTransactions.size()][3];

            for (int i = 0; i < outTransactions.size(); i++) {
                outDataArr[i][0] = outTransactions.get(i).transactionId;
                outDataArr[i][1] = outTransactions.get(i).fromPersonalAccountId;
                outDataArr[i][2] = outTransactions.get(i).payment;
            }

            JTable outTable = new JTable(outDataArr, outNameArr);
            outTable.setFillsViewportHeight(true);

            JScrollPane outScrollPane = new JScrollPane(inTable);
            JScrollPane inScrollPane = new JScrollPane(outTable);

            panel.add(backButton);
            panel.add(inLabel);
            panel.add(inScrollPane);
            panel.add(outLabel);
            panel.add(outScrollPane);

            repaint();
            revalidate();
        });
    }

    private void drawServicesPayPage() {
        SwingUtilities.invokeLater(() -> {
            JPanel panel = (JPanel) getContentPane();
            panel.removeAll();
            panel.setBorder(new EmptyBorder(5, 5, 5, 5));

            JButton backButton = new JButton("<-");
            backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            backButton.addActionListener(e -> drawMainPage());

            JLabel dataLabel = new JLabel("Введите данные для оплаты (номер телефона, адрес и т.д.)");
            dataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField dataTF = new JTextField();
            dataTF.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel sumLabel = new JLabel("Сумма оплаты");
            dataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField sumTF = new JTextField();
            dataTF.setAlignmentX(Component.CENTER_ALIGNMENT);

            ArrayList<BankAccount> list = BankAccountStorage.getBankAccountsByType(UserType.SERVICE);
            String[] fieldsName = {"Name", "Account id"};
            Object[][] data = new Object[list.size()][2];
            for (int i = 0; i < list.size(); i++) {
                data[i][0] = list.get(i).name;
                data[i][1] = list.get(i).personalAccountId;
            }
            JTable table = new JTable(data, fieldsName);
            table.setFillsViewportHeight(true);

            JButton submitButton = new JButton("Оплатить");
            submitButton.addActionListener(e -> {
                long accId = Long.parseLong(table.getValueAt(table.getSelectedRow(), 1).toString());
                try {
                    long payment = Long.parseLong(sumTF.getText());
                    try {
                        TransactionManager.makeTransaction(CurrentAccount.currentAccount,
                                BankAccountStorage.getByPersonalAccountId(accId), payment);
                    } catch (TransactionException te) {
                        drawTransactionInfoPage(te.getMessage());
                    }
                } catch (NumberFormatException ex) {
                    drawTransactionInfoPage("Некорректная сумма");
                }
                drawTransactionInfoPage("Оплата успешно проведена");
            });

            JScrollPane sc = new JScrollPane(table);

            panel.add(backButton);
            panel.add(sc);
            panel.add(dataLabel);
            panel.add(dataTF);
            panel.add(sumLabel);
            panel.add(sumTF);
            panel.add(submitButton);

            repaint();
            revalidate();
        });
    }

    private void drawUtilitiesPayPage() {
        SwingUtilities.invokeLater(() -> {
            JPanel panel = (JPanel) getContentPane();
            panel.removeAll();
            panel.setBorder(new EmptyBorder(5, 5, 5, 5));

            JButton backButton = new JButton("<-");
            backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            backButton.addActionListener(e -> drawMainPage());

            JLabel dataLabel = new JLabel("Введите данные для оплаты (номер лицевого счёта, адрес и т.д.)");
            dataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField dataTF = new JTextField();
            dataTF.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel sumLabel = new JLabel("Сумма оплаты");
            dataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField sumTF = new JTextField();
            dataTF.setAlignmentX(Component.CENTER_ALIGNMENT);

            ArrayList<BankAccount> list = BankAccountStorage.getBankAccountsByType(UserType.ORGANISATION);
            String[] fieldsName = {"Name", "Account id"};
            Object[][] data = new Object[list.size()][2];
            for (int i = 0; i < list.size(); i++) {
                data[i][0] = list.get(i).name;
                data[i][1] = list.get(i).personalAccountId;
            }
            JTable table = new JTable(data, fieldsName);
            table.setFillsViewportHeight(true);

            JButton submitButton = new JButton("Оплатить");
            submitButton.addActionListener(e -> {
                long accId = Long.parseLong(table.getValueAt(table.getSelectedRow(), 1).toString());
                try {
                    long payment = Long.parseLong(sumTF.getText());
                    try {
                        TransactionManager.makeTransaction(CurrentAccount.currentAccount,
                                BankAccountStorage.getByPersonalAccountId(accId), payment);
                    } catch (TransactionException te) {
                        drawTransactionInfoPage(te.getMessage());
                    }
                } catch (NumberFormatException ex) {
                    drawTransactionInfoPage("Некорректная сумма");
                }
                drawTransactionInfoPage("Оплата успешно проведена");
            });

            JScrollPane sc = new JScrollPane(table);

            panel.add(backButton);
            panel.add(sc);
            panel.add(dataLabel);
            panel.add(dataTF);
            panel.add(sumLabel);
            panel.add(sumTF);
            panel.add(submitButton);

            repaint();
            revalidate();
        });
    }

    private void drawTransferPayPage() {
        SwingUtilities.invokeLater(() -> {
            JPanel panel = (JPanel) getContentPane();
            panel.removeAll();

            JButton backButton = new JButton("<-");
            backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            backButton.addActionListener(e -> drawMainPage());

            JLabel accLabel = new JLabel("Номер счёта");
            accLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField accTF = new JTextField("");
            accTF.setAlignmentX(Component.CENTER_ALIGNMENT);
            accTF.setMaximumSize(new Dimension(100, 20));

            JLabel topUpLabel = new JLabel("Сумма перевода");
            topUpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField topUpTF = new JTextField("0");
            topUpTF.setAlignmentX(Component.CENTER_ALIGNMENT);
            topUpTF.setMaximumSize(new Dimension(100, 20));
            JButton submitButton = new JButton("Перевести");
            submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            submitButton.addActionListener(e -> {
                try {
                    long payment = Long.parseLong(topUpTF.getText());
                    long accId = Long.parseLong(accTF.getText());
                    TransactionManager.makeTransaction(CurrentAccount.currentAccount, BankAccountStorage.getByPersonalAccountId(accId), payment);
                    drawTransactionInfoPage("Счёт успешно пополнен");
                } catch (NumberFormatException | TransactionException ex) {
                    drawTransactionInfoPage("Введены некорректные данные");
                }
            });

            panel.add(backButton);
            panel.add(accLabel);
            panel.add(accTF);
            panel.add(topUpLabel);
            panel.add(topUpTF);
            panel.add(submitButton);

            repaint();
            revalidate();
        });
    }

    public void drawTransactionInfoPage(String text) {
        SwingUtilities.invokeLater(() -> {
            JPanel panel = (JPanel) getContentPane();
            panel.removeAll();

            JLabel textLabel = new JLabel(text);
            textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton backButton = new JButton("Вернуться на главную страницу");
            backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            backButton.addActionListener(e -> drawMainPage());

            panel.add(textLabel);
            panel.add(backButton);

            repaint();
            revalidate();
        });
    }

    public void drawTopUpPage() {
        SwingUtilities.invokeLater(() -> {
            JPanel panel = (JPanel) getContentPane();
            panel.removeAll();

            JButton backButton = new JButton("<-");
            backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            backButton.addActionListener(e -> drawMainPage());

            JLabel textLabel = new JLabel("Сумма пополнения");
            textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField topUpTF = new JTextField("0");
            topUpTF.setAlignmentX(Component.CENTER_ALIGNMENT);
            topUpTF.setMaximumSize(new Dimension(100, 20));
            JButton submitButton = new JButton("Пополнить");
            submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            submitButton.addActionListener(e -> {
                try {
                    long payment = Long.parseLong(topUpTF.getText());
                    TransactionManager.makeTransaction(null, CurrentAccount.currentAccount, payment);
                    drawTransactionInfoPage("Счёт успешно пополнен");
                } catch (NumberFormatException | TransactionException ex) {
                    drawTransactionInfoPage("Введена некорректная сумма");
                }
            });

            panel.add(backButton);
            panel.add(textLabel);
            panel.add(topUpTF);
            panel.add(submitButton);

            repaint();
            revalidate();
        });
    }
}
