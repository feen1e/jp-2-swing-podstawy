package pl.feen1e;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

/*
 * Stwórz prostą aplikację notatnika, która umożliwia użytkownikowi pisanie i edycję tekstu.
 * Zaimplementuj menu z opcjami takimi jak "Nowy", "Otwórz", "Zapisz" i "Zamknij". Użyj JTextArea do
 * wprowadzania i wyświetlania tekstu. Zadanie polega na obsłudze operacji na plikach i prostym interfejsie notatnika.
 */
public class Exercise2 extends JPanel
{
    private final JTextArea textArea = new JTextArea();

    public Exercise2()
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


    public void newNote()
    {

        if (textArea.isVisible())
        {
            Object[] options = {"Tak", "Nie"};
            int confirmation = JOptionPane.showOptionDialog(
                    this,
                    "Utworzenie nowej notatki wyczyści zawartość pola tekstowego. Utworzyć nową notatkę?",
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
        else
        {
            textArea.setVisible(true);
            textArea.setText("");
        }
    }

    public void openNote()
    {
        textArea.setVisible(true);
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Pliki tekstowe (*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();

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

    public void saveNote()
    {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Pliki tekstowe (*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();

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

    public void closeNote()
    {
        textArea.setVisible(false);
    }
}
