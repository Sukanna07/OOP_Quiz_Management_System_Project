import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizFrame extends JFrame {
    private Quiz quiz;
    private RegisteredStudent student;
    private int currentQuestionIndex = 0;
    private int[] userAnswers;

    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionsGroup;
    private JButton submitBtn;

    public QuizFrame(Quiz quiz, RegisteredStudent student) {
        this.quiz = quiz;
        this.student = student;
        this.userAnswers = new int[quiz.getQuestionCount()];

        setTitle("Quiz System - " + student.getUsername());
        setSize(450, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        questionLabel = new JLabel();
        add(questionLabel);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsGroup = new ButtonGroup();
        optionButtons = new JRadioButton[4];

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionsGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        add(optionsPanel);

        submitBtn = new JButton("Submit Answer");
        add(submitBtn);

        displayQuestion();

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    handleNextQuestion();
                } catch (InvalidOptionException ex) {
                    JOptionPane.showMessageDialog(QuizFrame.this, ex.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void displayQuestion() {
        if (currentQuestionIndex < quiz.getQuestionCount()) {
            Question q = quiz.getQuestions().get(currentQuestionIndex);
            questionLabel.setText("Q" + (currentQuestionIndex + 1) + ": " + q.getQuestionText());

            String[] opts = q.getOptions();
            optionsGroup.clearSelection();

            for (int i = 0; i < 4; i++) {
                if (i < opts.length) {
                    optionButtons[i].setText(opts[i]);
                    optionButtons[i].setVisible(true);
                } else {
                    optionButtons[i].setVisible(false);
                }
            }
        } else {
            showFinalResults();
        }
    }

    private void handleNextQuestion() throws InvalidOptionException {
        int selectedOption = -1;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                selectedOption = i + 1;
                break;
            }
        }

        if (selectedOption == -1) {
            throw new InvalidOptionException("Please select an option before continuing!");
        }

        userAnswers[currentQuestionIndex] = selectedOption;
        currentQuestionIndex++;
        displayQuestion();
    }

    private void showFinalResults() {
        int finalScore = quiz.calculateScore(userAnswers);
        quiz.recordScore(student.getUsername(), finalScore);

        for (JRadioButton btn : optionButtons) {
            btn.setVisible(false);
        }

        questionLabel.setText("Quiz Finished!");
        submitBtn.setVisible(false);

        JOptionPane.showMessageDialog(this,
                "Student: " + student.getUsername() +
                "\nScore: " + finalScore + " / " + quiz.getQuestionCount(),
                "Quiz Results",
                JOptionPane.INFORMATION_MESSAGE);
    }
}