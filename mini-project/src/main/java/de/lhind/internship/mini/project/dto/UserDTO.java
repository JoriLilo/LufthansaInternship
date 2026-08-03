package de.lhind.internship.mini.project.dto;


import de.lhind.internship.mini.project.entity.Role;
import lombok.*;

// UserDTO.java — outgoing response only, never carries the password/hash
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String username;
    private Role role;

}
