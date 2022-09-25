package com.steadykingdev.post.controller;

import com.steadykingdev.post.SessionConst;
import com.steadykingdev.post.domain.Member;
import com.steadykingdev.post.dto.PostForm;
import com.steadykingdev.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping
    public String posts(Model model) {
        List<PostForm> posts = postService.getPostList();
        model.addAttribute("posts", posts);
        return "post/posts";
    }

    @GetMapping("/add")
    public String addPostForm(@ModelAttribute PostForm postForm) {
        return "post/addPostForm";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute PostForm postForm, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        postService.addPost(postForm, member.getId());
        return "redirect:/";
    }

    @GetMapping("/{postId}")
    public String getPost(@PathVariable Long postId,
                          HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);

        Long memberId = null;
        if (session != null) {
            Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            memberId = member.getId();
        }

        PostForm form = postService.getPost(postId);


        model.addAttribute("postForm", form);
        model.addAttribute("memberId", memberId);

        return "post/post";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model) {
        PostForm postForm = postService.getPost(postId);
        model.addAttribute("postForm", postForm);

        return "post/editPostForm";
    }

    @PostMapping("/{postId}/edit")
    public String editPost(@ModelAttribute PostForm postForm, @PathVariable Long postId) {

        postService.editPost(postId, postForm);

        return "redirect:/post/{postId}";
    }

    @PostMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long a) {
        return "";
    }

}