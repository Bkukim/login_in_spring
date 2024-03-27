package org.example.loginexam.repository.basic;

import org.example.loginexam.model.entity.basic.Emp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.jpaexam.repository.basic
 * fileName : EmpRepository
 * author : PC
 * date : 2024-03-19
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-19         PC          최초 생성
 */
@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer> {

    @Query(
            value = "SELECT E.* FROM TB_EMP E\n" +
                    "WHERE E.ENAME LIKE UPPER('%'|| :ename ||'%')",
            countQuery = "SELECT COUNT (*) FROM TB_EMP E\n" +
                          "WHERE E.ENAME LIKE '%'|| :ename ||'%'",
            nativeQuery = true
    )
    public Page<Emp> findAllByEnameContaining(@Param("ename")String ename, Pageable pageable);
}
