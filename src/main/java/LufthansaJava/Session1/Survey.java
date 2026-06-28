package LufthansaJava.Session1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Survey {

    private String title;
    private String topic;
    private String description;
    private List<Question> questions = new ArrayList<>();

    public Survey(String title, String topic, String description) {
        this.title = title;
        this.topic = topic;
        this.description = description;

    }
    public String getTitle() {
        return title;

    }

    public List<Question> getQuestions() {
        return questions;
    }

    public boolean addQuestion(Question question) {
        if (questions.contains(question)) {
            return false;
        }
        for (Question q : questions) {
            if (q.getQuestion().equalsIgnoreCase(question.getQuestion())) {
                return false;
            }
        }
        if (questions.size() >= 40) {
            throw new IllegalStateException(
                    "No more than 40 questions in this survey");
        }
        questions.add(question);
        return true;

    }

    public boolean removeQuestion(Question question) {
        return questions.remove(question);
    }
    public boolean validateSurvey() {
        if (questions.size() < 10 || questions.size() > 40) {
            return false;
        }
        for (int i = 0; i < questions.size(); i++) {
            for (int j = i + 1; j < questions.size(); j++) {
                if (questions.get(i).getQuestion()
                        .equalsIgnoreCase(questions.get(j).getQuestion())) {
                    return false;
                }
            }
        }
        return true;
    }
}
