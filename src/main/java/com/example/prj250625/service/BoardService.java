package com.example.prj250625.service;

import com.example.prj250625.board.dto.BoardForm;
import com.example.prj250625.dto.BoardListInfo;
import com.example.prj250625.entity.Board;
import com.example.prj250625.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;

    public void add(BoardForm formData) {
        Board board = new Board();
        board.setTitle(formData.getTitle());
        board.setContent(formData.getContent());
        board.setWriter(formData.getWriter());

        boardRepository.save(board);

    }

    public List<BoardListInfo> list() {

        List<BoardListInfo> boardList = boardRepository.findAllBy();

        return boardList;
    }
}
