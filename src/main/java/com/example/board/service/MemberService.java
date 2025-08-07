package com.example.board.service;

import com.example.board.dto.MemberResponseDto;
import com.example.board.dto.SignUpResponseDto;
import com.example.board.entity.Member;
import com.example.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public SignUpResponseDto signUp(String username, String password, Integer age) {

        Member member = new Member(username, password, age);

        Member savedMember = memberRepository.save(member);

        return new SignUpResponseDto(
                savedMember.getId(),
                savedMember.getUsername(),
                savedMember.getAge()
        );
    }

    @Transactional
    public MemberResponseDto findById(Long id) {
        Member memberResponse = memberRepository.findByIdOrElseThrow(id);

        return new MemberResponseDto(memberResponse.getUsername(), memberResponse.getAge());
    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        Member findMember = memberRepository.findByIdOrElseThrow(id);

        if (!findMember.getPassword().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다");
        }

        findMember.updatePassword(newPassword);
    }
}
