package GUI;

import Database.DBHandler;
import Exceptions.SecurityException;
import Security.SecurityManager;
import Subject.Subject;

import javax.swing.*;
import java.sql.SQLException;

public class RegistrationForm extends JPanel {
    private JPanel mainPanel;
    private JTextField loginTF;
    private JTextField secretWordTF;
    private JTextField passwordTF;
    private JTextField repeatPasswordTF;
    private JButton registrationButton;
    private JButton backButton;
    private JTextField nameTF;
    private JComboBox typeCB;

    public RegistrationForm(Parent parent) {
        add(mainPanel);
        registrationButton.addActionListener(e -> {

            String login = loginTF.getText();
            String secretWord = secretWordTF.getText();
            String password = passwordTF.getText();
            String repeatPassword = repeatPasswordTF.getText();
            String name = nameTF.getText();
            String type = (String) typeCB.getSelectedItem();
            if (login.equals("") || secretWord.equals("") || password.equals("") || repeatPassword.equals("") || name.equals("")) {
                JOptionPane.showMessageDialog(this, "Все поля должны быть заполнены", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!password.equals(repeatPassword)) {
                JOptionPane.showMessageDialog(this, "Пароли должны совпадать", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            switch (type) {
                case "Физическое лицо" -> type = Subject.CLIENT_TYPE;
                case "Частная организация\\ИП" -> type = Subject.PRIVATE_ORGANISATION_TYPE;
                case "Государственная организация" -> type = Subject.PUBLIC_ORGANISATION_TYPE;
            }
            Subject subject = new Subject();
            subject.setPassword(password);
            subject.setLogin(login);
            subject.setSecurityWord(secretWord);
            subject.setName(name);
            subject.setType(type);

            try {
                SecurityManager.registerNewSubject(subject);
                JOptionPane.showMessageDialog(this, "Регистрация прошла успешно!");
            } catch (SecurityException throwables) {
                JOptionPane.showMessageDialog(this, throwables.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                throwables.printStackTrace();
            }
        });

        backButton.addActionListener(e -> parent.repaintSelf());
    }
}
