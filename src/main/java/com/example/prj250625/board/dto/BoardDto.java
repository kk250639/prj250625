package com.example.prj250625.board.dto;

import com.example.prj250625.member.dto.MemberDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDto {
    private Integer id;
    private String title;
    private String content;
    private MemberDto writer;
    private LocalDateTime createdAt;
}
