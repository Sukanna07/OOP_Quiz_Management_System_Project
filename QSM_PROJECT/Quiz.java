import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages question collection and score map persistence.
 */
public class Quiz {
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
