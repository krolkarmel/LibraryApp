import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main extends JFrame {
    public Main(){
        initComponents();
    }
    public void initComponents(){
        this.setTitle("LibraryApp");
        this.setBounds(100,100,600,600);
        this.setIconImage(new ImageIcon("owl.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(menu);

        JMenu podMenu1 = menu.add(new JMenu("Uczeń"));
        JMenuItem dodajUcznia = podMenu1.add(new JMenuItem("Dodaj ucznia"));
        JMenuItem usunUcznia =  podMenu1.add(new JMenuItem("Usuń ucznia"));

        JMenu podMenu2 = menu.add(new JMenu("Wyświetl"));
        JMenuItem klasa1 = podMenu2.add(new JMenuItem("Klasa 1"));
        JMenuItem klasa2 =  podMenu2.add(new JMenuItem("Klasa 2"));
        JMenuItem klasa3 =  podMenu2.add(new JMenuItem("Klasa 3"));
        JMenuItem wszyscyUczniowie =  podMenu2.add(new JMenuItem("Wszyscy uczniowie"));

        JMenu podMenu3 = menu.add(new JMenu("Posortuj"));
        JMenuItem alfabetycznieImiona = podMenu3.add(new JMenuItem("Alfabetycznie Imiona"));
        JMenuItem alfabetycznieNazwiska =  podMenu3.add(new JMenuItem("Alfabetycznie Nazwiska"));

        JMenu podMenu4 = menu.add(new JMenu("Plik"));
        JMenuItem utworz =  podMenu4.add(new JMenuItem("Utworz"));

        JMenu podMenu5 = menu.add(new JMenu("Info"));
        JMenuItem autor =  podMenu5.add(new JMenuItem("Autor"));


        clockText = new JTextArea();
        clockText.setFont(new Font("Arial", Font.BOLD, 16));
        clockText.setEditable(false);
        clockText.setBackground(Color.DARK_GRAY);
        clockText.setForeground(Color.GREEN);

        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();

        panelInfo.add(allStudents);
        panel.setLayout(new BorderLayout());
        panel.add(clockText, BorderLayout.EAST);
        panel.setBackground(Color.LIGHT_GRAY);
        desktop.setLayout(new BorderLayout());
        desktop.add(panel, BorderLayout.EAST);
        desktop.add(panelInfo, BorderLayout.WEST);

        this.getContentPane().add(desktop);

        autor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInfoAutor();
            }
        });

        klasa1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showAllStudents("klasa1");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        klasa2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showAllStudents("klasa2");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        klasa3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showAllStudents("klasa3");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        wszyscyUczniowie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showAllStudents("uczniowie");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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
    private JTextArea clockText;
    private JTextArea allStudents = new JTextArea();
    private void showInfoAutor(){
        JTextArea infoAutor = new JTextArea();
        infoAutor.setFont(new Font("Arial", Font.PLAIN, 14));
        infoAutor.setText("Autor: Tomasz Król");

        JOptionPane.showMessageDialog(this, infoAutor, "Informacja o autorze", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showAllStudents(String nPliku) throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedReader in = null;
        String l = "";
        String nPlik = "";


        in = new BufferedReader(new FileReader(nPliku +  ".TXT"));
        allStudents.setText("");

        while((l = in.readLine()) != null){
            String [] dane = l.trim().split(" +");
            int id = Integer.parseInt(dane[0]);
            String imie = dane[1];
            String nazwisko = dane[2];


            allStudents.append(id + ". " + imie + " " + nazwisko + "\n");
        }
    }

    public static void main(String[] args)
    {
        new Main().setVisible(true);
    }
}