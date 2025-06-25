package com.example.prj250625.member.controller;

import com.example.prj250625.member.dto.MemberForm;
import com.example.prj250625.member.service.MemberService;
import com.sun.source.tree.MemberSelectTree;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("signup")
    public String signupForm() {

        return "member/signup";
    }

    @PostMapping("signup")
    public String signup(MemberForm data, RedirectAttributes rttr) {
        // service

        memberService.add(data);

        rttr.addFlashAttribute("alert", Map.of("code", "success",
                "message", "회원 가입되었습니다."));

        return "redirect:/board/list";
    }

}
