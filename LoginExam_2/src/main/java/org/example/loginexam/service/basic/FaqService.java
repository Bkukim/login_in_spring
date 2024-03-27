package org.example.loginexam.service.basic;

import org.example.loginexam.model.entity.basic.Faq;
import org.example.loginexam.repository.basic.FaqRepository;
import org.example.loginexam.model.entity.basic.Faq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : org.example.jpaexam.service.basic
 * fileName : FaqService
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
@Service
public class FaqService {

    private final FaqRepository faqRepository;

    @Autowired
    public FaqService(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }


    public Page<Faq> findAllByTitleContaning(@Param("title") String title, Pageable pageable){
        Page<Faq> page = faqRepository.findAllByTitleContaining(title, pageable);
        return page;
    }

    public Optional<Faq> findByFno(int fno){
        Optional<Faq> faq = faqRepository.findById(fno);
        return faq;
    }
    public Faq save(Faq faq){
        Faq faq1 = faqRepository.save(faq);
        return faq1;
    }

    public Boolean delete(int fno){
        if (faqRepository.existsById(fno)) {
            faqRepository.deleteById(fno);
            return true;
        } else {
            return false;
        }
    }

}
