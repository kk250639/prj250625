package com.example.prj250625.member.repository;

import com.example.prj250625.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findById(String id);

    Optional<Member> findByNickName(String nickName);
}