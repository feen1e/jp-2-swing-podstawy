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
        // TODO dodać resztę aplikacji

        menuBar = new JMenuBar();

        backButton = new JButton("Powrót do Menu");
        backButton.addActionListener((ActionEvent e) -> showMenu());
        menuBar.add(backButton);

        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(cardPanel);
        mainFrame.setVisible(true);

    }

    public JPanel createMenu()
    {
        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new GridLayout(3, 3, 10, 10));

        JLabel welcomeLabel = new JLabel("Wybierz zadanie do uruchomienia:", SwingConstants.CENTER);
        mainMenu.add(welcomeLabel);

        JButton zad1Button = new JButton("Zadanie 1 - zmiana koloru tła");
        zad1Button.addActionListener((ActionEvent e) -> showApp("Zadanie 1"));
        mainMenu.add(zad1Button);

        // TODO dodać kolejne zadania
        JButton zad2Button = new JButton("Zadanie 2 - prosty notatnik");
        zad2Button.addActionListener((ActionEvent e) -> showApp("Zadanie 2"));
        mainMenu.add(zad2Button);

        return mainMenu;
    }

    private void showMenu()
    {
        cardLayout.show(cardPanel, "Menu");
        updateMenuBarForMenu();
    }

    private void showApp(String appName)
    {
        cardLayout.show(cardPanel, appName);
        switch (appName)
        {
            case "Zadanie 2" -> updateMenuBarForExercise2();
            case "Zadanie 8" -> updateMenuBarForExercise8();
        }
    }

    public void updateMenuBarForMenu()
    {
        menuBar.removeAll();
        menuBar.add(backButton);
        menuBar.revalidate();
        menuBar.repaint();
    }

    public void updateMenuBarForExercise2()
    {
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

    }
}