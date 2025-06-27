package com.example.prj250625.member.repository;

import com.example.prj250625.member.AutoLoginToken;
import com.example.prj250625.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoLoginTokenRepository extends JpaRepository<AutoLoginToken, String> {
    void deleteAllByMember(Member member);
}