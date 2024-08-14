package com.twoteam.toyproject.dto;

import com.twoteam.toyproject.entity.Board;
import com.twoteam.toyproject.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardDTO {
    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드
    private Long memberId;

    public BoardDTO(String title, String content, Long memberId) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }

    public Board toEntity(Member member) {
        Board board = new Board();
        board.setTitle(this.title);
        board.setContent(this.content);
        board.setMember(member); // Member 객체를 설정
        return board;
    }
}
