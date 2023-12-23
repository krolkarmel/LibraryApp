import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
        clockText.setFont(new Font("Arial", Font.PLAIN, 24));
        clockText.setEditable(false);

        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();




        panel.add(clockText);
        this.getContentPane().add(panel);
    }
    private void updateClock() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd\nHH:mm:ss\nEEEE");
        String formattedDate = dateFormat.format(new Date());
        clockText.setText(formattedDate);
    }
    private JMenuBar menu = new JMenuBar();
    //panele
    private JPanel panel = new JPanel();
    //etykiety

    //textArea
    private JTextArea clockText;

    public static void main(String[] args)
    {
        new Main().setVisible(true);
    }
}