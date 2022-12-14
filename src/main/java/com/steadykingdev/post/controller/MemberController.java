package com.steadykingdev.post.controller;

import com.steadykingdev.post.argumentresolver.Login;
import com.steadykingdev.post.domain.Member;
import com.steadykingdev.post.dto.MemberCreateDto;
import com.steadykingdev.post.service.MemberService;
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
    public String addForm(@ModelAttribute MemberCreateDto memberCreateDto, @Login Member member) {

        if (member != null) {
            return "redirect:/";
        }

        return "member/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute MemberCreateDto memberCreateDto, BindingResult bindingResult) {
        log.info("memberCreateDto={}", memberCreateDto);

        if (bindingResult.hasErrors() || memberService.validate(memberCreateDto) != null) {
            log.info("bindingResult={}", bindingResult);
            if (memberService.validate(memberCreateDto) != null) {
                FieldError fieldError = memberService.validate(memberCreateDto);
                bindingResult.addError(fieldError);
            }
            return "member/addMemberForm";
        }


        return "redirect:/post";
    }
}
