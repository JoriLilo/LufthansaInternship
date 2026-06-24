package Session1;

import java.time.LocalDate;

public class Question {
    private String question;
    private LocalDate submissionDate;
    private LocalDate deletionDate;
    public Question(String question) {
        this.question = question;
        this.submissionDate = LocalDate.now();
    }
    public String getQuestion() {
        return question;
    }
    public LocalDate getSubmissionDate() {
        return submissionDate;
    }
    public LocalDate getDeletionDate() {
        return deletionDate;
    }
    public void setDeletionDate(LocalDate deletionDate) {
        this.deletionDate = deletionDate;
    }
    @Override
    public String toString() {
        return question;
    }
}
