package org.example.loginexam.service.basic;

import org.example.loginexam.model.entity.basic.Dept;
import org.example.loginexam.repository.basic.DeptRepository;
import org.example.loginexam.model.entity.basic.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.jpaexam.service.basic
 * fileName : DeptService
 * author : GGG
 * date : 2024-03-19
 * description : CRUD DB 함수를 실행하는 서비스 함수 정의
 * 요약 :
 *      목적 : MVC 디자인 패턴에 의해 역할에 따라 자바 클래스를 정의함
 *      @Service, @Repository, 등 : IOC, 스프링 서버가 실행될때
 *          클래스를 생성해줌
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-19         GGG          최초 생성
 */
@Service
public class DeptService {

//    DB CRUD 클래스 받기 : JPA 제공 함수 사용 가능
    @Autowired
    DeptRepository deptRepository;

    /**
     * 전체 조회 : 페이징 없음
     * @return 부서배열
     */
    public List<Dept> findAll() {
//        JPA 전체조회 함수 실행 : select 문 자동 작성
        List<Dept> list = deptRepository.findAll();
        return list;
    }

    /**
     * 전체 조회 : 페이징 처리
     *
     * @param dname
     * @param pageable
     * @return 부서배열
     */
    // DB like 검색함수 실행 : 페이징 처리
    public Page<Dept> findAllByDnameContaning(String dname, Pageable pageable){
        Page<Dept> page = deptRepository.findAllByDnameContaining(dname, pageable);
        return page;
    }

    public Optional<Dept> findById(int dno){// optional을 하면 null이 방지된다.
        Optional<Dept> optionalDept
                = deptRepository.findById(dno);
        return optionalDept;
    }
    public Dept save(Dept dept){
        Dept dept2 = deptRepository.save(dept);
        return dept2;
    }

    public boolean deleteById(int dno){
        if (deptRepository.existsById(dno) == true) {
            deptRepository.deleteById(dno);
            return true;
        }else {
            return false;
        }
    }
}
