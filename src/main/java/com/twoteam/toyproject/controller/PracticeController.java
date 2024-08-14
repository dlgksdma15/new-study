package com.twoteam.toyproject.controller;

import com.twoteam.toyproject.dto.PracticeDTO;
import com.twoteam.toyproject.entity.Practice;
import com.twoteam.toyproject.repository.PracticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class PracticeController {
    @Autowired // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입
    private PracticeRepository practiceRepository;
    @GetMapping("/practice")
    public String practice(){
        return "practice";
    }
    @PostMapping("/practicecreate")
    public String practicecreate(PracticeDTO dto){
        System.out.println(dto.toString());
        //1.DTO를 엔티티로 변환
        Practice practice = dto.toEntity(); // practice에 dto를 entity로 변환해서 저장
        System.out.println(practice.toString());
        //2.리파지터리로 엔티티를 DB에 저장
        Practice saved = practiceRepository.save(practice);
        System.out.println(saved.toString());
        return "";
    }
    @GetMapping("/practice/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id= " + id);
        // 1. id를 조회해 데이터 가져오기
        Practice practiceEntity = practiceRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("practice",practiceEntity);
        return "practice/show";
    }

}
