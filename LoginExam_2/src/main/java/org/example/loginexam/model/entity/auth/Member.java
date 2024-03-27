package org.example.loginexam.model.entity.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.example.loginexam.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.loginexam.model.entity.auth
 * fileName : Member
 * author : PC
 * date : 2024-03-26
 * description : 로그인 사용자 클래스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-26         PC          최초 생성
 */
@Entity
@Table(name = "TB_MEMBER")
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Where(clause = "DELETE_YN = 'N'")
@SQLDelete(sql = "UPDATE TB_MEMBER " +
        "SET DELETE_YN = 'Y', DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') " +
        "WHERE EMAIL = ?")
public class Member extends BaseTimeEntity2 {

    @Id
    private String email; // 로그인 id로 스프링 시큐리티 속성을 username 을 사용한다. username = id = email 이다.
    private String password; // 암호
    private String name; // 유저명
    private String codeName; // 권한명 : ROLE_USER(사용자), ROLE_ADMIN(관리자) 반드시 DB에 넣을때 언더바를 넣어야한다.
}
