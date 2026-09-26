import java.util.ArrayList;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        RegisteredStudent student = new RegisteredStudent("Sukanna", "1234", "S001", "CSE", true, 0);

        String[] options1 = {"Java", "HTML", "CSS", "SQL"};
        String[] options2 = {"extends", "import", "package", "static"};

        Question q1 = new Question("Which language is mainly used for OOP?", options1, 1);
        Question q2 = new Question("Which keyword is used for inheritance in Java?", options2, 1);

        ArrayList<Question> questionsList = new ArrayList<>();
        questionsList.add(q1);
        questionsList.add(q2);

        Quiz quiz = new Quiz(questionsList);

        SwingUtilities.invokeLater(() -> {
            QuizFrame frame = new QuizFrame(quiz, student);
            frame.setVisible(true);
        });
    }
}
