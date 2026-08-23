import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
       
        // 1. Create Admin
        Admin admin = new Admin("admin01", "1234", "A001");
        admin.addQuestion();
        admin.viewQuestions();

        // 2. Student Information 
        System.out.print("Enter Student Username: ");
        String studentUsername = input.nextLine();

        System.out.print("Enter Student Password: ");
        String studentPassword = input.nextLine();

        System.out.print("Enter Student ID: ");
        String studentId = input.nextLine();

        System.out.print("Enter Department: ");
        String department = input.nextLine();

        //  RegisteredStudent constructor parameters
        RegisteredStudent student = new RegisteredStudent(
                studentUsername,
                studentPassword,
                studentId,
                department,
                true,
                0
        );

        student.login();
        student.viewProfile();
        student.registerForQuiz();

        // 3. Questions
        String[] options1 = {"Java", "HTML", "CSS", "SQL"};
        String[] options2 = {"extends", "import", "package", "static"};

        Question q1 = new Question("Which language is mainly used for OOP?", options1, 1);
        Question q2 = new Question("Which keyword is used for inheritance in Java?", options2, 1);

        // 4. Create Quiz
        Question[] questions = {q1, q2};
        Quiz quiz = new Quiz(questions);

        // 5. Start Quiz
        student.startQuiz();
        quiz.loadQuestions();

        // 6. Student Answers
        int[] answers = new int[quiz.getQuestionCount()];
        System.out.println("\n===== ANSWER THE QUIZ =====");

        for (int i = 0; i < quiz.getQuestionCount(); i++) {
            System.out.print("Enter your answer for Question " + (i + 1) + " (1-4): ");
            answers[i] = input.nextInt();
        }

        // 7. Calculate Score
        int score = quiz.calculateScore(answers);

        // 8. Display Result
        System.out.println("\n===== RESULT =====");
        System.out.println("Student: " + student.getUsername());
        System.out.println("Score: " + score + "/" + quiz.getQuestionCount());

        // 9. Submit And Logout
        student.submitAnswers();
        student.logout();
        input.close();
    }
}
