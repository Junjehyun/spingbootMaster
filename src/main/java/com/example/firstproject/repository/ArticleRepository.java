package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

// CrudRepository라는 인터페이스를 상속받는 명령
// CrudRepository는 JPA에서 제공하는 인터페이스로 이를 상속해 엔티티를 관리 (생성, 조회,
// 수정, 삭제) 할 수 있다. <>를 붙이고 그 다음에 2개의 제네릭 요소를 받는다.

// Article: 관리 대상 엔티티의 클래스 타입.
// Long: 관리 대상 엔티티의 대표값 타입.
public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    Iterable<Article> findAll(); // Iterable -> ArrayList로 수정
}
