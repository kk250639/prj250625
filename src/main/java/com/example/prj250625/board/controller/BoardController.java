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

        var result = boardService.list(page);
        model.addAllAttributes(result);

        return "board/list";
    }

    @GetMapping("view")
    public String view(Integer id, Model model) {

        // service에게 일 시키고
        var dto = boardService.get(id);

        // model에 넣고
        model.addAttribute("board", dto);
        // view로 forward
        return "board/view";
    }
}
