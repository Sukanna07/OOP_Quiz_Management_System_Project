import java.util.ArrayList;
import java.util.HashMap;

public class Quiz implements QuizOperation {
    private ArrayList<Question> questions;
    private HashMap<String, Integer> scoreMap;

    public Quiz(ArrayList<Question> questions) {
        this.questions = questions;
        this.scoreMap = new HashMap<>();
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question q) {
        this.questions.add(q);
    }

    public HashMap<String, Integer> getScoreMap() {
        return scoreMap;
    }

    @Override
    public void startQuiz() {
        System.out.println("Quiz session initiated.");
    }

    @Override
    public void calculateScore() {
        // Core score calculation logic
    }

    @Override
    public void displayResult() {
        System.out.println("Displaying results in GUI.");
    }

    public int calculateScore(int[] answers) {
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).checkAnswer(answers[i])) {
                score++;
            }
        }
        return score;
    }

    public void recordScore(String username, int score) {
        scoreMap.put(username, score);
    }

    public int getQuestionCount() {
        return questions.size();
    }
}
