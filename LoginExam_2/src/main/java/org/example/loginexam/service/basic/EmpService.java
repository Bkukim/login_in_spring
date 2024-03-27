package org.example.loginexam.service.basic;

import org.example.loginexam.model.entity.basic.Emp;
import org.example.loginexam.repository.basic.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.jpaexam.service.basic
 * fileName : EmpService
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
@Service
public class EmpService {

    private final EmpRepository empRepository;

    @Autowired
    public EmpService(EmpRepository empRepository) {
        this.empRepository = empRepository;
    }

    public List<Emp> findAll(){
        List<Emp> list = empRepository.findAll();
        return list;
    }

    public Page<Emp> findByEnameContaning(String dname, Pageable pageable){
        Page<Emp> page = empRepository.findAllByEnameContaining(dname, pageable);
        return page;
    }

    public Optional<Emp> findByEno(int eno){
        Optional<Emp> emp = empRepository.findById(eno);
        return emp;
    }

    public Emp save(Emp emp){
        Emp emp1 = empRepository.save(emp);
        return emp1;
    }

    public void delete(int eno){
        empRepository.deleteById(eno);
    }
}
