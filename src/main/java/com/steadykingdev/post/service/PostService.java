package com.steadykingdev.post.service;

import com.steadykingdev.post.domain.Member;
import com.steadykingdev.post.domain.Post;
import com.steadykingdev.post.dto.PostWriteDto;
import com.steadykingdev.post.dto.PostDto;
import com.steadykingdev.post.repository.MemoryMemberRepository;
import com.steadykingdev.post.repository.MemoryPostRepository;
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

    private final MemoryPostRepository memoryPostRepository;
    private final MemoryMemberRepository memoryMemberRepository;

    public PostWriteDto addPost(PostWriteDto postWriteDto, Long memberId) {
        Post post= Post.builder()
                .memberId(memberId)
                .title(postWriteDto.getTitle())
                .contents(postWriteDto.getContents())
                .dateTime(LocalDate.now())
                .build();

        Post savedPost = memoryPostRepository.save(post);
        log.info("add post repository={}", savedPost);

        return postWriteDto;
    }

    public PostWriteDto editPost(Long postId, PostWriteDto postWriteDto){

        Post savedPost = memoryPostRepository.update(postId, postWriteDto.getTitle(), postWriteDto.getContents());
        log.info("edit post ={}", savedPost);
        return postWriteDto;
    }

    public PostDto getPost(Long postId) {

        Post findPost = memoryPostRepository.findById(postId);
        Member member = memoryMemberRepository.findById(findPost.getMemberId());

        PostDto postDto = findPost.toPostDto();
        postDto.setNickName(member.getNickName());

        log.info("requestForm={}", postDto);

        return postDto;
    }

    public List<PostDto> getPostList() {

        List<Post> postList = memoryPostRepository.findAll();
        List<PostDto> postFormList = new ArrayList<>();

        Long sequance = 0L;

        for (Post post : postList) {
            PostDto postDto = post.toPostDto();
            postDto.setId(++sequance);
            postDto.setNickName(memoryMemberRepository.findById(post.getMemberId()).getNickName());
            postFormList.add(postDto);
        }
        return postFormList;
    }

    public void deletePost(Long postId) {
        memoryPostRepository.delete(postId);
    }
}
