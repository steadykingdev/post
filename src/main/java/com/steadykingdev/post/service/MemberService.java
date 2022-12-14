package com.steadykingdev.post.service;

import com.steadykingdev.post.domain.Member;
import com.steadykingdev.post.dto.MemberCreateDto;
import com.steadykingdev.post.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemoryMemberRepository memoryMemberRepository;
    public Member addMember(MemberCreateDto form) throws IllegalStateException{

        validateDuplicateMember(form);
        Member member = new Member();
        member.setLoginId(form.getLoginId());
        member.setPassword(form.getPassword());
        member.setNickName(form.getNickName());

        memoryMemberRepository.save(member);

        return member;
    }

    public void validateDuplicateMember(MemberCreateDto memberCreateDto) {
        memoryMemberRepository.findByLoginId(memberCreateDto.getLoginId())
                .ifPresent(m -> {
                    throw new IllegalArgumentException("이미 존재하는 아이디입니다");
                });
    }

    public FieldError validate(MemberCreateDto memberCreateDto) {
        if (!memberCreateDto.getPassword().equals(memberCreateDto.getPasswordCheck())) {
            return new FieldError("addMemberForm", "passwordCheck", "비밀번호가 일치하지 않습니다.");
        }
        try {
            addMember(memberCreateDto);
        } catch (IllegalStateException e) {
            return new FieldError("addMemberForm", "loginId", "이미 존재하는 아이디입니다.");
        }
        return null;
    }
}
