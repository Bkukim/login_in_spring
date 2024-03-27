package org.example.loginexam.repository.basic;

import org.example.loginexam.model.entity.basic.Faq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.jpaexam.repository.basic
 * fileName : FaqRepository
 * author : PC
 * date : 2024-03-21
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-21         PC          최초 생성
 */
@Repository
public interface FaqRepository extends JpaRepository<Faq, Integer> {

    @Query(value = "SELECT F.* FROM TB_FAQ F\n" +
            "WHERE F.TITLE LIKE UPPER('%'|| :title ||'%')"
            ,countQuery = "SELECT count(*) FROM TB_FAQ F\n" +
            "WHERE F.TITLE LIKE UPPER('%제목%')"
           ,nativeQuery = true)
    public Page<Faq> findAllByTitleContaining (@Param("title") String title, Pageable pageable);
}
