package com.example.prj250625.board.service;

import com.example.prj250625.board.dto.BoardForm;
import com.example.prj250625.board.dto.BoardListInfo;
import com.example.prj250625.board.entity.Board;
import com.example.prj250625.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public List<BoardListInfo> list(Integer page) {

        List<BoardListInfo> boardList = boardRepository
                .findAllBy(PageRequest.of(page - 1, 10, Sort.by("id").descending()));

        return boardList;
    }
}
