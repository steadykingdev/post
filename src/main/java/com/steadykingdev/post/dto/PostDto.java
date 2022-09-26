package com.steadykingdev.post.dto;

import com.steadykingdev.post.domain.Post;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostDto {

    private Long id;
    private Long memberId;
    private String title;
    private String contents;
    private String nickName;
    private LocalDate dateTime;
    private Long rowNum;

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
    public PostDto(Long id, Long memberId, String title, String contents, String nickName, LocalDate dateTime) {
        this.id = id;
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
        this.nickName = nickName;
        this.dateTime = dateTime;
    }
}
