package com.steadykingdev.post.controller;

import com.steadykingdev.post.SessionConst;
import com.steadykingdev.post.argumentresolver.Login;
import com.steadykingdev.post.domain.Member;
import com.steadykingdev.post.dto.PostWriteDto;
import com.steadykingdev.post.dto.PostDto;
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
        List<PostDto> posts = postService.getPostList();
        model.addAttribute("posts", posts);
        return "post/posts";
    }

    @GetMapping("/add")
    public String addPostForm(@ModelAttribute PostWriteDto postWriteDto) {
        return "post/addPostForm";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute PostWriteDto postWriteDto, @Login Member member) {
        log.info("member={}", member);
        postService.addPost(postWriteDto, member.getId());
        return "redirect:/post";
    }

    @GetMapping("/{postId}")
    public String getPost(@PathVariable Long postId,
                          @Login Member member, Model model) {

        PostDto form = postService.getPost(postId);


        model.addAttribute("postForm", form);
//        model.addAttribute("memberId", member.getId());

        return "post/post";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model) {
        PostDto postDto = postService.getPost(postId);
        model.addAttribute("postWriteDto", postDto);

        return "post/editPostForm";
    }

    @PostMapping("/{postId}/edit")
    public String editPost(@ModelAttribute PostWriteDto postWriteDto, @PathVariable Long postId) {

        postService.editPost(postId, postWriteDto);

        return "redirect:/post/{postId}";
    }

    @DeleteMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/post";
    }

}
