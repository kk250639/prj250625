package com.example.prj250625.repository;

import com.example.prj250625.dto.BoardListInfo;
import com.example.prj250625.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<BoardListInfo> findAllBy();
}