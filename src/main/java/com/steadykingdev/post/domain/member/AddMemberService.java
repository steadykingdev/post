package com.steadykingdev.post.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddMemberService {

    public Member addMember(String loginId, String password, String nickName) {
        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(password);
        member.setNickName(nickName);
        return member;
    }
}
