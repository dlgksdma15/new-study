package com.twoteam.toyproject.entity;
import jakarta.persistence.*;

@Entity
public class Practice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // null
    @Column
    private String title; // 안녕
    @Column
    private String content; // 방가

    public Practice(Long id, String title, String content) {
        this.id = id; // null
        this.title = title; // 안녕
        this.content = content; // 방가
    }

    @Override
    public String toString() {
        return "Practice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
