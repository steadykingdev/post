package com.steadykingdev.post.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberCreateDto {

//    private Long id;

    @NotEmpty(message = "아이디를 입력해주세요.")
    private String loginId;

    @NotEmpty(message = "닉네임을 입력해주세요.")
    private String nickName;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String passwordCheck;
}
