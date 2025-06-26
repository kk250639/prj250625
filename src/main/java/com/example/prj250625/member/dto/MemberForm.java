package com.example.prj250625.member.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberForm {
    private String id;
    private String nickName;
    private String password;
    private String info;
    private LocalDateTime createdAt;
}
