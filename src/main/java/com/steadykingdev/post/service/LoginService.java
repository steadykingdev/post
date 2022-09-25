package com.steadykingdev.post.service;

import com.steadykingdev.post.domain.Member;
import com.steadykingdev.post.dto.LoginDto;
import com.steadykingdev.post.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemoryMemberRepository memoryMemberRepository;

    public Member login(LoginDto loginDto) {
        return memoryMemberRepository.findByLoginId(loginDto.getLoginId())
                .filter(m -> m.getPassword().equals(loginDto.getPassword()))
                .orElse(null);
    }
}
