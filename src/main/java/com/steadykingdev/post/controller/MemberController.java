package com.steadykingdev.post.controller;

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

        if (bindingResult.hasErrors() || memberService.test(addMemberForm) != null) {
            log.info("bindingResult={}", bindingResult);
            if (memberService.test(addMemberForm) != null) {
                FieldError fieldError = memberService.test(addMemberForm);
                bindingResult.addError(fieldError);
            }
            return "member/addMemberForm";
        }


        return "redirect:/";
    }
}
