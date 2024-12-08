package pl.feen1e;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class MainApp
{
    private final JFrame mainFrame;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final JMenuBar menuBar;
    private final JButton backButton;

    private final HashMap<String, String> titles = new HashMap<>();

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(MainApp::new);
    }

    public MainApp()
    {
        FlatLightLaf.setup();
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);

        initializeTitles();
        mainFrame.setTitle(titles.get("Menu"));

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Dodanie kolejnych aplikacji do cardPanel
        cardPanel.add(createMenu(), "Menu");
        cardPanel.add(new Exercise1(), "Zadanie 1");
        cardPanel.add(new Exercise2(), "Zadanie 2");
        cardPanel.add(new Exercise3(), "Zadanie 3");
        cardPanel.add(new Exercise4(), "Zadanie 4");
        cardPanel.add(new Exercise5(), "Zadanie 5");
        cardPanel.add(new Exercise6(), "Zadanie 6");
        cardPanel.add(new Exercise7(), "Zadanie 7");
        cardPanel.add(new Exercise8(), "Zadanie 8");

        menuBar = new JMenuBar();
        backButton = new JButton("Powrót do Menu");
        backButton.addActionListener((ActionEvent e) -> showApp("Menu"));

        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(cardPanel);
        mainFrame.setVisible(true);
    }

    public JPanel createMenu()
    {
        JPanel menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        menuPanel.setLayout(new BorderLayout(5, 5));
        JLabel title = new JLabel("Wybierz zadanie do uruchomienia: ", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.PLAIN, 18));
        title.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        menuPanel.add(title, BorderLayout.NORTH);
        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new GridLayout(3, 3, 20, 20));

        JButton ex1Button = new JButton("Zadanie 1 - zmiana koloru tła");
        ex1Button.addActionListener((ActionEvent e) -> showApp("Zadanie 1"));
        mainMenu.add(ex1Button);

        JButton ex2Button = new JButton("Zadanie 2 - prosty notatnik");
        ex2Button.addActionListener((ActionEvent e) -> showApp("Zadanie 2"));
        mainMenu.add(ex2Button);

        JButton ex3Button = new JButton("Zadanie 3 - lista zakupów");
        ex3Button.addActionListener((ActionEvent e) -> showApp("Zadanie 3"));
        mainMenu.add(ex3Button);

        JButton ex4Button = new JButton("Zadanie 4 - konwerter jednostek");
        ex4Button.addActionListener((ActionEvent e) -> showApp("Zadanie 4"));
        mainMenu.add(ex4Button);

        JButton ex5Button = new JButton("Zadanie 5 - quiz wiedzy ogólnej");
        ex5Button.addActionListener((ActionEvent e) -> showApp("Zadanie 5"));
        mainMenu.add(ex5Button);

        JButton ex6Button = new JButton("Zadanie 6 - kalkulator BMI");
        ex6Button.addActionListener((ActionEvent e) -> showApp("Zadanie 6"));
        mainMenu.add(ex6Button);

        JButton ex7Button = new JButton("Zadanie 7 - aplikacja szyfrująca");
        ex7Button.addActionListener((ActionEvent e) -> showApp("Zadanie 7"));
        mainMenu.add(ex7Button);

        JButton ex8Button = new JButton("Zadanie 8 - prosty edytor tekstu");
        ex8Button.addActionListener((ActionEvent e) -> showApp("Zadanie 8"));
        mainMenu.add(ex8Button);

        JButton exitButton = new JButton("Zamknij program");
        exitButton.addActionListener((ActionEvent e) -> System.exit(0));
        mainMenu.add(exitButton);

        menuPanel.add(mainMenu, BorderLayout.CENTER);
        return menuPanel;
    }

    private void initializeTitles()
    {
        titles.put("Menu", "Dominik Kaczmarek 281007 - Podstawy Swing - Menu główne");
        titles.put("Zadanie 1", "Zadanie 1 - zmiana koloru tła");
        titles.put("Zadanie 2", "Zadanie 2 - prosty notatnik");
        titles.put("Zadanie 3", "Zadanie 3 - lista zakupów");
        titles.put("Zadanie 4", "Zadanie 4 - konwerter jednostek");
        titles.put("Zadanie 5", "Zadanie 5 - quiz wiedzy ogólnej");
        titles.put("Zadanie 6", "Zadanie 6 - kalkulator BMI");
        titles.put("Zadanie 7", "Zadanie 7 - aplikacja szyfrująca");
        titles.put("Zadanie 8", "Zadanie 8 - prosty edytor tekstu");
    }

    private void showApp(String appName)
    {
        cardLayout.show(cardPanel, appName);
        mainFrame.setTitle(titles.get(appName));
        switch (appName)
        {
            case "Menu" -> menuBar.setVisible(false);
            case "Zadanie 2" -> updateMenuBarForExercise2();
            case "Zadanie 8" -> updateMenuBarForExercise8();
            default -> updateMenuBarForOthers();
        }
    }

    public void updateMenuBarForOthers()
    {
        menuBar.setVisible(true);
        menuBar.removeAll();
        menuBar.add(backButton);
        menuBar.revalidate();
        menuBar.repaint();
    }

    public void updateMenuBarForExercise2()
    {
        menuBar.setVisible(true);
        menuBar.removeAll();
        menuBar.add(backButton);
        JButton newNote = new JButton("Nowy");
        JButton openNote = new JButton("Otwórz");
        JButton saveNote = new JButton("Zapisz");
        JButton closeNote = new JButton("Zamknij");
        menuBar.add(newNote);
        menuBar.add(openNote);
        menuBar.add(saveNote);
        menuBar.add(closeNote);
        menuBar.revalidate();
        menuBar.repaint();

        Exercise2 exercise2 = (Exercise2) cardPanel.getComponent(2);
        newNote.addActionListener((ActionEvent e) -> {
            exercise2.newNote();
        });
        openNote.addActionListener((ActionEvent e) -> {
            exercise2.openNote();
        });
        saveNote.addActionListener((ActionEvent e) -> {
            exercise2.saveNote();
        });
        closeNote.addActionListener((ActionEvent e) -> {
            exercise2.closeNote();
        });
    }

    public void updateMenuBarForExercise8()
    {
        menuBar.setVisible(true);
        menuBar.removeAll();
        menuBar.add(backButton);
        JButton open = new JButton("Wczytaj");
        JButton save = new JButton("Zapisz");
        JButton saveAs = new JButton("Zapisz jako");
        JButton clear = new JButton("Wyczyść");
        menuBar.add(open);
        menuBar.add(save);
        menuBar.add(saveAs);
        menuBar.add(clear);
        menuBar.revalidate();
        menuBar.repaint();

        Exercise8 exercise8 = (Exercise8) cardPanel.getComponent(8);
        open.addActionListener((ActionEvent e) -> {
            exercise8.open();
        });
        save.addActionListener((ActionEvent e) -> {
            exercise8.save();
        });
        saveAs.addActionListener((ActionEvent e) -> {
            exercise8.saveAs();
        });
        clear.addActionListener((ActionEvent e) -> {
            exercise8.clear();
        });
    }
}