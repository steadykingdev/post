package com.steadykingdev.post.domain;

import com.steadykingdev.post.domain.member.AddMemberForm;
import com.steadykingdev.post.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberService memberService;

    @PostConstruct
    public void init() {
        AddMemberForm addMemberForm = new AddMemberForm();
        addMemberForm.setLoginId("test");
        addMemberForm.setPassword("test1");
        addMemberForm.setNickName("테스터");
        memberService.addMember(addMemberForm);
    }
}
