package com.example.prj250625.board.dto;

import com.example.prj250625.member.dto.MemberListInfo;
import lombok.Data;

import java.time.LocalDateTime;

public interface BoardListInfo {
    public Integer getId();

    public String getTitle();

    public MemberListInfo getWriter();

    public LocalDateTime getCreatedAt();
}
