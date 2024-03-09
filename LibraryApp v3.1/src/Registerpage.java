import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Registerpage extends JDialog{
    private JPanel glownyPanel2;
    private JTextField Xlogin;
    private JPasswordField Xpassword;
    private JTextField Xphone;
    private JTextField Xemail;
    private JTextField Xaddress;
    private JPanel glownyPanel;
    private JButton STWÓRZKONTOButton;
    private JButton ZALOGUJButton;
    private JTextField Xname;
    private JLabel EMAILLabel;
    private JLabel NAMELabel;
    private JLabel ADDRESSLabel;
    private JLabel PHONELabel;
    private JLabel PASSWORDLabel;
    private JLabel LOGINLabel;
    private JPanel welcomePanel2;

    public Registerpage(JFrame parent) {
        super(parent);
        setTitle("Register");
        setContentPane(glownyPanel2);
        setMinimumSize(new Dimension(800, 800));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ZALOGUJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Loginpage(null).setVisible(true);
            }
        });
        STWÓRZKONTOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();

                if (user != null) {
                    JOptionPane.showMessageDialog(Registerpage.this, "Account created", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Xaddress.setText("");
                    Xlogin.setText("");
                    Xpassword.setText("");
                    Xname.setText("");
                    Xemail.setText("");
                    Xphone.setText("");
                }
            }
        });
    }

    public users user;

    private users registerUser() {
        String name = Xname.getText();
        String email = Xemail.getText();
        String phone = Xphone.getText();
        String address = Xaddress.getText();
        String login = Xlogin.getText();
        String password = String.valueOf(Xpassword.getPassword());

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || login.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Wprowadz dane poprawnie", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        user = addUserToDatabase(name, email, phone, address, login, password);

        return user;
    }


    private users addUserToDatabase(String name, String email, String phone, String address, String login, String password) {
        users user = null;
        final String DB_URL = "jdbc:mysql://localhost/libraryappdb?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String sql = "INSERT INTO users (name, email, phone, address, login, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, login);
            preparedStatement.setString(6, password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new users();
                user.name = name;
                user.email = email;
                user.phone = phone;
                user.address = address;
                user.login = login;
                user.password = password;
            }
            statement.close();
            connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


        return user;
    }


    public static void main(String[] args) {
        Registerpage registerpage = new Registerpage(null);
        registerpage.setVisible(true);
        users user = registerpage.user;

        if (user != null) {
            System.out.println("Pomyślnie utworzono konto");
        } else {
            System.out.println("Błąd podczas tworzenia konta");
        }
    }
}