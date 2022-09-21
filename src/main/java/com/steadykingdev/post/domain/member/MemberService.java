package com.steadykingdev.post.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public Member addMember(AddMemberForm form) {
        Member member = new Member();

        member.setLoginId(form.getLoginId());
        member.setPassword(form.getPassword());
        member.setNickName(form.getNickName());

        try{
            validateDuplicateMember(member);
        } catch(IllegalArgumentException e) {
            return member;
        }

        memberRepository.save(member);

        return member;
    }

    public void validateDuplicateMember(Member member) {
        memberRepository.findByLoginId(member.getLoginId())
                .ifPresent(m -> {
                    throw new IllegalArgumentException("이미 존재하는 아이디입니다");
                });
    }
}
