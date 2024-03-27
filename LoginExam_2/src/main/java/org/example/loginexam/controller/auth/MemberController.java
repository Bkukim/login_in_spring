package org.example.loginexam.controller.auth;

import lombok.extern.slf4j.Slf4j;
import org.example.loginexam.service.auth.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName : org.example.loginexam.controller.auth
 * fileName : MemberController
 * author : PC
 * date : 2024-03-27
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-27         PC          최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/auth")
public class MemberController {
    // 예전에는 생략이 가능했는데 이제는 필수가 됌 패스워드를 암호화 객체가 필수가 되었다.

    private final MemberService memberService;


    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    // todo : 로그인 : 1) 로그인 페이지 열기 함수
    @GetMapping("/customLogin")
    public String login(){
        return "auth/customLogin.jsp";
    }

    // todo : 로그인 : 2) 로그인 버튼 클릭시 실행될 함수(이건 스프링이 해주니 안 만들어도 됌)
    //                => jsp : form action="/auth/login" = WebSecurityConfig.java의 filerChain() 함수안에 정의했다.
    //                => DB에 사용자가 있는지 확인하는 함수를 제작해야 스프링이 알아서 로그인을 처리할 때 이 함수를 실행에 DB를 인식하고 id/pw를 DB와 비교할 수 있게된다.
    //                     - 스프링은 DB에서 어떤 테이블에 id/pw가 있는지를 모르기 때문에 쿼리문을 작성해서 이 테이블이 무엇인지 인식시켜주는 것.
    //                => DB 확인해서 정상사용자인지 검증이 끝나면 => 검증객체에 넣어준다(검증을 통과했다는 표시).

}
