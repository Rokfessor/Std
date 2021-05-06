package GUI;

import Exceptions.SecurityException;
import Exceptions.SubjectException;
import Security.SecurityManager;
import Subject.*;

import javax.swing.*;

public class SubjectRecoveryForm extends JPanel {
    private JPanel mainPanel;
    private JPanel panel;
    private JTextField loginTF;
    private JTextField secretWordTF;
    private JTextField newPasswordTF;
    private JTextField repeatNewPasswordTF;
    private JButton recoveryButton;
    private JButton backButton;

    public SubjectRecoveryForm(Parent parent) {
        add(mainPanel);

        backButton.addActionListener(e -> parent.repaintSelf());

        recoveryButton.addActionListener(e -> {
            String login = loginTF.getText();
            String secretWord = secretWordTF.getText();
            String password = newPasswordTF.getText();
            String repeatPassword = repeatNewPasswordTF.getText();

            if (login.isEmpty() || secretWord.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Все поля должны быть заполнены", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Subject subject = SubjectManager.getSubjectByLogin(login);

                if (!secretWord.equals(subject.getSecurityWord())) {
                    JOptionPane.showMessageDialog(this, "Неверное секретное слово", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!password.equals(repeatPassword)) {
                    JOptionPane.showMessageDialog(this, "Пароли должны совпадать", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                subject.setPassword(password);
                SecurityManager.editSubject(subject);
                JOptionPane.showMessageDialog(this, "Пароль успешно изменен!");
            } catch (SubjectException | SecurityException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
