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
        cardPanel.add(new JPanel(), "Zadanie 2");
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

        JButton zad1Button = new JButton("Zadanie 1");
        zad1Button.addActionListener((ActionEvent e) -> cardLayout.show(cardPanel, "Zadanie 1"));
        mainMenu.add(zad1Button);

        // TODO dodać kolejne zadania
        JButton zad2Button = new JButton("Zadanie 2");
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
    }

    public void updateMenuBarForExercise2()
    {
        menuBar.removeAll();
        menuBar.add(backButton);
        menuBar.add(new JButton("Nowy"));
    }

    public void updateMenuBarForExercise8()
    {

    }
}