package Session1;

import java.util.Objects;

public class Candidate {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    public Candidate(String firstName, String lastName,
                     String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;


    }

    public String getFirstName() {
        return firstName;

    }

    public String getLastName() {
        return lastName;

    }
    public String getEmail() {
        return email;

    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate)) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(email, candidate.email);
    }
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}