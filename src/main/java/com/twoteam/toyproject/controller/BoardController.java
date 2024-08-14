package com.twoteam.toyproject.controller;

import com.twoteam.toyproject.dto.BoardDTO;
import com.twoteam.toyproject.entity.Board;
import com.twoteam.toyproject.entity.Member;
import com.twoteam.toyproject.repository.BoardRepository;
import com.twoteam.toyproject.repository.MemberRepository;
import com.twoteam.toyproject.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardService boardService;

    private boolean addUserToModelIfLoggedIn(HttpSession session, Model model) {
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("memberName", loggedInUser.getMemberName());
            return true;
        }
        return false;
    }

    @GetMapping("/board")
    public String board(HttpSession session, Model model) {
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            List<Board> boardList = boardService.findAll(); // 모든 게시글을 가져오는 서비스 메서드 호출
            model.addAttribute("boardList", boardList);
            model.addAttribute("memberName", loggedInUser.getMemberName());
            return "board/board"; // 대시보드 페이지로 이동
        } else {
            return "redirect:/"; // 로그인 상태가 아니면 로그인 페이지로 리다이렉트
        }
    }

    @GetMapping("/board/write")
    public String boardWrite(HttpSession session, Model model) {
        if (addUserToModelIfLoggedIn(session, model)) {
            return "board/write";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/boardcreate")
    public String boardCreate(BoardDTO boardDTO, HttpSession session, Model model) {
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            boardDTO.setMemberId(loggedInUser.getIdx());
            // findById
            Member member = memberRepository.findById(boardDTO.getMemberId())
                    .orElseThrow(() -> new RuntimeException("Member not found"));

            Board board = boardDTO.toEntity(member);
            boardRepository.save(board);

            return "redirect:/board";
        } else {
            return "redirect:/";
        }
    }
    @GetMapping("/board/{id}")
    public String show(@PathVariable Long id, HttpSession session, Model model) {
        log.info("id = " + id);
        // 로그인된 사용자를 확인하고, memberName을 모델에 추가합니다.
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("memberName", loggedInUser.getMemberName());
        } else {
            log.warn("No user is logged in.");
            return "redirect:/login"; // 로그인 페이지로 리다이렉트 (로그인 필요 시)
        }
        // 게시물을 ID로 찾고, 찾지 못하면 예외를 던집니다.
        Board boardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        // 찾은 게시물을 모델에 추가합니다.
        model.addAttribute("board", boardEntity);
        // 게시물 상세보기 페이지로 이동합니다.
        return "board/show";
    }
    @GetMapping("/board/{id}/edit")
    public String edit(@PathVariable Long id, HttpSession session, Model model){
        // 로그인된 사용자를 확인하고, memberName을 모델에 추가합니다.
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("memberName", loggedInUser.getMemberName());
        } else {
            log.warn("No user is logged in.");
            return "redirect:/login"; // 로그인 페이지로 리다이렉트 (로그인 필요 시)
        }
        // 수정할 데이터 가져오기
        Board boardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        // 모델에 데이터 등록하기
        model.addAttribute("board",boardEntity);
        // 뷰 페이지 설정하기 //
        return "board/edit";
    }
    @PostMapping("/board/update")
    public String update(@RequestParam Long id, BoardDTO boardDTO, HttpSession session, Model model) {

        // 로그인된 사용자를 확인하고, memberName을 모델에 추가합니다.
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("memberName", loggedInUser.getMemberName());
        } else {
            log.warn("No user is logged in.");
            return "redirect:/login"; // 로그인 페이지로 리다이렉트 (로그인 필요 시)
        }
        // 수정할 데이터를 ID로 가져오기
        Board boardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        // DTO 데이터를 엔티티에 반영
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        // 엔티티를 DB에 저장(갱신)
        boardRepository.save(boardEntity);
        // 수정 결과 페이지로 리다이렉트
        return "redirect:/board/" + boardEntity.getId();
    }


    @GetMapping("/board/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr,BoardDTO boardDTO, HttpSession session, Model model){
        log.info("삭제 요청이 들어왔습니다!!");
        // 로그인된 사용자를 확인하고, memberName을 모델에 추가합니다.
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("memberName", loggedInUser.getMemberName());
        } else {
            log.warn("No user is logged in.");
            return "redirect:/login"; // 로그인 페이지로 리다이렉트 (로그인 필요 시)
        }
        // 1.삭제할 대상 가져오기
        Board target = boardRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2.대상 엔티티 삭제하기
        if(target != null){
            boardRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제됐습니다!");
        }
        // 3.결과 페이지로 리다이렉트하기
        return "redirect:/board";
    }
}
