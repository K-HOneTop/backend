package khonetop.backend.controller;

import khonetop.backend.domain.Member;
import khonetop.backend.dto.MemberSignInRequestDto;
import khonetop.backend.dto.MemberSignInResponseDto;
import khonetop.backend.dto.MemberSignUpRequestDto;
import khonetop.backend.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.FailedLoginException;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberServiceImpl memberService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@Valid @RequestBody MemberSignUpRequestDto form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        boolean result = memberService.signUp(form);
        if (!result) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/signin")
    public ResponseEntity signIn(@Valid @RequestBody MemberSignInRequestDto form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        MemberSignInResponseDto memberSignInResponseDto = memberService.signIn(form);
        if (memberSignInResponseDto.equals(null)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(memberSignInResponseDto, HttpStatus.OK);
    }

    @GetMapping("/signin/fail") //get 방식이 안된다넹 왜지??
    public ResponseEntity loginFail() {
        log.info("비밀번호가 일치하지 않음");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
