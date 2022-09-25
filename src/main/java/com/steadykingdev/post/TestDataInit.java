package com.steadykingdev.post;

import com.steadykingdev.post.domain.Member;
import com.steadykingdev.post.dto.MemberCreateRequestDto;
import com.steadykingdev.post.dto.PostForm;
import com.steadykingdev.post.service.MemberService;
import com.steadykingdev.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberService memberService;
    private final PostService postService;

    @PostConstruct
    public void init() {
        MemberCreateRequestDto memberCreateRequestDto = new MemberCreateRequestDto();
        memberCreateRequestDto.setLoginId("test");
        memberCreateRequestDto.setPassword("test1");
        memberCreateRequestDto.setNickName("테스터");
        Member member = memberService.addMember(memberCreateRequestDto);

        PostForm postForm = new PostForm();
        postForm.setTitle("test post");
        postForm.setContents("테스트중입니다.");
        postForm.setNickName(member.getNickName());
        postService.addPost(postForm, member.getId());
        PostForm postForm2 = new PostForm();
        postForm2.setTitle("test post");
        postForm2.setContents("테스트중입니다.");
        postForm2.setNickName(member.getNickName());
        postService.addPost(postForm, member.getId());
        log.info("postForm={}", postForm);
        log.info("postForm2={}", postForm2);
    }
}
