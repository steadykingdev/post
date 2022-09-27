package com.steadykingdev.post.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
public class PostWriteDto {

    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;

    @NotEmpty(message = "본문을 작성해주세요.")
    private String contents;

}
