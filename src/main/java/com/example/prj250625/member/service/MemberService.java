package com.example.prj250625.member.service;

import com.example.prj250625.member.dto.MemberForm;
import com.example.prj250625.member.entity.Member;
import com.example.prj250625.member.repository.MemberRepository;


public class MemberService {

    private final MemberRepository memberRepository;
    
    public void add(MemberForm data) {
        Member member = new Member();
        member.setId(data.getId());
        member.setNickName(data.getNickName());
        member.setPassword(data.getPassword());
        member.setInfo(data.getInfo());

        memberRepository.save(member);
    }
}
