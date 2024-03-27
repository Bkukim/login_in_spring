package org.example.loginexam.security.services;

import org.example.loginexam.model.entity.auth.Member;
import org.example.loginexam.repository.auth.MemberRepository;

import org.example.loginexam.security.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * packageName : org.example.loginexam.security.services
 * fileName : UserDetailsServiceImpl
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
@Service
public class UserDetailsServiceImpl implements UserDetailsService { // 인터페이스라 구현을 해야함,DB에 사용자가 있는지를 확인하는 클래스

    private final MemberRepository memberRepository;

    @Autowired
    public UserDetailsServiceImpl(MemberRepository memberRepositoryl) {
        this.memberRepository = memberRepositoryl;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //todo 1) 유저 DB 인증 : 상세 조회
        Member member // 얘는 optional 객체이지만 optional 함수를 사용하면 optional 타입으로 정의하지 않아도 된다.
                = memberRepository.findById(email)
                .orElseThrow(()->new UsernameNotFoundException("email 없음 : " + email)); // function(x){return x+1}; 이걸 function((x) -> x+1); 로만 표시할 수 있다.
        // orElseThrow() optional 에서 null 이 나오면 에러메세지를 나오게 하고, null이 아니면 객체에 값을 담는다.

        // todo 2) 검증 객체에 정보 넣기 -> 여기에 넣는 순간 검증 딱지가 붙어 권한이 생긴다.
        //        2-1) 권한을 생성해서 넣기 :  GrantedAuthority(스프링시큐리티 권한클래스)
        //        GrantedAuthority authority = new SimpleGrantedAuthority( 역할 ex) user, admin );
        GrantedAuthority authority = new SimpleGrantedAuthority(member.getCodeName());
        // todo  권한은 배열의 형태로 여러개를 가질 수 있다. 그래서 배열을 만들고 배열에 넣어야함
        //  2-2) 권한 배열(List, set )에 넣기, 그런데 스프링 시큐리티에서는 set을 이용

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority); // set에 권한을 넣어주었다.

        return new MemberDto(member.getEmail(), member.getPassword(), authorities);
    }
}
