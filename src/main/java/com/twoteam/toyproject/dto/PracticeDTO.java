package com.twoteam.toyproject.dto;

import com.twoteam.toyproject.entity.Practice;
import org.springframework.security.core.parameters.P;

public class PracticeDTO {
    private String title;
    private String content;

    public PracticeDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "PracticeDTO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Practice toEntity() {
        return new Practice(null,title,content);
    }
}