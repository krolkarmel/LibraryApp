import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StartApp extends JDialog{
    private JTextField Xlogin;
    private JPasswordField Xpassword;
    private JButton buttonLogin;
    private JButton buttonExit;
    private JPanel loginPanel;
    private JPanel panelMain;
    private JButton buttonSign;

    public StartApp(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(panelMain);
        setMinimumSize(new Dimension(800, 800));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = Xlogin.getText();
                String password = String.valueOf(Xpassword.getPassword());

                user = getAuthenticatedUser(login, password);

                if(user != null) {
                    dispose();
                    new Main().setVisible(true);
                }
                    else{
                        JOptionPane.showMessageDialog(StartApp.this, "Invalid login or password", "Error", JOptionPane.ERROR_MESSAGE);

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
                new Register(null).setVisible(true);
            }
        });
    }


    public users user;
    private users getAuthenticatedUser(String login, String password){
        users user = null;

        final String DB_URL = "jdbc:mysql://localhost/libraryappdb?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM users WHERE login = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new users();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.address = resultSet.getString("address");
                user.login = resultSet.getString("login");
                user.password = resultSet.getString("password");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return user;
    }


    public static void main(String[] args) {
    StartApp startapp = new StartApp(null);
    users user =  startapp.user;
    if(user != null){
        System.out.println("Logged in as: " + user.name);
        System.out.println("Email: " + user.email);
        System.out.println("Phone: " + user.phone);
        System.out.println("Address: " + user.address);

    }
    else{
        System.out.println("Authentication failed!");
    }
}
}
