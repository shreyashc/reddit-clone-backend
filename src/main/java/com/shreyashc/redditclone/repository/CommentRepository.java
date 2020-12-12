package com.shreyashc.redditclone.repository;

import com.shreyashc.redditclone.model.Comment;
import com.shreyashc.redditclone.model.Post;
import com.shreyashc.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {


    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
