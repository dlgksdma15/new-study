package com.twoteam.toyproject.controller;

import com.twoteam.toyproject.dto.MemberDTO;
import com.twoteam.toyproject.entity.Member;
import com.twoteam.toyproject.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String index() {
        return "index"; // 로그인 페이지로 반환
    }

    @GetMapping("/register")
    public String saveForm(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "register"; // 회원가입 페이지로 이동
    }

    @PostMapping("/register")
    public String registerMember(MemberDTO memberDTO, Model model) {
        try {
            memberService.registerMember(memberDTO);
            return "redirect:/";  // 회원가입 성공 후 로그인 페이지로 리다이렉트
        } catch (Exception e) {
            model.addAttribute("errorMessage", "회원가입에 실패했습니다. 다시 시도해주세요.");
            return "register"; // 회원가입 페이지에 오류 메시지를 포함하여 다시 이동
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String memberEmail, @RequestParam String memberPW, Model model, HttpSession session) {
        Member member = memberService.findMemberByEmailAndPassword(memberEmail, memberPW);

        if (member != null) {
            // 로그인 성공 - 세션에 사용자 정보 저장
            session.setAttribute("loggedInUser", member);

            // 세션에 loginMember 속성 추가
            MemberDTO loginMember = new MemberDTO();
            loginMember.setMemberEmail(member.getMemberEmail());
            session.setAttribute("loginMember", loginMember);

            return "redirect:/timer"; // 로그인 성공 시 타이머 페이지로 리다이렉트
        } else {
            // 로그인 실패
            model.addAttribute("errorMessage", "이메일 또는 비밀번호가 잘못되었습니다.");
            return "index"; // 로그인 페이지에 오류 메시지를 포함하여 다시 이동
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에서 사용자 정보 제거
        session.invalidate();
        return "redirect:/"; // 로그아웃 후 로그인 페이지로 리다이렉트
    }
}
