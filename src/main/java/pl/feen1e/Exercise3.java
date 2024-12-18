package pl.feen1e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/*
 * Utwórz prostą aplikację do zarządzania listą zakupów. Interfejs powinien zawierać pole tekstowe,
 * przycisk "Dodaj", i listę zakupów. Po wprowadzeniu nazwy produktu w polu tekstowym i kliknięciu "Dodaj",
 * produkt powinien być widoczny na liście zakupów. Użytkownik powinien mieć możliwość usuwania produktów z listy.
 */
public class Exercise3 extends JPanel
{
    private final JList<String> shoppingList;
    private final DefaultListModel<String> listModel;
    private final JTextField inputField = new JTextField();

    public Exercise3()
    {
        listModel = new DefaultListModel<>();
        shoppingList = new JList<>(listModel);

        shoppingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        shoppingList.setFixedCellHeight(20);
        shoppingList.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));

        inputField.addActionListener(this::addToList);

        inputField.setMargin(new Insets(5, 10, 5, 10));

        JButton addButton = new JButton("Dodaj");
        addButton.addActionListener(this::addToList);

        JButton removeButton = new JButton("Usuń");
        removeButton.addActionListener(this::removeFromList);
        removeButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                    .put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");
        removeButton.getActionMap().put("delete", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                removeButton.doClick();
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(inputField, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        inputPanel.add(buttonPanel, BorderLayout.EAST);

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(inputPanel, BorderLayout.NORTH);
        JScrollPane shoppingListScrollPane = new JScrollPane(shoppingList);
        add(shoppingListScrollPane, BorderLayout.CENTER);
    }

    private void removeFromList(ActionEvent e)
    {
        if (!shoppingList.isSelectionEmpty())
        {
            int selected = shoppingList.getSelectedIndex();
            listModel.remove(selected);
        }
        else
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Nie wybrano elementu do usunięcia.",
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void addToList(ActionEvent e)
    {
        String newItem = inputField.getText();
        if (newItem != null && !newItem.isEmpty())
        {
            listModel.addElement("•   " + newItem);
        }
        inputField.setText("");
    }

}
