package com.steadykingdev.post.controller;

import com.steadykingdev.post.dto.MemberCreateRequestDto;
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
    public String addForm(@ModelAttribute MemberCreateRequestDto memberCreateRequestDto) {
        return "member/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute MemberCreateRequestDto memberCreateRequestDto, BindingResult bindingResult) {
        log.info("memberCreateRequestDto={}", memberCreateRequestDto);

        if (bindingResult.hasErrors() || memberService.validate(memberCreateRequestDto) != null) {
            log.info("bindingResult={}", bindingResult);
            if (memberService.validate(memberCreateRequestDto) != null) {
                FieldError fieldError = memberService.validate(memberCreateRequestDto);
                bindingResult.addError(fieldError);
            }
            return "member/addMemberForm";
        }


        return "redirect:/";
    }
}
