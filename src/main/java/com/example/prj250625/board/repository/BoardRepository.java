package com.example.prj250625.board.repository;

import com.example.prj250625.board.dto.BoardListInfo;
import com.example.prj250625.board.entity.Board;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<BoardListInfo> findAllBy();

    List<BoardListInfo> findAllBy(PageRequest id);
}