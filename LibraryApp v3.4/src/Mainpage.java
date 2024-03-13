import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Mainpage extends JFrame {
    private workers loggedWorker;
    private biblioteki biblioteka;
    public Mainpage(workers loggedWorker) {
        this.loggedWorker = loggedWorker;
        initComponents();
    }

    public void initComponents() {
        this.setTitle("LibraryApp");
        this.setMinimumSize(new Dimension(800, 800));
        this.setIconImage(new ImageIcon("owl.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(menu);

        JMenu podMenu1 = menu.add(new JMenu("Czytelnik"));
        JMenuItem dodajUcznia = podMenu1.add(new JMenuItem("Dodaj czytelnika"));
        JMenuItem wyszukajCzytelnika = podMenu1.add(new JMenuItem("Wyszukaj czytelnika"));
        JMenuItem usunUcznia = podMenu1.add(new JMenuItem("Usuń czytelnika"));

        JMenu podMenu2 = menu.add(new JMenu("Książka"));
        JMenuItem dodajKsiazke = podMenu2.add(new JMenuItem("Dodaj książkę"));
        JMenuItem wyszukajKsiazke = podMenu2.add(new JMenuItem("Wyszukaj książkę"));
        JMenuItem usunKsiazke = podMenu2.add(new JMenuItem("Usuń książkę"));

        JMenu podMenu3 = menu.add(new JMenu("Wypożyczenia po terminie"));

        JMenu podMenu4 = menu.add(new JMenu("Nasze biblioteki"));
        JMenuItem biblioteka1 = podMenu4.add(new JMenuItem("Bielsko-Biała"));
        JMenuItem biblioteka2 = podMenu4.add(new JMenuItem("Rudzica"));
        JMenuItem biblioteka3 = podMenu4.add(new JMenuItem("Jasienica"));
        JMenuItem biblioteka4 = podMenu4.add(new JMenuItem("Iłownica"));

        JMenu podMenu6 = menu.add(new JMenu("Moje konto"));
        JMenuItem daneKonta = podMenu6.add(new JMenuItem("Dane konta"));
        JMenuItem zmianaHasla = podMenu6.add(new JMenuItem("Zmień hasło"));
        JMenuItem wylogowanie = podMenu6.add(new JMenuItem("Wyloguj"));

        clockText = new JTextArea();
        clockText.setFont(new Font("Arial", Font.BOLD, 16));
        clockText.setEditable(false);
        clockText.setBackground(Color.DARK_GRAY);
        clockText.setForeground(Color.GREEN);

        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();

        JScrollPane scrollPane = new JScrollPane(allStudents);
        scrollPane.setPreferredSize(new Dimension(300, 400));
        panel.setLayout(new BorderLayout());
        panel.add(clockText, BorderLayout.EAST);
        panel.setBackground(Color.LIGHT_GRAY);
        desktop.setLayout(new BorderLayout());
        desktop.add(panel, BorderLayout.EAST);
        desktop.add(panelInfo, BorderLayout.WEST);

        this.getContentPane().add(desktop);

        allStudents.setFont(new Font("Arial", Font.PLAIN, 18));

        //action listenery

        biblioteka1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                biblioteki.setFont(new Font("Arial", Font.PLAIN, 18));
                lista.clear();
                removeDojazdActionListeners();
                panelInfo.add(biblioteki, BorderLayout.CENTER);
                showBiblioteki("Bielsko-Biała");
                StringBuilder sb = new StringBuilder();
                sb.append("Biblioteki Bielsko-Biała: " + "\n"+ "\n");
                for (biblioteki biblioteka : lista) {
                            sb.append("Adres: ").append(biblioteka.miasto).append(" ul. ").append(biblioteka.ulica).append(" ").append(biblioteka.numer_budynku).append("\n")
                            .append("Numer telefonu: ").append(biblioteka.numer_telefonu_biblioteki).append("\n\n");
                }
                biblioteki.setText(sb.toString());
            panelInfo.add(dojazd, BorderLayout.SOUTH);
                dojazd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Desktop.getDesktop().browse(new URL("https://www.google.com/maps/place/Ma%C5%82a+Rudzica,+43-394+Rudzica/@49.8510865,18.9046543,17z/data=!3m1!4b1!4m6!3m5!1s0x4716a6ea5639bcbf:0x9cd590f10a02fcf8!8m2!3d49.8510831!4d18.9095252!16s%2Fg%2F1hhlncmj8?hl=pl-PL&entry=ttu").toURI());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (URISyntaxException ex) {
                            throw new RuntimeException(ex);
                        }

                    }

                });
            }
        });

        biblioteka2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lista.clear();
                removeDojazdActionListeners();
                panelInfo.add(biblioteki, BorderLayout.CENTER);
                showBiblioteki("Rudzica");
                biblioteki.setFont(new Font("Arial", Font.PLAIN, 18));
                biblioteki.setText("Biblioteki " + biblioteka.miasto + "\n"+ "\n" + "Adres: " + biblioteka.miasto + " ul. " + biblioteka.ulica + " " + biblioteka.numer_budynku + "\n" + "Numer telefonu: " + biblioteka.numer_telefonu_biblioteki);
                panelInfo.add(dojazd, BorderLayout.SOUTH);
                dojazd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Desktop.getDesktop().browse(new URL("https://www.google.com/maps/place/Ma%C5%82a+Rudzica,+43-394+Rudzica/@49.8510865,18.9046543,17z/data=!3m1!4b1!4m6!3m5!1s0x4716a6ea5639bcbf:0x9cd590f10a02fcf8!8m2!3d49.8510831!4d18.9095252!16s%2Fg%2F1hhlncmj8?hl=pl-PL&entry=ttu").toURI());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (URISyntaxException ex) {
                            throw new RuntimeException(ex);
                        }

                    }

                });
            }
        });

        biblioteka3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lista.clear();
                removeDojazdActionListeners();
                panelInfo.add(biblioteki, BorderLayout.CENTER);
                showBiblioteki("Jasienica");
                biblioteki.setFont(new Font("Arial", Font.PLAIN, 18));
                biblioteki.setText("Biblioteki " + biblioteka.miasto + "\n"+ "\n" + "Adres: " + biblioteka.miasto + " ul. " + biblioteka.ulica + " " + biblioteka.numer_budynku + "\n" + "Numer telefonu: " + biblioteka.numer_telefonu_biblioteki);
                panelInfo.add(dojazd, BorderLayout.SOUTH);
                dojazd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Desktop.getDesktop().browse(new URL("https://www.google.com/maps/place/Ma%C5%82a+Rudzica,+43-394+Rudzica/@49.8510865,18.9046543,17z/data=!3m1!4b1!4m6!3m5!1s0x4716a6ea5639bcbf:0x9cd590f10a02fcf8!8m2!3d49.8510831!4d18.9095252!16s%2Fg%2F1hhlncmj8?hl=pl-PL&entry=ttu").toURI());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (URISyntaxException ex) {
                            throw new RuntimeException(ex);
                        }

                    }

                });
            }
        });

        biblioteka4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeDojazdActionListeners();
                lista.clear();
                panelInfo.add(biblioteki, BorderLayout.CENTER);
                showBiblioteki("Iłownica");
                biblioteki.setFont(new Font("Arial", Font.PLAIN, 18));
                biblioteki.setText("Biblioteki " + biblioteka.miasto + "\n"+ "\n" + "Adres: " + biblioteka.miasto + " ul. " + biblioteka.ulica + " " + biblioteka.numer_budynku + "\n" + "Numer telefonu: " + biblioteka.numer_telefonu_biblioteki);
                panelInfo.add(dojazd, BorderLayout.SOUTH);
                dojazd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Desktop.getDesktop().browse(new URL("https://www.google.com/maps/search/Londzina+432/@49.8952059,18.7688783,13z/data=!3m1!4b1?hl=pl-PL&entry=ttu").toURI());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (URISyntaxException ex) {
                            throw new RuntimeException(ex);
                        }

                    }

                });
            }
        });

        daneKonta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panelInfo.add(dane, BorderLayout.CENTER);
                dane.setFont(new Font("Arial", Font.PLAIN, 18));
                dane.setText("Dane konta:" + "\n" + "Imię i nazwisko: " + loggedWorker.name + "\n" + "Adres: " + loggedWorker.address + "\n" + "Telefon: " + loggedWorker.phone +  "\n" + "Email: " + loggedWorker.email);
            }
        });

        wylogowanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(Mainpage.this, "Czy na pewno chcesz się wylogować?", "Wylogowanie", JOptionPane.YES_NO_OPTION)) {
                    dispose();
                    new Loginpage(null).setVisible(true);
                }

            }
        });

        zmianaHasla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePassword(loggedWorker).setVisible(true);
            }
        });



    }

    private void removeDojazdActionListeners() {
        ActionListener[] listeners = dojazd.getActionListeners();
        for (ActionListener listener : listeners) {
            dojazd.removeActionListener(listener);
        }
    }

    private void updateClock() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd\nEEEE\nHH:mm:ss");
        String formattedDate = dateFormat.format(new Date());
        clockText.setText(formattedDate);
    }

    private biblioteki showBiblioteki(String wybraneMiasto) {
        final String DB_URL = "jdbc:mysql://localhost/libraryappdb?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM biblioteki WHERE miasto = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wybraneMiasto);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                biblioteka = new biblioteki();
                biblioteka.miasto = resultSet.getString("miasto");
                biblioteka.ulica = resultSet.getString("ulica");
                biblioteka.numer_budynku = resultSet.getString("numer_budynku");
                biblioteka.numer_telefonu_biblioteki = resultSet.getString("numer_telefonu_biblioteki");

                lista.add(biblioteka);

            }
            updateBibliotekiText();
        } catch (Exception fileNotFoundException) {
        }
        return biblioteka;
    }
    private void updateBibliotekiText() {
        StringBuilder sb = new StringBuilder();
        for (biblioteki biblioteka : lista) {
            sb.append("Biblioteki ").append(biblioteka.miasto).append("\n")
                    .append("Adres: ").append(biblioteka.miasto).append(" ul. ").append(biblioteka.ulica).append(" ").append(biblioteka.numer_budynku).append("\n")
                    .append("Numer telefonu: ").append(biblioteka.numer_telefonu_biblioteki).append("\n\n");
        }
        biblioteki.setText(sb.toString());
    }
    private ArrayList<biblioteki> lista = new ArrayList<>();
    private biblioteki wybraneMiasto;

    private JMenuBar menu = new JMenuBar();
    //panele
    private JPanel desktop = new JPanel();
    private JPanel panel = new JPanel();
    private JPanel panelInfo = new JPanel();

    //etykiety

    //textArea

    private JTextArea biblioteki = new JTextArea();
    private JTextArea dane = new JTextArea();
    private JTextArea clockText;
    private JTextArea allStudents = new JTextArea();

    //buttony
    private JButton dojazd = new JButton("Jak trafic?");



    public static void main(String[] args) {
        Mainpage mainpage = new Mainpage(null);
        mainpage.setVisible(true);
    }
}