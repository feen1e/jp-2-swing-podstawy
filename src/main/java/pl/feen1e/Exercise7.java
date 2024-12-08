package pl.feen1e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/*
 * Opracuj aplikację szyfrującą, która pozwala użytkownikowi wprowadzać tekst i wybierać rodzaj szyfrowania,
 * na przykład Cezara lub ROT13. Użyj JTextField do wprowadzania tekstu, JComboBox do wyboru rodzaju szyfrowania
 * i JButton do uruchamiania procesu szyfrowania. Wyświetl zaszyfrowany tekst na interfejsie.
 */

public class Exercise7 extends JPanel
{
    private final JTextField inputField;
    private final JComboBox<String> cipherBox;
    private final JTextField resultField;

    public Exercise7()
    {
        setLayout(new GridLayout(5, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(new JLabel());

        JPanel inputPanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(inputPanel);
        inputPanel.setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        JLabel inputLabel = new JLabel("Podaj tekst do zaszyfrowania:", SwingConstants.CENTER);
        JLabel cipherLabel = new JLabel("Wybierz sposób szyfrowania:", SwingConstants.CENTER);
        inputField = new JTextField();
        cipherBox = new JComboBox<>();
        initializeCipherBox();

        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();
        hGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(inputLabel).addComponent(inputField));
        hGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(cipherLabel).addComponent(cipherBox));
        groupLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(inputLabel).addComponent(cipherLabel));
        vGroup.addGap(20);
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(inputField).addComponent(cipherBox));
        groupLayout.setVerticalGroup(vGroup);

        add(inputPanel);

        JButton encodeButton = new JButton("Zaszyfruj");
        JLabel resultLabel = new JLabel("Wynik:", SwingConstants.RIGHT);
        resultField = new JTextField();
        resultField.setEditable(false);

        JPanel encodePanel = new JPanel();
        encodePanel.setLayout(new BorderLayout());
        encodePanel.setBorder(BorderFactory.createEmptyBorder(15, 200, 15, 200));
        encodePanel.add(encodeButton, BorderLayout.CENTER);
        add(encodePanel);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout(10, 10));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));
        resultPanel.add(resultLabel, BorderLayout.WEST);
        resultPanel.add(resultField, BorderLayout.CENTER);

        add(resultPanel);
        add(new JLabel());

        encodeButton.addActionListener((ActionEvent e) -> encode());
    }

    private void initializeCipherBox()
    {
        cipherBox.addItem("Cezar");
        cipherBox.addItem("ROT13");
        cipherBox.addItem("Atbasz");
    }

    private void encode()
    {
        String inputText = inputField.getText();
        String cipherText = cipherBox.getSelectedItem().toString();
        switch (cipherText)
        {
            case "Cezar" -> caesar(inputText, 3);
            case "ROT13" -> rot13(inputText);
            case "Atbasz" -> atbash(inputText);
        }
    }

    private void caesar(String input, int shift)
    {
        final String ALPHABET = "aąbcćdeęfghijklłmnńoópqrsśtuvwxyzźż";
        final String ALPHABET_UPPER = ALPHABET.toUpperCase();

        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray())
        {
            if (Character.isLowerCase(c))
            {
                result.append(shiftChar(c, shift, ALPHABET));
            }
            else if (Character.isUpperCase(c))
            {
                result.append(shiftChar(c, shift, ALPHABET_UPPER));
            }
            else
            {
                result.append(c);
            }
        }

        resultField.setText(result.toString());
    }

    private void rot13(String inputText)
    {
        caesar(inputText, 13);
    }

    private static char shiftChar(char c, int shift, String alphabet)
    {
        int index = alphabet.indexOf(c);

        if (index == -1)
        {
            return c;
        }

        int newIndex = (index + shift) % alphabet.length();
        if (newIndex < 0)
        {
            newIndex += alphabet.length(); // Obsługa ujemnych przesunięć
        }

        return alphabet.charAt(newIndex);
    }

    private void atbash(String inputText)
    {
        final String ALPHABET = "aąbcćdeęfghijklłmnńoópqrsśtuvwxyzźż";
        final String ALPHABET_UPPER = ALPHABET.toUpperCase();

        StringBuilder result = new StringBuilder();

        for (char c : inputText.toCharArray())
        {
            if (Character.isLowerCase(c))
            {
                result.append(mirrorChar(c, ALPHABET));
            }
            else if (Character.isUpperCase(c))
            {
                result.append(mirrorChar(c, ALPHABET_UPPER));
            }
            else
            {
                result.append(c);
            }
        }

        resultField.setText(result.toString());
    }

    private static char mirrorChar(char c, String alphabet)
    {
        int index = alphabet.indexOf(c);

        if (index == -1)
        {
            return c;
        }
        int mirroredIndex = alphabet.length() - 1 - index;
        return alphabet.charAt(mirroredIndex);
    }

}
