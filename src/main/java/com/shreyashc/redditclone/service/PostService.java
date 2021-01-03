package com.shreyashc.redditclone.service;


import com.shreyashc.redditclone.dto.PostRequest;
import com.shreyashc.redditclone.dto.PostResponse;
import com.shreyashc.redditclone.exceptions.SpringRedditException;
import com.shreyashc.redditclone.mapper.PostMapper;
import com.shreyashc.redditclone.model.Post;
import com.shreyashc.redditclone.model.Subreddit;
import com.shreyashc.redditclone.model.User;
import com.shreyashc.redditclone.repository.PostRepository;
import com.shreyashc.redditclone.repository.SubredditRepository;
import com.shreyashc.redditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new SpringRedditException("Post not Found"));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName()).orElseThrow(() ->new  SpringRedditException("subreddit not found"));
        postRepository.save(postMapper.map(postRequest,subreddit,authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId){
        Subreddit subreddit = subredditRepository.findById(subredditId).orElseThrow(() -> new  SpringRedditException("subreddit not found"));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());

    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringRedditException(username +"not found"));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }



}
