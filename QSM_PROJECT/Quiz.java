public class Quiz {
    private Question[] questions;

    public Quiz(Question[] questions) {
        this.questions = questions;
    }

    public void loadQuestions() {
        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questions[i].getQuestionText());
            String[] opts = questions[i].getOptions();
            for (int j = 0; j < opts.length; j++) {
                System.out.println((j + 1) + ". " + opts[j]);
            }
        }
    }

    public int calculateScore(int[] answers) {
        int score = 0;
        for (int i = 0; i < questions.length; i++) {
            if (questions[i].checkAnswer(answers[i])) {
                score++;
            }
        }
        return score;
    }

    public int getQuestionCount() {
        return questions.length;
    }
}
