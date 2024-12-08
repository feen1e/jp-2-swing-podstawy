package pl.feen1e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Stwórz prostą aplikację do quizu wiedzy ogólnej. Wprowadź kilka pytań i odpowiedzi.
 * Interfejs powinien zawierać JLabel z pytaniem, przyciski z odpowiedziami i pole do wyświetlania wyniku.
 * Po wybraniu odpowiedzi przez użytkownika aplikacja powinna ocenić poprawność odpowiedzi i wyświetlić wynik.
 */
public class Exercise5 extends JPanel
{
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);
    private final HashMap<String, String[]> questions = new HashMap<>();
    private final ArrayList<JPanel> questionPanels = new ArrayList<>();
    private final ArrayList<String> correctAnswers = new ArrayList<>();
    private final ArrayList<String> givenAnswers = new ArrayList<>();
    private int currentQuestion = 0;

    public Exercise5()
    {
        initializeQuestions();
        setLayout(new BorderLayout(10, 10));
        add(cardPanel, BorderLayout.CENTER);

        for (HashMap.Entry<String, String[]> entry : questions.entrySet())
        {
            questionPanels.add(new Question(entry.getValue()));
            correctAnswers.add(entry.getKey());
        }

        for (int i = 0; i < questionPanels.size(); i++)
        {
            cardPanel.add(questionPanels.get(i), "Pytanie " + (i + 1));
        }

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BorderLayout(20, 20));
        int resultPoints = calculateResult();
        JLabel result =
                new JLabel("Odpowiedziano poprawnie na " + resultPoints + " pytań." + (resultPoints == correctAnswers.size() ? "Gratulacje!" : ""));

        JButton nextButton = new JButton("Dalej");
        nextButton.addActionListener((ActionEvent e) -> {
            Question q = (Question) cardPanel.getComponent(currentQuestion);
            givenAnswers.add(q.givenAnswer);
            cardLayout.next(cardPanel);
            currentQuestion += 1;
        });

        add(nextButton, BorderLayout.SOUTH);

    }

    private int calculateResult()
    {
        int resultPoints = 0;

        for (int i = 0; i < correctAnswers.size(); i++)
        {
            if (correctAnswers.get(i).equals(givenAnswers.get(i)))
            {
                resultPoints += 1;
            }
        }

        return resultPoints;
    }

    private void initializeQuestions()
    {
        questions.put("Tlen",
                new String[]{"Który pierwiastek chemiczny ma symbol 'O'?", "Tlen", "Wodór", "Azot", "Hel"});
        questions.put("Paryż", new String[]{"Które miasto jest stolicą Francji?", "Londyn", "Berlin", "Paryż", "Rzym"});
        questions.put("3.14",
                new String[]{"Ile wynosi liczba Pi zaokrąglona do dwóch miejsc po przecinku?", "3.12", "3.14", "3.16", "3.18"});
        questions.put("Ocean Spokojny",
                new String[]{"Jak nazywa się największy ocean na Ziemi?", "Ocean Atlantycki", "Ocean Indyjski", "Ocean Arktyczny", "Ocean Spokojny"});
        questions.put("Thomas Edison",
                new String[]{"Który wynalazca jest znany z opracowania żarówki elektrycznej?", "Nikola Tesla", "Thomas Edison", "Alexander Bell", "Albert Einstein"});
        questions.put("Chiński",
                new String[]{"Który język jest najczęściej używany na świecie?", "Angielski", "Chiński", "Hiszpański", "Hinduski"});
        questions.put("Jowisz",
                new String[]{"Jak nazywa się największa planeta w Układzie Słonecznym?", "Ziemia", "Mars", "Jowisz", "Saturn"});
        questions.put("Australia",
                new String[]{"Który kontynent jest najmniejszy pod względem powierzchni?", "Antarktyda", "Europa", "Australia", "Afryka"});
        questions.put("Sherlock Holmes",
                new String[]{"Która postać literacka była detektywem w książkach Sir Arthura Conan Doyle’a?", "Hercule Poirot", "Sherlock Holmes", "Philip Marlowe", "James Bond"});
        questions.put("1969",
                new String[]{"W którym roku człowiek pierwszy raz stanął na Księżycu?", "1965", "1967", "1969", "1971"});
    }

    protected class Question extends JPanel
    {
        private final JLabel question;
        private final JButton answerUL; // Upper left
        private final JButton answerLL; // Lower left
        private final JButton answerUR; // Upper right
        private final JButton answerLR; // Lower right
        protected String givenAnswer = "";

        protected Question(String[] questionAndAnswers)
        {
            this.question = new JLabel(questionAndAnswers[0]);
            this.answerUL = new JButton(questionAndAnswers[1]);
            this.answerLL = new JButton(questionAndAnswers[2]);
            this.answerUR = new JButton(questionAndAnswers[3]);
            this.answerLR = new JButton((questionAndAnswers[4]));

            setLayout(new GridLayout(2, 1, 10, 50));
            add(question);

            JPanel answersPanel = new JPanel();
            answersPanel.setLayout(new GridLayout(2, 2, 30, 30));
            answersPanel.add(answerUL);
            answersPanel.add(answerLL);
            answersPanel.add(answerUR);
            answersPanel.add(answerLR);

            add(answersPanel);

            answerUL.addActionListener((ActionEvent e) -> {
                this.givenAnswer = answerUL.getText();
                blockAllButtons();
            });
            answerLL.addActionListener((ActionEvent e) -> {
                this.givenAnswer = answerLL.getText();
                blockAllButtons();
            });
            answerUR.addActionListener((ActionEvent e) -> {
                this.givenAnswer = answerUR.getText();
                blockAllButtons();
            });
            answerLR.addActionListener((ActionEvent e) -> {
                this.givenAnswer = answerLR.getText();
                blockAllButtons();
            });
        }

        private void blockAllButtons()
        {
            answerUL.setEnabled(false);
            answerLL.setEnabled(false);
            answerUR.setEnabled(false);
            answerLR.setEnabled(false);
        }
    }
}
