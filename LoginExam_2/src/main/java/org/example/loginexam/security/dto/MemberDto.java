package org.example.loginexam.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;

/**
 * packageName : org.example.loginexam.security.dto
 * fileName : MemberDto
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
@Setter
@Getter
@ToString
public class MemberDto extends User { // 여기에 인증이 된 애들을 넣어주는 객체이다.
    // todo : 이 클래스는 명단 같은 클래스이다. 여기 명단에 있는 애들만 권한이 주어진다.
    //      1) 스프링에서 제공한 유저 클래스 : user, userDetails 둘 중하나만 상속해서 쓰면 되는데 user로 하는게 코딩이 길어지지 않는다.
    //      2) 검증된 유저 객체는 권한이 있음 : 권한 넣기

    // User에 있는 기능 말고 우리가 추가로 속성을 넣어보자. 우리는 email을 id로 사용하는데 user에는 email이 없어서 이걸 우리가 만들어보자.
    private String email;

    public MemberDto(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.email = email;
    }
}
