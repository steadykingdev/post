package com.steadykingdev.post.controller;

import com.steadykingdev.post.argumentresolver.Login;
import com.steadykingdev.post.domain.Member;
import com.steadykingdev.post.dto.PostWriteDto;
import com.steadykingdev.post.dto.PostDto;
import com.steadykingdev.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public String addPost(@Validated @ModelAttribute PostWriteDto postWriteDto, BindingResult bindingResult, @Login Member member) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "post/addPostForm";
        }

        postService.addPost(postWriteDto, member.getId());
        return "redirect:/post";
    }

    @GetMapping("/get/{postId}")
    public String getPost(@PathVariable Long postId,
                          @Login Member member, Model model) {

        PostDto form = postService.getPost(postId);
        Long memberId = new Long(0);

        if(member != null) {
            memberId = member.getId();
        }

        model.addAttribute("postForm", form);
        model.addAttribute("memberId", memberId);

        return "post/post";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId,@Login Member member, Model model) {

        PostDto postDto = postService.getPost(postId);

        model.addAttribute("postWriteDto", postDto);

        return "post/editPostForm";
    }

    @PostMapping("/{postId}/edit")
    public String editPost(@ModelAttribute PostWriteDto postWriteDto, @PathVariable Long postId) {

        postService.editPost(postId, postWriteDto);

        return "redirect:/post/{postId}";
    }

    @PostMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {

        postService.deletePost(postId);

        return "redirect:/post";
    }

}
