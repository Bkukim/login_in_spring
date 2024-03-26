package org.example.loginexam.controller.basic;

import org.example.loginexam.model.entity.basic.Emp;
import org.example.loginexam.service.basic.EmpService;
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
 * fileName : EmpController
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
@Controller
@RequestMapping("/basic")
public class EmpController {

    private final EmpService empService;

    @Autowired
    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping("/emp")
    public String getEmpAll(Model model,
                            @RequestParam(defaultValue = "") String ename,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "3") int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Emp> pageRes = empService.findByEnameContaning(ename, pageable);

        model.addAttribute("emp", pageRes.getContent());
        model.addAttribute("currentPage", pageRes.getNumber());
        model.addAttribute("totalItems", pageRes.getTotalElements());
        model.addAttribute("totalPages", pageRes.getTotalPages());

        long blockStartPage = (long)Math.floor((double)pageRes.getNumber()/size)*size;
        model.addAttribute("startPage", blockStartPage);

        long blockEndPage = blockStartPage + size -1;
        blockEndPage = (blockEndPage>=pageRes.getTotalPages())? pageRes.getTotalPages()-1 : blockEndPage;
        blockEndPage = (blockEndPage<0)? 0:blockEndPage;
        model.addAttribute("endPage", blockEndPage);
        return "/basic/emp/emp_all.jsp";
    }

    @GetMapping("/emp/{eno}")
    public String getEmpId(Model model, @PathVariable int eno){
        Optional<Emp> emp = empService.findByEno(eno);
        model.addAttribute("emp", emp.get());
        return "/basic/emp/emp_id.jsp";
    }

    @GetMapping("/emp/addition")
    public String additionEmp(Model model){
        return "basic/emp/add_emp.jsp";
    }

    @PostMapping("/emp/save")
    public RedirectView saveEmp(Model model, @ModelAttribute Emp emp){
        empService.save(emp);
        return new RedirectView("/basic/emp");
    }

    @GetMapping("/emp/edition/{eno}")
    public String edition (@PathVariable int eno, Model model){
        Optional<Emp> emp = empService.findByEno(eno);
        model.addAttribute("emp", emp.get());
        return "/basic/emp/edit_emp.jsp";
    }

    @PutMapping("/emp/edit/{eno}")
    public RedirectView updateEmp(@ModelAttribute Emp emp){
        empService.save(emp);
        return new RedirectView("/basic/emp");
    }

    @DeleteMapping("/emp/delete/{eno}")
    public RedirectView deleteEmp(@PathVariable int eno){
        empService.delete(eno);
        return new RedirectView("/basic/emp");
    }

}
