package GUI;

import Exceptions.SecurityException;
import GUI.AccountForm.AccountForm;
import Security.SecurityManager;

import javax.swing.*;

public class LoginForm extends JPanel implements Parent {
    private JPanel mainPanel;
    private JTextField loginTF;
    private JTextField passwordTF;
    private JPanel panel;
    private JButton signInButton;
    private JButton registrationButton;
    private JButton forgetPasswordButton;

    public LoginForm() {
        add(mainPanel);
        registrationButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            removeAll();
            add(new RegistrationForm(this));
            revalidate();
            repaint();
        }));

        signInButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            try {
                if (SecurityManager.login(loginTF.getText(), passwordTF.getText())) {
                    removeAll();
                    add(new AccountForm(this));
                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "Неверный логин или пароль!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SecurityException securityException) {
                JOptionPane.showMessageDialog(this, securityException.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }));

        forgetPasswordButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            removeAll();
            add(new SubjectRecoveryForm(this));
            revalidate();
            repaint();
        }));
    }

    @Override
    public void repaintSelf() {
        SwingUtilities.invokeLater(() -> {
            removeAll();
            add(mainPanel);
            revalidate();
            repaint();
        });
    }
}
