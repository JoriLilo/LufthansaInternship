package de.lhind.internship.mini.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GuestProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private LocalDate dateOfBirth;
    private String nationality;
    public String preferredLanguage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", unique = true, nullable = false)
    private Guest guest;
}
