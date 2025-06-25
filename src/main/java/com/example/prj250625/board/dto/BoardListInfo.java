package com.example.prj250625.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

public interface BoardListInfo {
    public Integer getId();

    public String getTitle();

    public String getWriter();

    public LocalDateTime getCreatedAt();
}
