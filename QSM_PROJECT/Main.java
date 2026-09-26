import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
       
        RegisteredStudent s1 = new RegisteredStudent("Sukanna", "1234", "S001", "CSE", "1st", true, 0);
        RegisteredStudent s2 = new RegisteredStudent("Rohit", "5678", "S002", "CSE", "1st", true, 0);
        RegisteredStudent s3 = new RegisteredStudent("Mitu", "5671", "S003", "CSE", "1st", false, 0);

        ArrayList<RegisteredStudent> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);

        // 2. Question create 
        String[] options1 = {"Java", "HTML", "CSS", "SQL"};
        String[] options2 = {"extends", "import", "package", "static"};

        Question q1 = new Question("Which language is mainly used for OOP?", options1, 1);
        Question q2 = new Question("Which keyword is used for inheritance in Java?", options2, 1);

        ArrayList<Question> questionsList = new ArrayList<>();
        questionsList.add(q1);
        questionsList.add(q2);

        Quiz quiz = new Quiz(questionsList);

        // 3. Popup dialog threshold
        String[] studentNames = {s1.getUsername(), s2.getUsername(), s3.getUsername()};
        
        String selectedName = (String) JOptionPane.showInputDialog(
                null,
                "Select Student to Start Quiz:",
                "Student Login",
                JOptionPane.QUESTION_MESSAGE,
                null,
                studentNames,
                studentNames[0]
        );

        RegisteredStudent currentStudent = null;
        if (selectedName != null) {
            for (RegisteredStudent st : studentList) {
                if (st.getUsername().equals(selectedName)) {
                    currentStudent = st;
                    break;
                }
            }
        } else {
            System.out.println("No student selected. Exiting...");
            System.exit(0);
        }

        // --- REGISTRATION STATUS CHECK ---
        if (!currentStudent.isRegistrationStatus()) {
            JOptionPane.showMessageDialog(
                    null,
                    currentStudent.getUsername() + " is not registered for the quiz!",
                    "Access Denied",
                    JOptionPane.ERROR_MESSAGE
            );
            System.out.println(currentStudent.getUsername() + "'s registration status is false. Cannot take the exam.");
            return; // Exam UI won't start
        }

        //for registered student
        currentStudent.viewProfile();
        currentStudent.registerForQuiz();

        RegisteredStudent finalStudent = currentStudent;
        SwingUtilities.invokeLater(() -> {
            QuizFrame frame = new QuizFrame(quiz, finalStudent);
            frame.setVisible(true);
        });
    }
}
