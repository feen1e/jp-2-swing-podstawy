package pl.feen1e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

/*
 * Stwórz interfejs GUI z JFrame, który zawiera komponenty: JTextField, JButton i JPanel.
 * Użytkownik powinien móc wprowadzić nazwę koloru w JTextField, a po kliknięciu przycisku,
 * kolor tła JPanel powinien zmieniać się na podstawie wprowadzonej nazwy koloru
 * (np. "czerwony", "zielony", "niebieski"). Upewnij się, że interfejs reaguje na nieprawidłowe
 * nazwy kolorów i informuje użytkownika o błędzie.
 */
public class Exercise1 extends JPanel
{
    private final JTextField colourField;
    private final JButton colourButton;
    private final JPanel colourPanel;

    private final HashMap<String, Color> colourMap;

    public Exercise1()
    {
        setLayout(new BorderLayout(10, 10));

        colourMap = new HashMap<>();
        colourMap.put("czerwony", Color.RED);
        colourMap.put("zielony", Color.GREEN);
        colourMap.put("niebieski", Color.BLUE);
        colourMap.put("żółty", Color.YELLOW);
        colourMap.put("czarny", Color.BLACK);
        colourMap.put("biały", Color.WHITE);
        colourMap.put("pomarańczowy", Color.ORANGE);
        colourMap.put("szary", Color.GRAY);
        colourMap.put("różowy", Color.PINK);
        colourMap.put("magenta", Color.MAGENTA);
        colourMap.put("cyjan", Color.CYAN);

        colourField = new JTextField();
        colourField.setToolTipText("Wprowadź nazwę koloru: ");
        colourField.addActionListener(this::changeColour);

        colourButton = new JButton("Zmień kolor");
        colourButton.addActionListener(this::changeColour);

        colourPanel = new JPanel();
        colourPanel.setBackground(Color.WHITE);

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.add(new JLabel("Wpisz nazwę koloru na który ma zostać zmienione tło: ", SwingConstants.CENTER),
                BorderLayout.NORTH);
        inputPanel.add(colourField, BorderLayout.CENTER);
        inputPanel.add(colourButton, BorderLayout.EAST);

        // Dodanie komponentów do głównego panelu
        add(inputPanel, BorderLayout.NORTH);
        add(colourPanel, BorderLayout.CENTER);

    }

    private void changeColour(ActionEvent e)
    {
        String colorName = colourField.getText().toLowerCase().trim();
        if (colourMap.containsKey(colorName)) {
            colourPanel.setBackground(colourMap.get(colorName));
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Brak koloru: " + colorName,
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
