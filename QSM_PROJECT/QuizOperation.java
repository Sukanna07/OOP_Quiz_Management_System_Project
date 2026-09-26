public interface QuizOperation {

    void startQuiz();
    int calculateScore(int[] answers);
    void displayResult(int score);
    
}
