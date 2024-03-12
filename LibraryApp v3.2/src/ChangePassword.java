import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ChangePassword extends JDialog {
    private JPanel glownyPanel3;
    private JPanel changePanel;
    private JPasswordField newPasswd;
    private JPasswordField repeatPasswd;
    private JPasswordField oldPasswd;
    private JButton anulujButton;
    private JButton zmienButton;



    public ChangePassword(JFrame parent) {
        super(parent);
        setTitle("ChangePassword");
        setContentPane(glownyPanel3);
        setMinimumSize(new Dimension(600, 400));
        setModal(true);
        setLocationRelativeTo(parent);
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
                worker = changePasswordWorkers();
                if(worker != null){
                    JOptionPane.showMessageDialog(ChangePassword.this, "Hasło zmienione", "Sukces", JOptionPane.INFORMATION_MESSAGE);
                    oldPasswd.setText("");
                    newPasswd.setText("");
                    repeatPasswd.setText("");
                }
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


      //  worker = changePasswordWorkers(oldPassword, newPassword, repeatPassword);


        return worker;
    }

    private workers changePasswordDatabase(String oldPassword, String newPassword, String repeatPassword){
        workers worker = null;
        final String DB_URL = "jdbc:mysql://localhost/libraryappdb?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String sql = "ALTER USER worker IDENTIFIED BY 'newPassword'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


        }
        catch(Exception e){
            e.printStackTrace();
        }











        return worker;
    }




    public static void main(String[] args) {
        ChangePassword startApplication = new ChangePassword(null);
        startApplication.setVisible(true);
    }
}
