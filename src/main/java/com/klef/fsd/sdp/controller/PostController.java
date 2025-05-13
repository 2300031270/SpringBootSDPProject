package com.klef.fsd.sdp.controller;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.klef.fsd.sdp.dto.PostDTO;
import com.klef.fsd.sdp.model.Post;
import com.klef.fsd.sdp.service.PostService;

@RestController
@RequestMapping("/post")
@CrossOrigin("*")
public class PostController 
{
    @Autowired
    private PostService postService;

    // Add a post with image
    @PostMapping("/addpost")
    public ResponseEntity<String> addPost(
        @RequestParam String title,
        @RequestParam String content,
        @RequestParam String url,
        @RequestParam("postimage") MultipartFile file) {
        
        try {
            byte[] bytes = file.getBytes();
            Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

            Post post = new Post();
            post.setTitle(title);
            post.setContent(content);
            post.setUrl(url);
            post.setImage(blob);

            String output = postService.addPost(post);
            return ResponseEntity.ok(output);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // View all posts
    @GetMapping("/viewallposts")
    public ResponseEntity<List<PostDTO>> viewAllPosts() {
        List<Post> postList = postService.viewAllPosts();
        List<PostDTO> postDTOList = new ArrayList<>();

        for (Post post : postList) {
            PostDTO dto = new PostDTO();
            dto.setId(post.getId());
            dto.setTitle(post.getTitle());
            dto.setContent(post.getContent());
            dto.setUrl(post.getUrl());
            postDTOList.add(dto);
        }

        return ResponseEntity.ok(postDTOList);
    }

    // Display post image by id
    @GetMapping("/displaypostimage")
    public ResponseEntity<byte[]> displayPostImage(@RequestParam int id) throws Exception {
        Post post = postService.viewPostById(id);
        byte[] imageBytes = post.getImage().getBytes(1, (int) post.getImage().length());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    // View post by ID
    @PostMapping("/displaypostbyid")
    public ResponseEntity<PostDTO> displayPostById(@RequestParam int pid) {
        Post post = postService.viewPostById(pid);

        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setUrl(post.getUrl());

        return ResponseEntity.ok(dto);
    }
}
