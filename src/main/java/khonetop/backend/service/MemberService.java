package khonetop.backend.service;

import khonetop.backend.domain.Member;
import khonetop.backend.dto.MemberSignInRequestDto;
import khonetop.backend.dto.MemberSignInResponseDto;
import khonetop.backend.dto.MemberSignUpRequestDto;

import java.util.Optional;

public interface MemberService {
    boolean signUp(MemberSignUpRequestDto request);
    MemberSignInResponseDto signIn(MemberSignInRequestDto request) throws IllegalArgumentException;
}
