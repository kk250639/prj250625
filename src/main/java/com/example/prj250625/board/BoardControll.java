package com.example.prj250625.board;

import com.example.prj250625.board.dto.BoardForm;
import com.example.prj250625.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.invoke.MethodType;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardControll {


    private final BoardService boardService;

    @GetMapping("write")
    public String writeForm() {

        return "board/write";
    }

    @PostMapping("write")
    public String writePost(BoardForm data) {
        System.out.println("data = " + data);

        boardService.add(data);

        return "board/write";
    }

    @GetMapping("list")
    public String list(Model model) {

        var list = boardService.list();
        model.addAttribute("boardList", list);
        
        return "board/list";
    }
}
