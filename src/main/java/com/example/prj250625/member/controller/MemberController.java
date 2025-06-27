package com.example.prj250625.member.controller;

import com.example.prj250625.member.dto.MemberDto;
import com.example.prj250625.member.dto.MemberForm;
import com.example.prj250625.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String view(Model model,
                       String id,
                       RedirectAttributes rttr,
                       @SessionAttribute(value = "loggedInUser", required = false)
                       MemberDto user) {

        MemberDto member = memberService.get(id);

        if (user != null) {
            if (member.getId().equals(user.getId())) {
                model.addAttribute("member", member);

                return "member/view";
            }
        }
        rttr.addFlashAttribute("alert",
                Map.of("code", "warning", "message", "권한이 없습니다."));

        return "redirect:/board/list";
    }

    @PostMapping("remove")
    public String remove(MemberForm data,
                         @SessionAttribute(value = "loggedInUser", required = false)
                         MemberDto user,
                         HttpSession session,
                         RedirectAttributes rttr) {
        // TODO
        boolean result = memberService.remove(data, user);

        if (result) {
            rttr.addFlashAttribute("alert", Map.of("code", "danger",
                    "message", data.getId() + "님 탈퇴 되었습니다."));

            session.invalidate();
            return "redirect:/board/list";
        } else {
            rttr.addFlashAttribute("alert", Map.of("code", "danger",
                    "message", "암호가 일치하지 않습니다."));

            rttr.addAttribute("id", data.getId());

            return "redirect:/member/view";
        }
    }

    // TODO
    @GetMapping("edit")
    public String edit(Model model, String id,
                       @SessionAttribute(value = "loggedInUser", required = false)
                       MemberDto user,
                       RedirectAttributes rttr) {
        MemberDto member = memberService.get(id);
        if (user != null) {
            if (member.getId().equals(user.getId())) {
                model.addAttribute("member", member);
                return "member/edit";
            }
        }
        rttr.addFlashAttribute("alert", Map.of("code", "warning",
                "message", "권한이 없습니다."));
        return "redirect:/board/list";
    }

    @PostMapping("edit")
    public String edit(MemberForm data,
                       @SessionAttribute(value = "loggedInUser", required = false)
                       MemberDto user,
                       HttpSession session,
                       RedirectAttributes rttr) {

        boolean result = memberService.update(data, user, session);

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
                                 @SessionAttribute(value = "loggedInUser", required = false)
                                 MemberDto user,
                                 RedirectAttributes rttr) {

        if (user != null && user.getId().equals(id)) {

            boolean result = memberService.updatePassword(id, oldPassword, newPassword);

            if (result) {
                rttr.addFlashAttribute("alert",
                        Map.of("code", "success", "message", "암호가 변경되었습니다."));
            } else {
                rttr.addFlashAttribute("alert",
                        Map.of("code", "warning", "message", "암호가 일치하지 않습니다."));
            }
        }
        rttr.addAttribute("id", id);
        return "redirect:/member/edit";
    }

    @GetMapping("login")
    public String loginForm(@CookieValue(value = "rememberId", required = false)
                            String rememberId,
                            Model model) {
        model.addAttribute("id", rememberId);
        return "member/login";
    }

    @PostMapping("login")
    public String loginProcess(String id, String password,
                               @RequestParam(value = "remember", required = false)
                               String remember, // ← 추가
                               HttpSession session,
                               RedirectAttributes rttr,
                               HttpServletResponse response) {

        boolean result = memberService.login(id, password, session);

        if (result) {
            if (remember != null) {
                Cookie rememberCookie = new Cookie("rememberId", id);
                rememberCookie.setMaxAge(Integer.MAX_VALUE);
                rememberCookie.setPath("/");
                response.addCookie(rememberCookie);
            } else {
                Cookie deleteCookie = new Cookie("rememberId", null);
                deleteCookie.setMaxAge(0);
                deleteCookie.setPath("/");
                response.addCookie(deleteCookie);
            }
            rttr.addFlashAttribute("alert",
                    Map.of("code", "success", "message", "로그인 되었습니다."));
            return "redirect:/board/list";
        } else {
            rttr.addFlashAttribute("alert",
                    Map.of("code", "warning", "message", "아이디/패스워드가 일치하지 않습니다."));
            rttr.addFlashAttribute("id", id);
            // 로그인 실패
            return "redirect:/member/login";
        }
    }

    @RequestMapping("logout")
    public String logout(HttpSession session, RedirectAttributes rttr) {
        session.invalidate();

        rttr.addFlashAttribute("alert",
                Map.of("code", "success", "message", "로그아웃 되었습니다."));

        return "redirect:/member/login";
    }


}
