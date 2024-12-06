package pl.feen1e;


import javax.swing.*;
import java.awt.*;

/*
 * Stwórz prostą aplikację notatnika, która umożliwia użytkownikowi pisanie i edycję tekstu.
 * Zaimplementuj menu z opcjami takimi jak "Nowy", "Otwórz", "Zapisz" i "Zamknij". Użyj JTextArea do
 * wprowadzania i wyświetlania tekstu. Zadanie polega na obsłudze operacji na plikach i prostym interfejsie notatnika.
 */
public class Exercise2 extends JPanel
{
    private final JTextArea textArea = new JTextArea();

    public Exercise2(MainApp mainApp)
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
        textArea.setText("");
    }
}
