package khonetop.backend.service;

import khonetop.backend.member.dto.MemberSignInRequestDto;
import khonetop.backend.member.dto.MemberSignInResponseDto;
import khonetop.backend.member.dto.MemberSignUpRequestDto;
import khonetop.backend.member.service.MemberServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.security.auth.login.FailedLoginException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberServiceImpl memberService;

    @AfterEach
    public void afterEach(){
        memberService.clearRepo();
    }

    @Test
    void join() {
        memberService.signUp(new MemberSignUpRequestDto("이름","email", "nickname", "password"));

        assertThat(memberService.countRepo()).isEqualTo(1L);
    }

    @Test
    void login() {
        memberService.signUp(new MemberSignUpRequestDto("이름","email", "nickname", "password"));

        Optional<MemberSignInResponseDto> memberSignInResponseDto = memberService.signIn(new MemberSignInRequestDto("email", "password"));

        assertThat(memberSignInResponseDto.get().getEmail()).isEqualTo("email");


    }

    @Test
    void createTemporaryPassword(){
        memberService.signUp(new MemberSignUpRequestDto("이름","email", "nickname", "password"));
        String temporaryPassword = memberService.createTemporaryPassword("email");
        MemberSignInRequestDto memberSignInRequestDto = new MemberSignInRequestDto("email", temporaryPassword);
        Optional<MemberSignInResponseDto> memberSignInResponseDto = memberService.signIn(memberSignInRequestDto);

        assertThat(memberSignInResponseDto.isPresent()).isTrue();
    }
}