package com.steadykingdev.post;

import com.steadykingdev.post.domain.Member;
import com.steadykingdev.post.dto.MemberCreateDto;
import com.steadykingdev.post.dto.PostWriteDto;
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
        MemberCreateDto memberCreateDto = new MemberCreateDto();
        memberCreateDto.setLoginId("test1");
        memberCreateDto.setPassword("test1");
        memberCreateDto.setNickName("테스터1");
        Member member = memberService.addMember(memberCreateDto);
        MemberCreateDto memberCreateDto2 = new MemberCreateDto();
        memberCreateDto2.setLoginId("test2");
        memberCreateDto2.setPassword("test2");
        memberCreateDto2.setNickName("테스터2");
        Member member2 = memberService.addMember(memberCreateDto2);

        PostWriteDto postWriteDto = new PostWriteDto();
        postWriteDto.setTitle("test post");
        postWriteDto.setContents("테스트중입니다.");
        postService.addPost(postWriteDto, member.getId());
        PostWriteDto postWriteDto2 = new PostWriteDto();
        postWriteDto2.setTitle("test post");
        postWriteDto2.setContents("테스트중입니다.");
        postService.addPost(postWriteDto2, member2.getId());
        log.info("postForm={}", postWriteDto);
        log.info("postForm2={}", postWriteDto2);
    }
}
