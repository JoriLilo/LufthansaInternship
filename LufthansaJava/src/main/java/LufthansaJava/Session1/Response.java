package LufthansaJava.Session1;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Response {

    private Survey survey;
    private Candidate candidate;
    private LocalDate dateTaken;
    // Question -> Answer
    private Map<Question, Answers> answers = new HashMap<>();

    public Response(Survey survey, Candidate candidate) {
        this.survey = survey;
        this.candidate = candidate;
        this.dateTaken = LocalDate.now();
    }


    public void answerQuestion(Question question, Answers answer) {
        answers.put(question, answer);
    }

    public Survey getSurvey() {
        return survey;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public LocalDate getDateTaken() {
        return dateTaken;
    }

    public Map<Question, Answers> getAnswers() {
        return answers;
    }
}
