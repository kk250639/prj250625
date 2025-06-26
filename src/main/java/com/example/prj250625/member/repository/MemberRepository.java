package com.example.prj250625.member.repository;

import com.example.prj250625.member.dto.MemberListInfo;
import com.example.prj250625.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
//    Optional<Member> findById(String id);

    Optional<Member> findByNickName(String nickName);

    List<MemberListInfo> findAllBy();
}