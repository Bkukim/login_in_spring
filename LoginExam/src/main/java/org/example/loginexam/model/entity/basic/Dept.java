package org.example.loginexam.model.entity.basic;

import jakarta.persistence.*;
import lombok.*;

import org.example.loginexam.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.jpaexam.model.entity.basic
 * fileName : Dept
 * author : GGG
 * date : 2024-03-18
 * description : DB 엔티티(JPA 용어) : 테이블과 똑같음
 * 요약 :
 *     TODO:
 *       - JPA :  SQL 을 자동생성해주는 기본 함수를 제공,
 *          복잡한 기능은 sql 직접 작성할 수 있는 기능을 제공 (불완전)
 *           + (실무) Querydsl 라이브러리 도움을 받음
 *       - vs Mybatis : 직접 개발자가 모든 sql 을 작성,
 *              작성시 가독성이 높음
 *       - JPA 어노테이션 
 *        (1) 클래스에 붙임
 *          1) @Entity : JPA 프레임워크 사용시 기능을 대상 클래스 부여하는 어노테이션
 *          2) @Table(name = "테이블명") : JPA ddl(테이블,인덱스등생성) 생성기능 사용시
 *              그 이름으로 DB 생성해주는 어노테이션
 *          3) @SequenceGenerator(
 *                    name = "시퀀스_제너레이터이름"
 *                    , sequenceName = "DB시퀀스명"
 *                    , initialValue = 초기값   ( 시퀀스 처음값 )
 *                    , allocationSize = 할당값 (JPA 공간에서 생성될 값: 보통 1 사용)
 *                : 오라클 DB 제품의 시퀀스를 JPA 에서 사용하기 위한 어노테이션,
 *                  클래스 위에 붙임
 *          4) @DynamicInsert(옵션) : 옵션 기능임, JPA 에서 insert SQL 자동 생성시
 *                  null 값 들어오는 컬럼은 제외하고 sql 작성해주는 어노테이션
 *                  예) insert into dept(dno, dname, loc)
 *                      values(1,2, null);
 *                      => insert into dept(dno, dname)
 *                         values(1,2); // 이렇게 바뀜(null 에러 방지)
 *           5) @DynamicUpdate(옵션) : 옵션 기능임, JPA 에서 update SQL 자동 생성시
 *  *                  null 값 들어오는 컬럼은 제외하고 sql 작성해주는 어노테이션
 *           6) 기타 : 롬북 어노테이션 상황에따라 추가 (setter/getter 등)
 *         (2) 속성(필드)에 붙임
 *           1) @Id : 속성(필드) 기본키를 정의하는 어노테이션 (필수)
 *           2) @GeneratedValue(strategy = GenerationType.SEQUENCE
 *                 , generator = "시퀀스_제너레이터이름"
 *                 : 시퀀스를 어느 속성(필드)에 연결할 것인가를 지시하는 어노테이션
 *                   보통 기본키 컬럼(속성)에 사용함
 *           3) @Column(columnDefinition = "DB컬럼자료형")
 *              : 생략가능, JPA 의 ddl(테이블,인덱스등) 생성기능을 사용한다면 DB 테이블을 만들때
 *                  지시된 자료형으로 생성함
 *
 *     )
 * )
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-18         GGG          최초 생성
 */
// 아래 어노테이션 : JPA 어노테이션임
@Entity
@Table(name = "TB_DEPT")
@SequenceGenerator(
        name = "SQ_DEPT_GENERATOR"
        , sequenceName = "SQ_DEPT"
        , initialValue = 1
        , allocationSize = 1
)
@DynamicInsert
@DynamicUpdate
// 롬북 어노테이션 : setter, getter, 생성자, tostring 등
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dept extends BaseTimeEntity {
//    부서 속성필드
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_DEPT_GENERATOR"
    )
    private Integer dno;   // 부서번호(기본키)

    private String  dname; // 부서명

    private String  loc;   // 부서위치

}
