package pl.feen1e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainApp
{
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(MainApp::new);
    }

    public MainApp()
    {
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Dodanie kolejnych aplikacji do cardPanel
        cardPanel.add(createMainView(), "Menu");
        cardPanel.add(new Exercise1(this), "Zadanie 1");
        // TODO dodać resztę aplikacji

        JMenuBar menuBar = new JMenuBar();

        JButton backToMenuButton = new JButton("Powrót do Menu");
        backToMenuButton.addActionListener((ActionEvent e) -> cardLayout.show(cardPanel, "Menu"));
        menuBar.add(backToMenuButton);

        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(cardPanel);
        mainFrame.setVisible(true);

    }

    public JPanel createMainView()
    {
        JPanel mainView = new JPanel();
        mainView.setLayout(new GridLayout(3, 3, 10, 10));

        JLabel welcomeLabel = new JLabel("Wybierz zadanie do uruchomienia:", SwingConstants.CENTER);
        mainView.add(welcomeLabel);

        JButton zad1Button = new JButton("Zadanie 1");
        zad1Button.addActionListener((ActionEvent e) -> cardLayout.show(cardPanel, "Zadanie 1"));
        mainView.add(zad1Button);

        // TODO dodać kolejne zadania
        JButton zad2Button = new JButton("Zadanie 2");
        zad2Button.addActionListener((ActionEvent e) -> cardLayout.show(cardPanel, "Zadanie 2"));
        mainView.add(zad2Button);

        return mainView;
    }

    public void updateMenuBarForMainView()
    {

    }

    public void updateMenuBarForExercise2()
    {

    }

    public void updateMenuBarForExercise8()
    {

    }
}