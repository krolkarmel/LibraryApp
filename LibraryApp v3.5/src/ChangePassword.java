import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ChangePassword extends JDialog {
    private JPanel glownyPanel3;
    private JPanel changePanel;
    private JPasswordField newPasswd;
    private JPasswordField repeatPasswd;
    private JPasswordField oldPasswd;
    private JButton anulujButton;
    private JButton zmienButton;


    private workers loggedWorker;
    public ChangePassword(workers loggedWorker) {
        this.loggedWorker = loggedWorker;
        setTitle("ChangePassword");
        setContentPane(glownyPanel3);
        setMinimumSize(new Dimension(600, 400));
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        zmienButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePasswordWorkers();
            }
        });
    }
    public workers worker;
    private workers changePasswordWorkers(){
        String oldPassword = String.valueOf(oldPasswd.getPassword());
        String newPassword = String.valueOf(newPasswd.getPassword());
        String repeatPassword = String.valueOf(repeatPasswd.getPassword());

        if(oldPassword.isEmpty() && newPassword.isEmpty() && repeatPassword.isEmpty()){
            JOptionPane.showMessageDialog(ChangePassword.this, "Nie wprowadziłeś danych", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if(oldPassword.isEmpty()){
            JOptionPane.showMessageDialog(ChangePassword.this, "Nie wprowadziłeś starego hasła", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(newPassword.isEmpty()){
            JOptionPane.showMessageDialog(ChangePassword.this, "Nie wprowadziłeś nowego hasła", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(repeatPassword.isEmpty()){
            JOptionPane.showMessageDialog(ChangePassword.this, "Nie wprowadziłeś powtórzenia nowego hasła", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if(!newPassword.equals(repeatPassword)){
            JOptionPane.showMessageDialog(ChangePassword.this, "Nowe hasła nie pasują", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if(oldPassword.equals(newPassword)){
            JOptionPane.showMessageDialog(ChangePassword.this, "Nowe hasło nie może być takie samo jak stare", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        changePasswordDatabase(newPassword);

        return worker;
    }

    private void changePasswordDatabase(String newPassword) {
        final String DB_URL = "jdbc:mysql://localhost/libraryappdb?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String sql = "UPDATE workers SET password = ? WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, loggedWorker.name);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(ChangePassword.this, "Password changed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(ChangePassword.this, "Failed to change password. No records were updated.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(ChangePassword.this, "Failed to change password. Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        System.out.println(loggedWorker.name);
        System.out.println(newPassword);
        System.out.println(loggedWorker.id);
    }

    public static void main(String[] args) {
        ChangePassword startApplication = new ChangePassword(null);
        startApplication.setVisible(true);
    }
}
