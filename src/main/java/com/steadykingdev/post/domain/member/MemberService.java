package com.steadykingdev.post.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public Member addMember(AddMemberForm form) throws IllegalStateException{

        validateDuplicateMember(form);
        Member member = new Member();
        member.setLoginId(form.getLoginId());
        member.setPassword(form.getPassword());
        member.setNickName(form.getNickName());

        memberRepository.save(member);

        return member;
    }

    public void validateDuplicateMember(AddMemberForm form) {
        memberRepository.findByLoginId(form.getLoginId())
                .ifPresent(m -> {
                    throw new IllegalArgumentException("이미 존재하는 아이디입니다");
                });
    }

    public FieldError test(AddMemberForm addMemberForm) {
        if (!addMemberForm.getPassword().equals(addMemberForm.getPasswordCheck())) {
            return new FieldError("addMemberForm", "passwordCheck", "비밀번호가 일치하지 않습니다.");
        }
        try {
            addMember(addMemberForm);
        } catch (IllegalStateException e) {
            return new FieldError("addMemberForm", "loginId", "이미 존재하는 아이디입니다.");
        }
        return null;
    }
}
