package com.example.prj250625.member.dto;

import lombok.Data;

@Data
public class MemberForm {
    private Integer id;
    private String nickName;
    private String password;
    private String info;
}
