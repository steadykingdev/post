package com.steadykingdev.post.domain;

import com.steadykingdev.post.dto.PostDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Post {

    private Long id;
    private Long memberId;
    private String title;
    private String contents;
    private LocalDate dateTime;

    public PostDto toPostDto() {
        PostDto postDto = PostDto.builder()
                .id(id)
                .memberId(memberId)
                .title(title)
                .contents(contents)
                .dateTime(dateTime)
                .build();
        return postDto;
    }

    @Builder
    public Post(Long id, Long memberId, String title, String contents, LocalDate dateTime) {
        this.id = id;
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
        this.dateTime = dateTime;
    }
}
