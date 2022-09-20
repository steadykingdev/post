package com.steadykingdev.post.domain;

import com.steadykingdev.post.domain.member.AddMemberForm;
import com.steadykingdev.post.domain.member.AddMemberService;
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
        AddMemberForm addMemberForm = new AddMemberForm();
        addMemberForm.setLoginId("test");
        addMemberForm.setPassword("test1");
        addMemberForm.setNickName("테스터");
        memberRepository.save(addMemberForm);
    }
}
