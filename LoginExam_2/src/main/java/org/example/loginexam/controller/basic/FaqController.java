package org.example.loginexam.controller.basic;

import org.example.loginexam.model.entity.basic.Faq;
import org.example.loginexam.service.basic.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

/**
 * packageName : org.example.jpaexam.controller.basic
 * fileName : FaqController
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
@Controller
@RequestMapping("/basic")
public class FaqController {

    private final FaqService faqService;

    @Autowired
    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping("/faq")
    public String getFaqAll(
            @RequestParam (defaultValue = "") String title,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "3")int size,
            Model model
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<Faq> faqPage = faqService.findAllByTitleContaning(title, pageable);


        model.addAttribute("faq", faqPage.getContent());
        model.addAttribute("currentPage", faqPage.getNumber());
        model.addAttribute("totalItems", faqPage.getTotalElements());
        model.addAttribute("totalPages", faqPage.getTotalPages());

        long blockStartPage = (long) Math.floor((double) (faqPage.getNumber()/size)*size);
        model.addAttribute("startPage", blockStartPage);

        long blockEndPage = blockStartPage + size -1;
        blockEndPage = (blockEndPage >= faqPage.getTotalPages())? faqPage.getTotalPages() - 1 : blockEndPage;
        blockEndPage = (blockEndPage<0)? 0 : blockEndPage;
        model.addAttribute("endPage", blockEndPage);
        return "basic/faq/faq_all.jsp";
    }

    @GetMapping("/faq/addition")
    public String additionFaq (Model model){
        return "/basic/faq/add_faq.jsp";
    }

    @PostMapping("/faq/save")
    public RedirectView saveFaq (@ModelAttribute Faq faq){
        faqService.save(faq);
        return new RedirectView("/basic/faq");
    }

    @GetMapping("/faq/edition/{fno}")
    public String edition(Model model, @PathVariable int fno){
        Optional<Faq> faq = faqService.findByFno(fno);
        model.addAttribute("faq1", faq.get());
        return "basic/faq/edit_faq.jsp";
    }

    @DeleteMapping("/faq/delete/{fno}")
    public RedirectView deleteFaq(Model model, @PathVariable int fno){
        faqService.delete(fno);
        return new RedirectView("/basic/faq");
    }

}
