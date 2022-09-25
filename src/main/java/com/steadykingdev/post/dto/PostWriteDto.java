package com.steadykingdev.post.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostWriteDto {

    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;
    @NotEmpty(message = "본문을 작성해주세요.")
    private String contents;


    @Builder
    public PostWriteDto(Long id, Long memberId, String title, String contents, LocalDate dateTime) {
        this.title = title;
        this.contents = contents;
    }
}
