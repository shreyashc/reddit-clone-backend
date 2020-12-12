package com.shreyashc.redditclone.repository;

import com.shreyashc.redditclone.model.Post;
import com.shreyashc.redditclone.model.User;
import com.shreyashc.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
