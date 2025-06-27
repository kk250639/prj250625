package com.example.prj250625.member;

import com.example.prj250625.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "auto_login_token", schema = "prj250625")
public class AutoLoginToken {
    @Id
    @Column(name = "token", nullable = false, length = 100)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

}