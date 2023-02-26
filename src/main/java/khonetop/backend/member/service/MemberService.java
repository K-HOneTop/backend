package khonetop.backend.member.service;

import khonetop.backend.member.dto.MemberSignInRequestDto;
import khonetop.backend.member.dto.MemberSignInResponseDto;
import khonetop.backend.member.dto.MemberSignUpRequestDto;

import java.util.Optional;

public interface MemberService {
    boolean signUp(MemberSignUpRequestDto request);
    Optional<MemberSignInResponseDto> signIn(MemberSignInRequestDto request) throws IllegalArgumentException;

    boolean isExistMember(String email);
    boolean isDuplicateNickname(String nickname);

}
