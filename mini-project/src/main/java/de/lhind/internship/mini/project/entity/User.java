package de.lhind.internship.mini.project.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private int failedAttempts=0;
    private boolean accountLocked= false;
}
