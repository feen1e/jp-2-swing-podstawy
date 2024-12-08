package pl.feen1e;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

/*
 * Opracuj prosty edytor tekstu w interfejsie Swing. Edytor powinien zawierać pole tekstowe, przycisk "Zapisz" i przycisk "Wczytaj".
 * Wymagane funkcje edytora:
 *  * Pole Tekstowe: Obszar tekstowy, w którym użytkownik może wprowadzać tekst.
 *  * Przycisk "Zapisz": Po kliknięciu tego przycisku zawartość pola tekstowego powinna być zapisana do pliku na dysku.
 *  * Przycisk "Wczytaj": Po kliknięciu tego przycisku użytkownik powinien móc wybrać plik z dysku, a jego zawartość
 *  *   powinna być wczytana i wyświetlona w polu tekstowym.
 */
public class Exercise8 extends JPanel
{
    private final JTextArea textArea = new JTextArea();
    private File selectedFile;

    public Exercise8()
    {
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setVisible(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void open()
    {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Pliki tekstowe (*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile)))
            {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null)
                {
                    content.append(line).append("\n");
                }
                textArea.setText(content.toString());
            }
            catch (IOException e)
            {
                JOptionPane.showMessageDialog(this, "Błąd podczas ładowania pliku", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void save()
    {
        if (selectedFile == null)
        {
            saveAs();
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile)))
        {
            writer.write(textArea.getText());
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(this, "Błąd podczas zapisywania pliku", "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveAs()
    {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Pliki tekstowe (*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = fileChooser.getSelectedFile();

            if (!selectedFile.getName().endsWith(".txt"))
            {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile)))
            {
                writer.write(textArea.getText());
            }
            catch (IOException e)
            {
                JOptionPane.showMessageDialog(this, "Błąd podczas zapisywania pliku", "Błąd",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void clear()
    {
        Object[] options = {"Tak", "Nie"};
        int confirmation = JOptionPane.showOptionDialog(
                this,
                "Czy na pewno chcesz wyczyścić cały tekst?",
                "Potwierdzenie",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]
        );
        if (confirmation == JOptionPane.YES_OPTION)
        {
            textArea.setText("");
        }
    }
}
