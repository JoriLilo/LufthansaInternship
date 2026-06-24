package Session1;

import java.util.*;
import java.util.stream.Collectors;

public class SurveyService {

    private List<Response> responses= new ArrayList<>();

    public void addResponse(Response response) {
        responses.add(response);
    }

    public Answers findMostGivenAnswer(Survey survey) {
        Map<Answers, Integer> countMap = new EnumMap<>(Answers.class);

        for (Response response: responses) {
            if (!response.getSurvey().equals(survey))
                continue;
            for (Answers answer : response.getAnswers().values()) {
                countMap.merge(answer, 1, Integer::sum);
            }
        }
        return countMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }



    public void printSurveyResult(Survey survey) {
        for (Question question : survey.getQuestions()) {
            System.out.println("\nQUESTION: "
                    + question.getQuestion());
            Map<Answers, Integer> counts =
                    new EnumMap<>(Answers.class);
            for (Answers a : Answers.values()) {
                counts.put(a, 0);
            }
            for (Response response : responses) {
                if (!response.getSurvey().equals(survey))
                    continue;
                Answers answer =
                        response.getAnswers().get(question);
                if (answer != null) {
                    counts.put(answer,
                            counts.get(answer) + 1);
                }
            }
            counts.forEach((k, v) ->
                    System.out.println(k + ": " + v));
        }
    }



    public Map<Question, Answers> findAnswersByCandidate(
            Candidate candidate,
            Survey survey) {
        for (Response response : responses) {
            if (response.getCandidate().equals(candidate)
                    && response.getSurvey().equals(survey)) {
                return response.getAnswers();
            }
        }
        return Collections.emptyMap();
    }



    public Candidate findCandidateWithMostSurveys() {
        Map<Candidate, Long> map =
                responses.stream()
                        .collect(Collectors.groupingBy(
                                Response::getCandidate,
                                Collectors.counting()));
        return map.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }


    public void removeLowResponseQuestions(Survey survey) {
        int totalCandidates = (int) responses.stream()
                .filter(r -> r.getSurvey().equals(survey))
                .count();
        List<Question> toRemove = new ArrayList<>();
        for (Question question : survey.getQuestions()) {
            int answered = 0;
            for (Response response : responses) {
                if (!response.getSurvey().equals(survey))
                    continue;
                if (response.getAnswers().containsKey(question)) {
                    answered++;
                }
            }
            double percentage =
                    totalCandidates == 0 ? 0 :
                            (answered * 100.0 / totalCandidates);
            if (percentage < 50.0) {
                toRemove.add(question);
            }
        }
        survey.getQuestions().removeAll(toRemove);
    }
}
