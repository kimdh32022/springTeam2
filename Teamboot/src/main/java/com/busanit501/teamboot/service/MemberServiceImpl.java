package com.busanit501.teamboot.service;

import com.busanit501.teamboot.domain.Member;
import com.busanit501.teamboot.domain.MemberRole;
import com.busanit501.teamboot.dto.MemberJoinDTO;
import com.busanit501.teamboot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws MidExistException {
        String mid = memberJoinDTO.getMid();
         boolean exist = memberRepository.existsById(mid);
         if (exist) {
             throw new MidExistException();
         }
         // 중복 아이디가 없다. 회원 가입 진행하기.
      Member member = modelMapper.map(memberJoinDTO, Member.class);
         // 평문 패스워드, 암호화
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        // 일반 유저 권한 추가.
        member.addRole(MemberRole.USER);
         memberRepository.save(member);
    }

    @Override
    public boolean checkEmailExists(String email) {
        return memberRepository.existsById(email);
    }
}
