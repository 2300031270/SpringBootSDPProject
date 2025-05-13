package com.klef.fsd.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsd.sdp.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
  // Additional query methods (if needed) can be added here
}
