package com.example.prj250625.member.controller;

import com.example.prj250625.member.dto.MemberDto;
import com.example.prj250625.member.dto.MemberForm;
import com.example.prj250625.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
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

        try {
            memberService.add(data);

            rttr.addFlashAttribute("alert", Map.of("code", "success",
                    "message", "회원 가입되었습니다."));

            return "redirect:/board/list";
        } catch (DuplicateKeyException e) {

            rttr.addFlashAttribute("alert", Map.of("code", "warning",
                    "message", e.getMessage()));

            rttr.addFlashAttribute("member", data);

            return "redirect:/member/signup";
        }

    }

    @GetMapping("list")
    public String list(Model model) {
        model.addAttribute("memberList", memberService.list());

        return "member/list";
    }

    @GetMapping("view")
    public String view(Model model, String id) {
        model.addAttribute("member", memberService.get(id));

        return "member/view";
    }

    @PostMapping("remove")
    public String remove(MemberForm data, RedirectAttributes rttr) {

        boolean result = memberService.remove(data);

        if (result) {
            rttr.addFlashAttribute("alert", Map.of("code", "danger",
                    "message", data.getId() + "님 탈퇴 되었습니다."));

            return "redirect:/board/list";
        } else {
            rttr.addFlashAttribute("alert", Map.of("code", "danger",
                    "message", "암호가 일치하지 않습니다."));

            rttr.addAttribute("id", data.getId());

            return "redirect:/member/view";
        }
    }

    @GetMapping("edit")
    public String edit(Model model, String id) {
        model.addAttribute("member", memberService.get(id));
        return "member/edit";
    }

    @PostMapping("edit")
    public String edit(MemberForm data, RedirectAttributes rttr) {

        boolean result = memberService.update(data);

        if (result) {

            rttr.addFlashAttribute("alert",
                    Map.of("code", "success", "message", "회원 정보가 변경되었습니다."));

            rttr.addAttribute("id", data.getId());

            return "redirect:/member/view";
        } else {
            rttr.addAttribute("id", data.getId());
            rttr.addFlashAttribute("alert",
                    Map.of("code", "warning", "message", "암호가 일치하지 않습니다."));

            return "redirect:/member/edit";
        }
    }

    @PostMapping("changePw")
    public String changePassword(String id,
                                 String oldPassword,
                                 String newPassword,
                                 RedirectAttributes rttr) {

        boolean result = memberService.updatePassword(id, oldPassword, newPassword);

        if (result) {
            rttr.addFlashAttribute("alert",
                    Map.of("code", "success", "message", "암호가 변경되었습니다."));
        } else {
            rttr.addFlashAttribute("alert",
                    Map.of("code", "warning", "message", "암호가 일치하지 않습니다."));
        }
        rttr.addAttribute("id", id);
        return "redirect:/member/view";
    }

    @GetMapping("login")
    public String loginForm() {

        return "member/login";
    }

    @PostMapping("login")
    public String loginProcess(String id, String password,
                               HttpSession session,
                               RedirectAttributes rttr) {

        boolean result = memberService.login(id, password, session);

        if (result) {
            rttr.addFlashAttribute("alert",
                    Map.of("code", "success", "message", "로그인 되었습니다."));
            // 로그인 성공
            return "redirect:/board/list";
        } else {
            rttr.addFlashAttribute("alert",
                    Map.of("code", "warning", "message", "아이디/패스워드가 일치하지 않습니다."));
            // 로그인 실패
            return "redirect:/member/login";
        }
    }

    @RequestMapping("logout")
    public String logout(HttpSession session, RedirectAttributes rttr) {
        session.invalidate();

        rttr.addFlashAttribute("alert",
                Map.of("code", "success", "message", "로그아웃 되었습니다."));

        return "redirect:/board/list";
    }


}
