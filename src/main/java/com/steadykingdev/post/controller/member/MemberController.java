package com.steadykingdev.post.controller.member;

import com.steadykingdev.post.domain.member.AddMemberForm;
import com.steadykingdev.post.domain.member.Member;
import com.steadykingdev.post.domain.member.MemberRepository;
import com.steadykingdev.post.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute AddMemberForm addMemberForm) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute AddMemberForm addMemberForm, BindingResult bindingResult) {
        log.info("member={}", addMemberForm);

        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            return "members/addMemberForm";
        }

        if (!addMemberForm.getPassword().equals(addMemberForm.getPasswordCheck())) {
            bindingResult.addError(new FieldError("addMemberForm","passwordCheck","비밀번호가 일치하지 않습니다."));
            log.info("bindingResult={}", bindingResult);
            return "members/addMemberForm";
        }

        Member member = memberService.addMember(addMemberForm);

        if (member.getId() == null) {
            log.info("if문 member={}", member);
            bindingResult.addError(new FieldError("addMemberForm","loginId","이미 존재하는 아이디입니다."));
            return "members/addMemberForm";
        }

        return "redirect:/";
    }
}
