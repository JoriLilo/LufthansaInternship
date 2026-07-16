package com.example.social.dto;

import com.example.social.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;



}
