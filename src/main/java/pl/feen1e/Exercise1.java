package pl.feen1e;

import javax.swing.*;
import java.awt.*;

/*
 * Stwórz interfejs GUI z JFrame, który zawiera komponenty: JTextField, JButton i JPanel.
 * Użytkownik powinien móc wprowadzić nazwę koloru w JTextField, a po kliknięciu przycisku,
 * kolor tła JPanel powinien zmieniać się na podstawie wprowadzonej nazwy koloru
 * (np. "czerwony", "zielony", "niebieski"). Upewnij się, że interfejs reaguje na nieprawidłowe
 * nazwy kolorów i informuje użytkownika o błędzie.
 */
public class Exercise1 extends JPanel
{
    public Exercise1(MainApp mainApp)
    {
        setLayout(new BorderLayout());
        add(new JLabel("Zadanie 1"), BorderLayout.NORTH);
        add(new JLabel("SJKHGFSHJAKFGSDKHJFG"), BorderLayout.SOUTH);
    }
}
