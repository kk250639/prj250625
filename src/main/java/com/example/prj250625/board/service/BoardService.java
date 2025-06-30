package com.example.prj250625.board.service;

import com.example.prj250625.board.dto.BoardDto;
import com.example.prj250625.board.dto.BoardForm;
import com.example.prj250625.board.dto.BoardListInfo;
import com.example.prj250625.board.entity.Board;
import com.example.prj250625.board.repository.BoardRepository;
import com.example.prj250625.member.dto.MemberDto;
import com.example.prj250625.member.entity.Member;
import com.example.prj250625.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public void add(BoardForm formData, MemberDto user) {
        Board board = new Board();
        board.setTitle(formData.getTitle());
        board.setContent(formData.getContent());
//        board.setWriter(user.getId());
        Member member = memberRepository.findById(user.getId()).get();
        board.setWriter(member);

        boardRepository.save(board);

    }

    public Map<String, Object> list(Integer page, String keyword) {

        Page<BoardListInfo> boardPage = null;

        if (keyword == null || keyword.isBlank()) {

            boardPage = boardRepository
                    .findAllBy(PageRequest.of(page - 1, 10, Sort.by("id").descending()));
        } else {
            boardPage = boardRepository.searchByKeyword("%" + keyword + "%",
                    PageRequest.of(page - 1, 10, Sort.by("id").descending()));
        }
        List<BoardListInfo> boardList = boardPage.getContent();

        List<Integer> pageList = new ArrayList<>();
        int totalPages = boardPage.getTotalPages();
        int displayCount = 5;

        int start = page;
        int end = Math.min(page + displayCount - 1, totalPages - 2); // 마지막 2개는 따로

        // 현재 페이지부터 최대 displayCount개
        for (int i = start; i <= end; i++) {
            pageList.add(i);
        }

        if (end < totalPages - 2) {
            pageList.add(-1); // ... 표시
        }
        // 마지막 2개 페이지 (중복 방지해서 추가)
        if (totalPages >= 2) {
            if (!pageList.contains(totalPages - 1)) {
                pageList.add(totalPages - 1);
            }
        }
        if (!pageList.contains(totalPages)) {
            pageList.add(totalPages);
        }

        Integer rightPageNumber = ((page - 1) / 10 + 1) * 10;
        Integer leftPageNumber = rightPageNumber - 9;
        rightPageNumber = Math.min(rightPageNumber, boardPage.getTotalPages());

        var result = Map.of("boardList", boardList,
                "totalElements", boardPage.getTotalElements(),
                "totalPages", boardPage.getTotalPages(),
                "pageList", pageList,
                "rightPageNumber", rightPageNumber,
                "leftPageNumber", leftPageNumber,
                "currentPage", page);

        return result;
    }

    public BoardDto get(Integer id) {
        Board board = boardRepository.findById(id).get();
        BoardDto dto = new BoardDto();
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        MemberDto memberDto = new MemberDto();
        memberDto.setId(board.getWriter().getId());
        memberDto.setNickName(board.getWriter().getNickName());

        dto.setWriter(memberDto);
        dto.setCreatedAt(board.getCreatedAt());
        return dto;
    }

    public void remove(Integer id) {
        boardRepository.deleteById(id);
    }

    public void update(BoardForm data) {
        // 조회
        Board board = boardRepository.findById(data.getId()).get();
        // 수정
        board.setTitle(data.getTitle());
        board.setContent(data.getContent());
        // 저장
        boardRepository.save(board);
    }
}
