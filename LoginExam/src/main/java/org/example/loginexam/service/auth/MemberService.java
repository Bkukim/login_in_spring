package org.example.loginexam.service.auth;

import org.example.loginexam.model.entity.auth.Member;
import org.example.loginexam.repository.auth.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : org.example.loginexam.service.auth
 * fileName : MemberService
 * author : PC
 * date : 2024-03-26
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-26         PC          최초 생성
 */
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // todo : 로그인 관련 함수
    //      1)상세 조회
    public Optional<Member> findById(String email){
        Optional<Member> optionalMember = memberRepository.findById(email);
        return optionalMember;
    }

}
