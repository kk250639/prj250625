package com.example.prj250625.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDto {
    private Integer id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
}
