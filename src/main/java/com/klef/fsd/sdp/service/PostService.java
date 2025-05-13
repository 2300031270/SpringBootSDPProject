package com.klef.fsd.sdp.service;

import java.util.List;
import com.klef.fsd.sdp.model.Post;

public interface PostService 
{
   public String addPost(Post post);
   public List<Post> viewAllPosts();
   public Post viewPostById(int pid);
}
