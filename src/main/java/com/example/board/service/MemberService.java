package com.example.board.service;

import com.example.board.dto.MemberResponseDto;
import com.example.board.dto.SignUpResponseDto;
import com.example.board.entity.Member;
import com.example.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public SignUpResponseDto signUp(String username, String password, Integer age) {

        Member member = new Member(username, password, age);

        Member savedMember = memberRepository.save(member);

        return new SignUpResponseDto(
                savedMember.getId(),
                savedMember.getUsername(),
                savedMember.getAge()
        );
    }

    public MemberResponseDto findById(Long id) {
        Member memberResponse = memberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));

        return new MemberResponseDto(memberResponse.getUsername(), memberResponse.getAge());
    }
}
