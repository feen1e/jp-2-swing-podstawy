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
    private int resultPoints = 0;

    public Exercise5()
    {
        initializeQuestions();
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(cardPanel, BorderLayout.CENTER);

        for (HashMap.Entry<String, String[]> entry : questions.entrySet())
        {
            questionPanels.add(new Question(entry.getValue()));
            correctAnswers.add(entry.getKey());
        }

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel result = new JLabel();
        JButton resetButton = getResetButton();

        resultsPanel.add(result, BorderLayout.CENTER);
        resultsPanel.add(resetButton, BorderLayout.SOUTH);

        for (int i = 0; i < questionPanels.size(); i++)
        {

            JButton nextButton = getNextButton(result);

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout(20, 20));
            panel.add(questionPanels.get(i), BorderLayout.CENTER);
            panel.add(nextButton, BorderLayout.SOUTH);
            cardPanel.add(panel, "Pytanie " + (i + 1));
        }

        cardPanel.add(resultsPanel, "Wynik");
    }

    private JButton getNextButton(JLabel result)
    {
        JButton nextButton = new JButton("Dalej");
        nextButton.addActionListener((ActionEvent e) -> {
            Question q = (Question) cardPanel.getComponent(currentQuestion).getComponentAt(10, 10);
            givenAnswers.add(q.givenAnswer);
            cardLayout.next(cardPanel);
            currentQuestion += 1;
            if (currentQuestion == questions.size())
            {
                System.out.println(correctAnswers);
                System.out.println(givenAnswers);
                resultPoints = calculateResult();
                result.setText("Odpowiedziano poprawnie na " + resultPoints + " z " + questionPanels.size() + " " +
                        "pytań." + (resultPoints == correctAnswers.size() ? "Gratulacje!" : ""));
                result.repaint();
            }
        });
        return nextButton;
    }

    private JButton getResetButton()
    {
        JButton resetButton = new JButton("Zacznij rozwiązywać ponownie");
        resetButton.addActionListener((ActionEvent e) -> {
            currentQuestion = 0;
            resultPoints = 0;
            givenAnswers.clear();
            cardLayout.show(cardPanel, "Pytanie 1");
            for (int i = 0; i < questionPanels.size(); i++)
            {
                Question q = (Question) cardPanel.getComponent(i).getComponentAt(10, 10);
                q.resetQuestion();
            }
        });
        return resetButton;
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

    protected static class Question extends JPanel
    {
        private final JButton answerUL; // Upper left
        private final JButton answerLL; // Lower left
        private final JButton answerUR; // Upper right
        private final JButton answerLR; // Lower right
        protected String givenAnswer = "";

        protected Question(String[] questionAndAnswers)
        {
            JLabel question = new JLabel(questionAndAnswers[0]);
            this.answerUL = new JButton(questionAndAnswers[1]);
            this.answerLL = new JButton(questionAndAnswers[2]);
            this.answerUR = new JButton(questionAndAnswers[3]);
            this.answerLR = new JButton((questionAndAnswers[4]));

            setLayout(new GridLayout(2, 1, 20, 50));
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

        private void resetQuestion()
        {
            answerUL.setEnabled(true);
            answerLL.setEnabled(true);
            answerUR.setEnabled(true);
            answerLR.setEnabled(true);
            givenAnswer = "";
        }
    }
}
