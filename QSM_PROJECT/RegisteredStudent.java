/**
 * Registered student implementing QuizOperation interface contracts.
 */
public class RegisteredStudent extends Student implements QuizOperation {

    private boolean registrationStatus;
    private int quizAttemptCount;

    public RegisteredStudent(String username, String password,
                             String studentId, String department,
                             String semester,
                             boolean registrationStatus,
                             int quizAttemptCount) {

        super(username, password, studentId, department, semester);
        this.registrationStatus = registrationStatus;
        this.quizAttemptCount = quizAttemptCount;
    }

    public boolean isRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(boolean registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public int getQuizAttemptCount() {
        return quizAttemptCount;
    }

    public void setQuizAttemptCount(int quizAttemptCount) {
        this.quizAttemptCount = quizAttemptCount;
    }

    // Interface Methods
    @Override
    public void startQuiz() {
        quizAttemptCount++;
        System.out.println("Quiz started.");
        System.out.println("Quiz Attempt: " + quizAttemptCount);
    }

    @Override
    public int calculateScore(int[] answers) {
        System.out.println("Answers received from student.");
        return 0;
    }

   // Interface method
    @Override
    public void displayResult(int score) {
    System.out.println("Student Name: " + getUsername());
    System.out.println("Quiz Result: " + score);
    }

    public void submitAnswers() {
        System.out.println("Answers submitted.");
    }
}
