package khonetop.backend.member.controller;

import khonetop.backend.member.dto.MemberSignInRequestDto;
import khonetop.backend.member.dto.MemberSignInResponseDto;
import khonetop.backend.member.dto.MemberSignUpRequestDto;
import khonetop.backend.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberServiceImpl memberService;

    @PostMapping("/signup/{email}")
    public ResponseEntity isDuplicateEmail(@PathVariable("email") String email) { //이메일 중복 체크
        boolean existMember = memberService.isExistMember(email);
        if (existMember) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/signup") //회원가입
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
    @PostMapping("/signin") //로그인
    public ResponseEntity signIn(@Valid @RequestBody MemberSignInRequestDto form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Optional<MemberSignInResponseDto> memberSignInResponseDto = memberService.signIn(form);
        if (memberSignInResponseDto==null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(memberSignInResponseDto, HttpStatus.OK);
    }

    @GetMapping("/signin/fail") //로그인 실패 시.. 일단 사용 안함
    public ResponseEntity loginFail() {
        log.info("비밀번호가 일치하지 않음");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
