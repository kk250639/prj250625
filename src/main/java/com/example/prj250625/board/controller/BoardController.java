package com.example.prj250625.board.controller;

import com.example.prj250625.board.dto.BoardForm;
import com.example.prj250625.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {


    private final BoardService boardService;

    @GetMapping("write")
    public String writeForm() {

        return "board/write";
    }

    @PostMapping("write")
    public String writePost(BoardForm data) {
        System.out.println("data = " + data);

        boardService.add(data);

        return "redirect:/board/list";
    }

    @GetMapping("list")
    public String list(@RequestParam(defaultValue = "1")
                       Integer page,
                       Model model) {

        var list = boardService.list(page);
        model.addAttribute("boardList", list);

        return "board/list";
    }
}
