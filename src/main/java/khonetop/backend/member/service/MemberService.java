package khonetop.backend.member.service;

import khonetop.backend.member.dto.MemberSignInRequestDto;
import khonetop.backend.member.dto.MemberSignInResponseDto;
import khonetop.backend.member.dto.MemberSignUpRequestDto;

public interface MemberService {
    boolean signUp(MemberSignUpRequestDto request);
    MemberSignInResponseDto signIn(MemberSignInRequestDto request) throws IllegalArgumentException;
}
