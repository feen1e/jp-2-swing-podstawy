package pl.feen1e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainApp
{
    private final JFrame mainFrame;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final JMenuBar menuBar;
    private final JButton backButton;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(MainApp::new);
    }

    public MainApp()
    {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);

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
        backButton.addActionListener((ActionEvent e) -> showMenu());

        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(cardPanel);
        mainFrame.setVisible(true);

    }

    public JPanel createMenu()
    {
        JPanel menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menuPanel.setLayout(new BorderLayout(5, 5));
        JLabel title = new JLabel("Wybierz zadanie do uruchomienia: ", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.PLAIN, 18));
        title.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        menuPanel.add(title, BorderLayout.NORTH);
        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new GridLayout(3, 3, 10, 10));

        JButton ex1Button = new JButton("Zadanie 1 - zmiana koloru tła");
        ex1Button.addActionListener((ActionEvent e) -> showApp("Zadanie 1"));
        mainMenu.add(ex1Button);

        JButton ex2Button = new JButton("Zadanie 2 - prosty notatnik");
        ex2Button.addActionListener((ActionEvent e) -> showApp("Zadanie 2"));
        mainMenu.add(ex2Button);

        JButton ex3Button = new JButton("Zadanie 3 - lista zakupów");
        ex3Button.addActionListener((ActionEvent e) -> showApp("Zadanie 3"));
        mainMenu.add(ex3Button);
        // TODO dodać tytuły reszty zadań
        JButton ex4Button = new JButton("Zadanie 4");
        ex4Button.addActionListener((ActionEvent e) -> showApp("Zadanie 4"));
        mainMenu.add(ex4Button);

        JButton ex5Button = new JButton("Zadanie 5");
        ex5Button.addActionListener((ActionEvent e) -> showApp("Zadanie 5"));
        mainMenu.add(ex5Button);

        JButton ex6Button = new JButton("Zadanie 6");
        ex6Button.addActionListener((ActionEvent e) -> showApp("Zadanie 6"));
        mainMenu.add(ex6Button);

        JButton ex7Button = new JButton("Zadanie 7");
        ex7Button.addActionListener((ActionEvent e) -> showApp("Zadanie 7"));
        mainMenu.add(ex7Button);

        JButton ex8Button = new JButton("Zadanie 8");
        ex8Button.addActionListener((ActionEvent e) -> showApp("Zadanie 8"));
        mainMenu.add(ex8Button);

        JButton exitButton = new JButton("Zamknij program");
        exitButton.addActionListener((ActionEvent e) -> System.exit(0));
        mainMenu.add(exitButton);

        menuPanel.add(mainMenu, BorderLayout.CENTER);
        return menuPanel;
    }

    private void showMenu()
    {
        cardLayout.show(cardPanel, "Menu");
        menuBar.setVisible(false);
    }

    private void showApp(String appName)
    {
        cardLayout.show(cardPanel, appName);
        switch (appName)
        {
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
        // TODO zmienić !!!
        updateMenuBarForOthers();
    }
}