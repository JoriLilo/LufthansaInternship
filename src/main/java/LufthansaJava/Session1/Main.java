package LufthansaJava.Session1;

public class Main {
    public static void main(String[] args) {
        Survey survey = new Survey(
                "Employee Satisfaction",
                "HR",
                "Annual survey");

        for (int i = 1; i <= 10; i++) {
            survey.addQuestion(
                    new Question("Question " + i));
        }

        Candidate c1 = new Candidate(
                "John",
                "Smith",
                "john@test.com",
                "111111");
        Candidate c2 = new Candidate(
                "Mary",
                "Jones",
                "mary@test.com",
                "222222");

        Response r1 =
                new Response(survey, c1);
        Response r2 =
                new Response(survey, c2);

        for (Question q : survey.getQuestions()) {
            r1.answerQuestion(q, Answers.AGREE);
            r2.answerQuestion(q, Answers.SLIGHTLY_AGREE);
        }

        SurveyService service =
                new SurveyService();
        service.addResponse(r1);
        service.addResponse(r2);

        System.out.println(
                "Survey valid: "
                        + survey.validateSurvey());
        System.out.println(
                "Most common answer: "
                        + service.findMostGivenAnswer(survey));
        System.out.println(
                "Top candidate: "
                        + service.findCandidateWithMostSurveys());

        service.printSurveyResult(survey);
    }
}