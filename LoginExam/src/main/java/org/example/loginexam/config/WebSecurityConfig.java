package org.example.loginexam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * packageName : org.example.loginexam.config
 * fileName : WebSecurityConfig
 * author : PC
 * date : 2024-03-26
 * description : todo 스프링 시큐리티
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-26         PC          최초 생성
 */
@Configuration // 자바 클래스를 설정 파일로 만들어주는 어노테이션
public class WebSecurityConfig {

    // 1) DB인증을 하는 클래스 (당연히 DB에 있어야 올바른 사람이므로 당연히 있어야한다.) :
    // 2) 패스워드 암호화 함수  :
    // 3) 스프링 시큐리티 규칙 정의 (권한 관리):
    //   관리자와 일반 유저가 볼 수 있는 페이지를 관리하는 것, 예) 부서는 일반 유저가 볼수 있음, 갤러리는 관리자만 볼수있음

    // todo 스프링 시큐리티 : default 값이 모든 요청에 대해 보안 모드로 적용된다.
    //  => 로그인 화면 (기본화면: 스프링 시큐리티가 제공한다. 하지만 허접해서 개발자가 다시 만듬)
    //  => webSecurityConfig.java의 filterChain() 에서 인증 설정하면 로그인 없이 화면을 볼 수 있다.

    //      TODO: 인증 설정 부분 : 1) authorize      : 권한 설정(인가)
    //                              (권한에 따라 화면을 볼 수 있는 설정)
    //                           2) authentication : 인증 (로그인 : id/password)

    @Bean // 자바 설정 파일의 함수 위에 붙어서 ioc(스프링컨테이너에 객체를 생성)을 해준다.
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // todo : 권한관리
        http.authorizeRequests() // 권한 설정 부분이다. (권한에 따라 화면을 볼 수 있게 해줌) 스프링 시큐리티에는 권한설정과 인증(로그인)을 동시에 할 수 있게 해준다.
                /*.requestMatchers("/**").permitAll()*/ // root뒤에 나오는 모든 접근은 다 허용한다는 뜻. 즉, 모든 사람에게 접근허용
                .requestMatchers("/auth/**").permitAll() // 여러개 계속 사용할 수 있다. 이건 auth 밑의 경로는 다 허용한다는 뜻(이상은 안됌)
                .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN 역할을 가진 사용자만 접근 권한 허용
                .requestMatchers("/basic/**").permitAll() // 모든 사용자 접근 허용
                .anyRequest().authenticated(); // 나머지는 로그인 사용자만 허용

        //        TODO: 2) 인증(로그인/로그아웃) 관리 : 쿠키/세션 방식(스프링시큐리티에서 자동 관리)
        //          1) 로그인 설정
        http.formLogin() // 스프링 시큐리티에서 제공하는 로그인화면
                .loginPage("/auth/customLogin") // 기본 로그인화면 대신 개발자가 만든 화면 사용하게 해주는 함수
                .loginProcessingUrl("/auth/login") // 로그인 버튼 클릭시 실행될 함수 url (스프링시큐리티가 자동 실행하므로 controller 를 따로 만들 필요가 없다. 대신 기본정보(user 객체 정보를 제공해야한다.)
                .defaultSuccessUrl("/"); // 로그인 성공하면 강제이동할 페이지 url

        //        TODO: 2) 로그아웃 설정
        http.logout() // 로그아웃 처리진행 : 스프링 시큐리티가 자동 진행
                .logoutUrl("/auth/customLogout") //jsp에서 클릭시 로그아웃 이 url이 실행된다. post 방식 숨겨서 백엔드 내에서 보내주는 것(로그인/로그아웃 동일)
                .invalidateHttpSession(true) // 로그아웃이 되면 세션 id를 뺏어야한다. 이것을 해주는 코드이고 스프링 시큐리티가 해준다.
                .logoutSuccessUrl("/"); // 로그아웃 성공하면 강제이동할 페이지 url이다.

        return http.build();

    }
}
