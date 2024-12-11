package pl.feen1e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/*
 * Stwórz prosty kalkulator BMI (Body Mass Index), który pozwala użytkownikowi obliczyć
 * wskaźnik BMI na podstawie wprowadzonych wartości wagi i wzrostu. Użyj JTextField do wprowadzania danych,
 * a po kliknięciu przycisku "Oblicz", wyświetl wynik BMI na interfejsie.
 */
public class Exercise6 extends JPanel
{
    private final JTextField massField;
    private final JTextField heightField;
    private final JTextField resultField;

    public Exercise6()
    {
        setLayout(new GridLayout(6, 1, 10, 50));
        setBorder(BorderFactory.createEmptyBorder(20, -300, 20, 60));
        setFont(new Font("Verdana", Font.PLAIN, 18));

        add(new JLabel());

        JLabel massLabel = new JLabel("Masa [kg]:");
        massLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        massLabel.setFont(this.getFont().deriveFont(Font.BOLD));
        massField = new JTextField();
        massField.setFont(this.getFont());
        JPanel massPanel = new JPanel();
        massPanel.setLayout(new GridLayout(1, 2, 20, 10));
        massPanel.add(massLabel);
        massPanel.add(massField);
        add(massPanel);

        JLabel heightLabel = new JLabel("Wzrost [cm]:");
        heightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        heightLabel.setFont(this.getFont().deriveFont(Font.BOLD));
        heightField = new JTextField();
        heightField.setFont(this.getFont());
        JPanel heightPanel = new JPanel();
        heightPanel.setLayout(new GridLayout(1, 2, 20, 10));
        heightPanel.add(heightLabel);
        heightPanel.add(heightField);
        add(heightPanel);

        JButton calculateButton = new JButton("Oblicz BMI");
        calculateButton.setFont(this.getFont().deriveFont(Font.BOLD));
        JPanel calculatePanel = new JPanel();
        calculatePanel.setLayout(new GridLayout(1, 2, 20, 10));
        calculatePanel.add(new JLabel());
        calculatePanel.add(calculateButton);
        add(calculatePanel);

        JLabel resultLabel = new JLabel("Wynik: ");
        resultLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        resultLabel.setFont(this.getFont().deriveFont(Font.BOLD));
        resultField = new JTextField();
        resultField.setFont(this.getFont());
        resultField.setEditable(false);
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(1, 2, 20, 10));
        resultPanel.add(resultLabel);
        resultPanel.add(resultField);
        add(resultPanel);

        add(new JLabel());

        massField.addActionListener((ActionEvent e) -> calculateBMI());
        heightField.addActionListener((ActionEvent e) -> calculateBMI());
        calculateButton.addActionListener((ActionEvent e) -> calculateBMI());
    }

    private void calculateBMI()
    {
        try
        {
            double mass = Double.parseDouble(massField.getText());
            double height = Double.parseDouble(heightField.getText()) / 100.0;

            double bmi = mass / (height * height);

            resultField.setText(String.format("%.2f", bmi));
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Proszę wprowadzić poprawne liczby.",
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
