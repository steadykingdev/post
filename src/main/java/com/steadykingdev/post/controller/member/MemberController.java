package com.steadykingdev.post.controller.member;

import com.steadykingdev.post.domain.member.AddMemberForm;
import com.steadykingdev.post.domain.member.Member;
import com.steadykingdev.post.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute AddMemberForm addMemberForm) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute AddMemberForm addMemberForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            return "members/addMemberForm";
        }

        if (!addMemberForm.getPassword().equals(addMemberForm.getPasswordCheck())) {
            bindingResult.reject("passwordNotMatched", "비밀번호가 일치하지않습니다.");
            log.info("bindingResult={}", bindingResult);
            return "members/addMemberForm";
        }
        memberRepository.save(addMemberForm);
        log.info("member={}", addMemberForm);
        return "redirect:/";
    }
}
