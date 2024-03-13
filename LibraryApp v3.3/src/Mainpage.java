import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Mainpage extends JFrame {
    private workers loggedWorker;
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
        JMenuItem biblioteka2 = podMenu4.add(new JMenuItem("Cieszyn"));
        JMenuItem biblioteka3 = podMenu4.add(new JMenuItem("Ustroń"));
        JMenuItem biblioteka4 = podMenu4.add(new JMenuItem("Katowice"));

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

        daneKonta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panelInfo.add(dane, BorderLayout.CENTER);
                dane.setFont(new Font("Arial", Font.PLAIN, 18));
                dane.setText("Dane konta:" + "\n" + "Imię i nazwisko: " + loggedWorker.name + "\n" + "Adres: " + loggedWorker.address + "\n" + "Telefon: " + loggedWorker.phone +  "\n" + "Email: " + loggedWorker.email);
            }
        });


        zmianaHasla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //   new ChangePassword().setVisible(true);
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

    private void updateClock() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd\nEEEE\nHH:mm:ss");
        String formattedDate = dateFormat.format(new Date());
        clockText.setText(formattedDate);

    }

    private JMenuBar menu = new JMenuBar();
    //panele
    private JPanel desktop = new JPanel();
    private JPanel panel = new JPanel();
    private JPanel panelInfo = new JPanel();

    //etykiety

    //textArea

    private JTextArea dane = new JTextArea();
    private JTextArea clockText;
    private JTextArea allStudents = new JTextArea();

    private void showInfoAutor() {
        JTextArea infoAutor = new JTextArea();
        infoAutor.setFont(new Font("Arial", Font.PLAIN, 14));
        infoAutor.setText("Autor: Tomasz Król");

        JOptionPane.showMessageDialog(this, infoAutor, "Informacja o autorze", JOptionPane.INFORMATION_MESSAGE);
    }


public static void main(String[] args) {
        Mainpage mainpage = new Mainpage(null);
        mainpage.setVisible(true);
    }
}