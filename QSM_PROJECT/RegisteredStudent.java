public class RegisteredStudent extends Student {
    private boolean registrationStatus;
    private int quizAttemptCount;

    public RegisteredStudent(String username, String password, String studentId, 
                             String department, boolean registrationStatus, int quizAttemptCount) {
        super(username, password, studentId, department);
        this.registrationStatus = registrationStatus;
        this.quizAttemptCount = quizAttemptCount;
    }

    public boolean isRegistrationStatus() {
        return registrationStatus;
    }

    public int getQuizAttemptCount() {
        return quizAttemptCount;
    }

    public void setRegistrationStatus(boolean registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public void setQuizAttemptCount(int quizAttemptCount) {
        this.quizAttemptCount = quizAttemptCount;
    }

    public void startQuiz() {
        System.out.println("Quiz started.");
    }

    public void viewQuiz() {
        System.out.println("Quiz viewed.");
    }

    public void submitAnswers() {
        System.out.println("Answers submitted.");
    }
}
