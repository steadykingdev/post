package com.steadykingdev.post.repository;

import com.steadykingdev.post.domain.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PostRepository {

    private static final Map<Long, Post> store = new HashMap<>();
    private static long sequance = 0L;

    public Post save(Post post) {
        post.setId(++sequance);
        store.put(post.getId(), post);
        return post;
    }

    public Post update(Long postId, Post updatePost) {
        log.info("repository updatePost={}", updatePost);
        Post post = store.get(postId);

        post.setTitle(updatePost.getTitle());
        post.setContents(updatePost.getContents());
        post.setDateTime(post.getDateTime());

        return post;
    }

    public Post findById(Long id) {
        return store.get(id);
    }

    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }
}
