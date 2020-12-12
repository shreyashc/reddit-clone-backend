package com.shreyashc.redditclone.repository;

import com.shreyashc.redditclone.model.Post;
import com.shreyashc.redditclone.model.Subreddit;
import com.shreyashc.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
