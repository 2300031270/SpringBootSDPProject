package com.klef.fsd.sdp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsd.sdp.model.Post;
import com.klef.fsd.sdp.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService
{
    @Autowired
    private PostRepository postRepository;

    @Override
    public String addPost(Post post) 
    {
        postRepository.save(post);
        return "Post Added Successfully";
    }

    @Override
    public List<Post> viewAllPosts() 
    {
        return postRepository.findAll();
    }

    @Override
    public Post viewPostById(int pid) 
    {
        return postRepository.findById(pid).orElse(null);
    }
}
