package com.example.firstproject.controller;
// 메모장 내용에 따라 시키는대로 입력하이소.

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j // 로깅 기능을 위한 어노테이션
@Controller // 컨트롤러 선언
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해 놓은 레파지토리 객체 주입(DI)
    // ArticleRepository 객체 선언
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new") // URL 요청 접수
    public String newArticleForm() { // 메서드 생성 및 반환값 작성
        return "articles/new";
    }
    @PostMapping("/articles/create") // URL 요청 접수
    public String createArticle(ArticleForm form) { // 폼 데이터를 DTO로 받기
        log.info(form.toString());
        // System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겼는지 확인
        // 1. DTO를 엔티티로 변환
        // DTO를 엔티티로 변환하기 위해 form객체의 toEntity()라는 메서드를 호출해서 그 반환값을 Article 타입의 article 엔티티에 저장한다.
        Article article = form.toEntity();
        log.info(article.toString());
        // System.out.println(article.toString()); // DTO가 엔티티로 잘 변환되는지 확인 출력
        // 2. 레파지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        // System.out.println(saved.toString()); // article이 DB에 잘 저장되는지 확인 출력
        return "";
    }
}
