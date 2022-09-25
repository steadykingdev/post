package com.steadykingdev.post.dto;

import com.steadykingdev.post.domain.Post;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostForm {

    private Long id;
    private Long memberId;
    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;
    @NotEmpty(message = "본문을 작성해주세요.")
    private String contents;
    private String nickName;
    private LocalDate dateTime;

    public Post toPost() {
        Post post = Post.builder()
                .id(id)
                .memberId(memberId)
                .title(title)
                .contents(contents)
                .dateTime(dateTime)
                .build();
        return post;
    }

    @Builder
    public PostForm(Long id, Long memberId, String title, String contents, String nickName, LocalDate dateTime) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.nickName = nickName;
        this.dateTime = dateTime;
    }
}
