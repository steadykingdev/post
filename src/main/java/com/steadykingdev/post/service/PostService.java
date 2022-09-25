package com.steadykingdev.post.service;

import com.steadykingdev.post.domain.Member;
import com.steadykingdev.post.domain.Post;
import com.steadykingdev.post.dto.PostForm;
import com.steadykingdev.post.repository.MemberRepository;
import com.steadykingdev.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostForm addPost(PostForm postForm, Long memberId) {

        Post post= Post.builder()
                .id(postForm.getId())
                .memberId(memberId)
                .title(postForm.getTitle())
                .contents(postForm.getContents())
                .dateTime(LocalDate.now())
                .build();

        Post savedPost = postRepository.save(post);
        log.info("repositoryPost={}", savedPost);

        return postForm;
    }

    public PostForm editPost(Long postId, PostForm postForm){

        Post post = postForm.toPost();
        log.info("edit service post={}", post);
        Post editedPost = postRepository.update(postId, post);
        return postForm;
    }

    public PostForm getPost(Long postId) {

        Post findPost = postRepository.findById(postId);
        Member member = memberRepository.findById(findPost.getMemberId());

        PostForm requestForm = findPost.toPostForm();
        requestForm.setNickName(member.getNickName());

        log.info("requestForm={}", requestForm);

        return requestForm;
    }

    public List<PostForm> getPostList() {
        List<Post> postList = postRepository.findAll();
        List<PostForm> postFormList = new ArrayList<>();

        for (Post post : postList) {
            PostForm postForm = PostForm.builder()
                    .id(post.getId())
                    .memberId(post.getMemberId())
                    .title(post.getTitle())
                    .dateTime(post.getDateTime())
                    .contents(post.getContents())
                    .nickName(memberRepository.findById(post.getMemberId()).getNickName())
                    .build();
            postFormList.add(postForm);
        }
        return postFormList;
    }
}
