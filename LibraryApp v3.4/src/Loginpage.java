import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Loginpage extends JDialog {
    private JPanel loginPanel;
    private JTextField Xlogin;
    private JPasswordField Xpassword;
    private JPanel glownyPanel1;
    private JPanel welcomePanel1;
    private JButton buttonLogin;
    private JButton buttonExit;
    private JPanel panelMain;
    private JButton buttonSign;

    public Loginpage(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(glownyPanel1);
        setMinimumSize(new Dimension(800, 800));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = Xlogin.getText();
                String password = String.valueOf(Xpassword.getPassword());

                worker = getAuthenticatedWorkers(login, password);

                if (login.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Loginpage.this, "Login and password are required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (worker != null) {
                    dispose();
                    openMainpage(worker);
                } else {
                    JOptionPane.showMessageDialog(Loginpage.this, "Invalid login or password", "Error", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonSign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Registerpage(null).setVisible(true);
            }
        });
    }

    private void openMainpage(workers loggedWorker) {
        Mainpage mainpage = new Mainpage(worker);
        mainpage.setVisible(true);
    }
    public workers worker;

    private workers getAuthenticatedWorkers(String login, String password) {
        workers worker = null;

        final String DB_URL = "jdbc:mysql://localhost/libraryappdb?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM workers WHERE login = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                worker = new workers();
                worker.name = resultSet.getString("name");
                worker.email = resultSet.getString("email");
                worker.phone = resultSet.getString("phone");
                worker.address = resultSet.getString("address");
                worker.login = resultSet.getString("login");
                worker.password = resultSet.getString("password");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return worker;
    }


    public static void main(String[] args) {
        Loginpage loginpage = new Loginpage(null);
        loginpage.setVisible(true);
    }
}