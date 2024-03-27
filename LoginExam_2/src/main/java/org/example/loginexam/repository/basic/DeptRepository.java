package org.example.loginexam.repository.basic;

import org.example.loginexam.model.entity.basic.Dept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.jpaexam.repository.basic
 * fileName : DeptRepository
 * author : GGG
 * date : 2024-03-19
 * description : 레포지토리 클래스 : DB CRUD 함수가 있는 인터페이스
 * 요약 :
 *      CRUD 용어 : C(Create==Insert), R(Read==Select)
 *                 U(Update==Update), D(Delete=Delete)
 *                 DML(데이터 조작어) 문임
 *      사용법 : interface 이름 extends JpaRepository<엔티티명,기본키속성자료형>
 *          => 엔티티명        : DB 와 연결될 엔티티 클래스명
 *          => 기본키속성자료형 : 엔티티 클래스의 기본키 속성 자료형 명시
 *          => JPA 기본 함수 사용 가능
 *             - findAll()       : 전체 조회 , 자동 sql 문 생성
 *             - findById(기본키) : 상세 조회(1건), 자동 sql 문 생성
 *             - save(객체)       : 저장/수정을 알아서 실행함
 *                  저장 : 기본키가 없으면 insert
 *                  수정 : 기본키가 있으면 update
 *             - deleteById(기본키): 삭제 , 자동 sql 문 생성
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-19         GGG          최초 생성
 */
@Repository
public interface DeptRepository extends JpaRepository<Dept,Integer> {
    // todo 페이징 처리 like 검색어

    // like 검색 sql문 작성 :
    // 사용법 : @Query(value="sql", nativeQuery=true)
    @Query(value = "SELECT D.* FROM TB_DEPT D\n" +
                    "WHERE D.DNAME LIKE UPPER('%'|| :dname ||'%')"
            ,countQuery ="SELECT COUNT(*) FROM TB_DEPT D\n" +
                         "WHERE D.DNAME LIKE upper('%'|| :dname ||'%')"
            ,nativeQuery = true)                  // oracle 쿼리문을 사용가능하게 해준다. false일 경우 객체 sql(JPQL)을 사용해야한다.
    public Page<Dept> findAllByDnameContaining (@Param("dname") String dname, Pageable pageable);

    // 입력할 DNAME 으로 시작하는 것을 찾고 페이지 번호를 만들려면 전체 개수를 세는 쿼리가 필요하다.
    // 그래야 몇개인지를 알고 JPA가 알아서 페이지를 만들수 있기 때문

    //   Page<엔티티>객체 : 페이징된 결과값을 저장할 객체(함수의 리턴값으로 사용)
    //          - 예) 속성 : 현재페이지번호, 전체페이지건수 등
    //     Pageable   객체 : 페이징하기 위한 객체(함수의 매개변수로 사용)
}
