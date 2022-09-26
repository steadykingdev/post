package com.steadykingdev.post.controller;

import com.steadykingdev.post.argumentresolver.Login;
import com.steadykingdev.post.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@Login Member loginMember, Model model) {

        model.addAttribute("member", loginMember);

        return "home";
    }
}
