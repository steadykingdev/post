package com.steadykingdev.post.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id;
    private String loginId;
    private String nickName;
    private String password;
}
