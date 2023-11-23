package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
@Entity // 엔티티 선언
public class Article {
    @Id // 엔티티의 대표값 지정
    @GeneratedValue // 자동 생성 기능 추가(숫자가 자동으로 매겨짐)
    private Long id;

    @Column // title 필드 선언, DB 테이블의 title 열과 연결됨
    private String title;

    @Column // content 필드 선언, DB 테이블의 content 열과 연결됨
    private String content;

}