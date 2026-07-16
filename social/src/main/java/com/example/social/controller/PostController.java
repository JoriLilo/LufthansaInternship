package com.example.social.controller;

import com.example.social.dto.PostDTO;

import com.example.social.dto.UserDTO;
import com.example.social.entity.Post;
import com.example.social.entity.User;
import com.example.social.repo.PostRepository;
import com.example.social.repo.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users/{userId}/posts")
@AllArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @PostMapping
    public ResponseEntity<Void> save(@PathVariable Long userId , @Valid @RequestBody PostDTO postDTO) {
        Post post = new Post();
        post.setContent(postDTO.getContent());
        post.setTitle(postDTO.getTitle());
        postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAll( @PathVariable Long userId) {

        List<Post> posts = postRepository.findAll();

        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post : posts) {
            PostDTO postDTO = PostDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .build();

            postDTOS.add(postDTO);

        }

        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<PostDTO>> getPostsByTitle( @PathVariable Long userId,@RequestParam String title) {

        List<Post> posts = postRepository.findByTitle(title);
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post : posts) {
            PostDTO postDTO = PostDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .build();

            postDTOS.add(postDTO);

        }

        return new ResponseEntity<>(postDTOS, HttpStatus.OK);


    }



}
