import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Graphical UI component built using Java Swing.
 * Renders questions dynamically, handles user input events, and displays final score pop-ups.
 */
public class QuizFrame extends JFrame {
    private Quiz quiz;
    private RegisteredStudent student;
    private int currentQuestionIndex = 0;
    private int[] userAnswers; // Stores user-selected option per question

    // Swing UI Components
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionsGroup; // Groups radio buttons so only one can be selected
    private JButton submitBtn;

    public QuizFrame(Quiz quiz, RegisteredStudent student) {
        this.quiz = quiz;
        this.student = student;
        this.userAnswers = new int[quiz.getQuestionCount()];

        // Configure frame window settings
        setTitle("Quiz System - " + student.getUsername());
        setSize(450, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window on screen
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        // Question Title Label
        questionLabel = new JLabel();
        add(questionLabel);

        // Panel grouping radio option buttons vertically
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

        // Submit Action Button
        submitBtn = new JButton("Submit Answer");
        add(submitBtn);

        // Load initial question into UI components
        displayQuestion();

        // Register button event click handler
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    handleNextQuestion();
                } catch (InvalidOptionException ex) {
                    // Show dialog popup when custom exception is thrown
                    JOptionPane.showMessageDialog(QuizFrame.this, ex.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    // Loads current question text and option labels into UI elements
    private void displayQuestion() {
        if (currentQuestionIndex < quiz.getQuestionCount()) {
            Question q = quiz.getQuestions().get(currentQuestionIndex);
            questionLabel.setText("Q" + (currentQuestionIndex + 1) + ": " + q.getQuestionText());

            String[] opts = q.getOptions();
            optionsGroup.clearSelection(); // Reset selections for new question

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

    // Reads user selection and validates input before proceeding
    private void handleNextQuestion() throws InvalidOptionException {
        int selectedOption = -1;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                selectedOption = i + 1; // Convert index to 1-based option
                break;
            }
        }

        // Throw exception if no radio button was selected by user
        if (selectedOption == -1) {
            throw new InvalidOptionException("Please select an option before continuing!");
        }

        // Record selection and navigate to next index
        userAnswers[currentQuestionIndex] = selectedOption;
        currentQuestionIndex++;
        displayQuestion();
    }

    // Displays score completion message and updates user score in HashMap
    private void showFinalResults() {
        int finalScore = quiz.calculateScore(userAnswers);
        quiz.recordScore(student.getUsername(), finalScore);
        quiz.displayResult(finalScore); // Calls the updated interface method

        // Hide input radio buttons when quiz finishes
        for (JRadioButton btn : optionButtons) {
            btn.setVisible(false);
        }

        questionLabel.setText("Quiz Finished!");
        submitBtn.setVisible(false);

        // Display results modal alert box
        JOptionPane.showMessageDialog(this,
                "Student: " + student.getUsername() +
                "\nScore: " + finalScore + " / " + quiz.getQuestionCount(),
                "Quiz Results",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
