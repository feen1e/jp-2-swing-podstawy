package pl.feen1e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Random;

/*
 * Opracuj konwerter jednostek, który pozwala użytkownikowi przeliczać jednostki miar, takie jak
 * długość, masa lub objętość. Użyj JComboBox do wyboru jednostek wejściowych i wyjściowych oraz
 * JTextField do wprowadzania wartości. Umożliw użytkownikowi przeliczanie jednostek i wyświetl wynik na interfejsie.
 */
public class Exercise4 extends JPanel
{
    private final JComboBox<String> categoryComboBox;
    private final JComboBox<String> inputUnitComboBox;
    private final JComboBox<String> outputUnitComboBox;
    private final JTextField inputField;
    private final JTextField outputField;
    private final JButton convertButton;

    private final HashMap<String, HashMap<String, Double>> conversionRates;

    public Exercise4()
    {
        setLayout(new GridLayout(6, 2, 40, 20));
        setBorder(BorderFactory.createEmptyBorder(20, -160, 20, 20));
        setFont(new Font("Verdana", Font.PLAIN, 16));
        conversionRates = initializeConversionRates();

        JLabel categoryLabel = new JLabel("Kategoria:");
        categoryLabel.setFont(this.getFont().deriveFont(Font.BOLD));
        categoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        categoryComboBox = new JComboBox<>(new String[]{" Długość ", " Masa ", " Objętość "});
        categoryComboBox.setFont(this.getFont());
        add(categoryLabel);
        add(categoryComboBox);

        JLabel inputUnitLabel = new JLabel("Jednostka wejściowa:");
        inputUnitLabel.setFont(this.getFont().deriveFont(Font.BOLD));
        inputUnitLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        inputUnitComboBox = new JComboBox<>();
        inputUnitComboBox.setFont(this.getFont());
        add(inputUnitLabel);
        add(inputUnitComboBox);

        JLabel outputUnitLabel = new JLabel("Jednostka wyjściowa:");
        outputUnitLabel.setFont(this.getFont().deriveFont(Font.BOLD));
        outputUnitLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        outputUnitComboBox = new JComboBox<>();
        outputUnitComboBox.setFont(this.getFont());
        add(outputUnitLabel);
        add(outputUnitComboBox);

        JLabel inputLabel = new JLabel("Wartość wejściowa:");
        inputLabel.setFont(this.getFont().deriveFont(Font.BOLD));
        inputLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        inputField = new JTextField();
        inputField.setFont(this.getFont());
        inputField.setMargin(new Insets(10, 10, 10, 10));
        inputField.addActionListener((ActionEvent e) -> {
            convertUnits();
        });

        add(inputLabel);
        add(inputField);

        JLabel outputLabel = new JLabel("Wynik:");
        outputLabel.setFont(this.getFont().deriveFont(Font.BOLD));
        outputLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        outputField = new JTextField();
        outputField.setFont(this.getFont());
        outputField.setEditable(false);
        outputField.setMargin(new Insets(10, 10, 10, 10));
        add(outputLabel);
        add(outputField);

        convertButton = new JButton("Przelicz");
        convertButton.setFont(this.getFont());
        add(new JLabel());
        add(convertButton);

        categoryComboBox.addActionListener(e -> updateUnitComboBoxes());
        convertButton.addActionListener(e -> convertUnits());
        updateUnitComboBoxes();

    }

    private HashMap<String, HashMap<String, Double>> initializeConversionRates()
    {
        HashMap<String, HashMap<String, Double>> rates = new HashMap<>();

        // Długość
        HashMap<String, Double> lengthRates = new HashMap<>();
        lengthRates.put(" Metry ", 1.0);
        lengthRates.put(" Centymetry ", 100.0);
        lengthRates.put(" Milimetry ", 1000.0);
        lengthRates.put(" Kilometry ", 0.001);
        lengthRates.put(" Cale ", 39.37);
        rates.put(" Długość ", lengthRates);

        // Masa
        HashMap<String, Double> massRates = new HashMap<>();
        massRates.put(" Kilogramy ", 1.0);
        massRates.put(" Gramy ", 1000.0);
        massRates.put(" Tony ", 0.001);
        massRates.put(" Funty ", 2.20462);
        massRates.put(" Uncje ", 35.274);
        rates.put(" Masa ", massRates);

        // Objętość
        HashMap<String, Double> volumeRates = new HashMap<>();
        volumeRates.put(" Litry", 1.0);
        volumeRates.put(" Mililitry", 1000.0);
        volumeRates.put(" Kubiki ", 0.001);
        volumeRates.put(" Galony", 0.264172);
        volumeRates.put(" Pinty", 2.11338);
        rates.put(" Objętość ", volumeRates);

        return rates;
    }

    private void updateUnitComboBoxes()
    {
        String category = (String) categoryComboBox.getSelectedItem();
        HashMap<String, Double> units = conversionRates.get(category);

        if (units != null)
        {
            inputUnitComboBox.removeAllItems();
            outputUnitComboBox.removeAllItems();
            for (String unit : units.keySet())
            {
                inputUnitComboBox.addItem(unit);
                outputUnitComboBox.addItem(unit);
            }
            inputUnitComboBox.setSelectedIndex(new Random().nextInt(inputUnitComboBox.getItemCount()));
            outputUnitComboBox.setSelectedIndex(new Random().nextInt(outputUnitComboBox.getItemCount()));
        }
    }

    private void convertUnits()
    {
        try
        {
            double inputValue = Double.parseDouble(inputField.getText());
            String inputUnit = (String) inputUnitComboBox.getSelectedItem();
            String outputUnit = (String) outputUnitComboBox.getSelectedItem();
            String category = (String) categoryComboBox.getSelectedItem();

            if (inputUnit != null && outputUnit != null && category != null)
            {
                HashMap<String, Double> units = conversionRates.get(category);
                double inputRate = units.get(inputUnit);
                double outputRate = units.get(outputUnit);

                double result = inputValue * (outputRate / inputRate);
                outputField.setText(String.format("%.4f", result));
            }
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Proszę wprowadzić poprawną liczbę.",
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}