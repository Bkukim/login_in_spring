package org.example.loginexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName : org.example.jpaexam.controller.basic
 * fileName : HomeController
 * author : PC
 * date : 2024-03-22
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-22         PC          최초 생성
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome(){
        return "index.jsp";
    }
}
