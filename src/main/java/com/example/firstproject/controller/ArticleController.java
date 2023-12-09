package com.example.firstproject.controller;
// 메모장 내용에 따라 시키는대로 입력하이소.

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


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
        return "redirect:/articles/" + saved.getId();
    }

        @GetMapping("/articles/{id}")
        public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id); // id를 잘 받았는지 확인하는 로그 찍기
            // 1. id를 조회해 데이터 가져오기
            Article articleEntity = articleRepository.findById(id).orElse(null);
            // 2. 모델에 데이터 등록하기
            model.addAttribute("article", articleEntity);
            // 3. 뷰 페이지 반환하기
            return "articles/show";
        }

        @GetMapping("/articles")
        public String index(Model model) {
            // 1. 모든 데이터 가져오기
            List<Article> articleEntityList = (List<Article>) articleRepository.findAll();
            // 2. 모델에 데이터 등록하기
            model.addAttribute("articleList", articleEntityList);
            // 3. 뷰 페이지 설정하기
            return"articles/index";
        }

        @GetMapping("/articles/{id}/edit")
        public String edit(@PathVariable Long id, Model model) {
            // 수정할 데이터 가져오기
            Article articleEntity = articleRepository.findById(id).orElse(null);
            // 모델에 데이터 등록하기
            model.addAttribute("article", articleEntity);
            //뷰 페이지 설정하기
            return "articles/edit";
        }

        // URL 요청 접수
        @GetMapping("/articles/{id}/delete")
        public String delete(@PathVariable Long id, RedirectAttributes rttr) {
            log.info("삭제 요청이 들어왔습니다!!");
            // 1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null); // Search for data
            log.info(target.toString());
            // 2. 대상 엔티티 삭제하기
        if (target != null) { // 삭제할 대상이 있는지 확인
            articleRepository.delete(target); // delete()메서드로 대상 삭제
            rttr.addFlashAttribute("msg", "삭제됐슴니당");
        }
            // 3. 결과 페이지로 리다이렉트하기
            return "redirect:/articles";
        }
}
