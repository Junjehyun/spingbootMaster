package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 클래스 안쪽의 모든 필드를 매개변수로 하는 생성자가 자동으로 만들어진다.
@ToString // toString() 메서드를 사용하는것과 같은 효과
public class ArticleForm {
    private Long id;
    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드

    // Article.java 생성자 입력 양식에 맞게 작성한다.
    public Article toEntity() {
        return new Article(null, title, content);
    }
}
