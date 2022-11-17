package com.hyunwoo.board.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity // Entity 클래스는 실제 DB테이블과 매핑되는 핵심 클래스.(DB의 테이블과 1:1매핑)
        // Entity는 데이터베이스 영속성의 목적으로 사용되는 객체 
        //      -> DB로부터 조회된 Entity를 그대로 View로 넘기게 되었을 때 불필요한 정보 및 노출되면 안되는 정보까지 노출될 수 있음
        //          --> Entity를 Dto로 변환시켜 Dto가 View와 Controller 사이에서 데이터를 주고 받는다.
        // 테이블과 매핑되는 Entity 클래스가 변경되면 여러 클래스에 영향을 끼치게 되는 반면 View와 통신하는 DTO 클래스(Request / Response 클래스)는 자주 변경되므로 분리한다.
@Table(name = "board")
public class BoardEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    public BoardEntity(Long id, String title, String content, String writer) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}