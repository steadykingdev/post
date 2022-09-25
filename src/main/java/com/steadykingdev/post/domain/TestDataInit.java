package com.steadykingdev.post.domain;

import com.steadykingdev.post.dto.AddMemberForm;
import com.steadykingdev.post.service.MemberService;
import com.steadykingdev.post.dto.PostForm;
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
        AddMemberForm addMemberForm = new AddMemberForm();
        addMemberForm.setLoginId("test");
        addMemberForm.setPassword("test1");
        addMemberForm.setNickName("테스터");
        Member member = memberService.addMember(addMemberForm);

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
