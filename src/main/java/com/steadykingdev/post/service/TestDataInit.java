package com.steadykingdev.post.service;

import com.steadykingdev.post.domain.member.Member;
import com.steadykingdev.post.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test1");
        member.setNickName("테스터");

        memberRepository.save(member);
    }
}
