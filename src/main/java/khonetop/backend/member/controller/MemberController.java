package khonetop.backend.member.controller;

import khonetop.backend.member.dto.MemberSignInRequestDto;
import khonetop.backend.member.dto.MemberSignInResponseDto;
import khonetop.backend.member.dto.MemberSignUpRequestDto;
import khonetop.backend.member.service.EmailServiceImpl;
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
    private final EmailServiceImpl emailService;

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

    @GetMapping("/signup/{nickname}")
    public ResponseEntity checkNicknameDuplicate(@PathVariable("nickname") String nickname) {
        boolean duplicateNickname = memberService.isDuplicateNickname(nickname);
        if (duplicateNickname) {
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
        if (!memberSignInResponseDto.isPresent()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(memberSignInResponseDto, HttpStatus.OK);
    }

    @GetMapping("/password/{email}") //뭐로 해야할지 잘..
    public ResponseEntity temporaryPassword(@PathVariable("email") String email) {
        String temporaryPassword = memberService.createTemporaryPassword(email);
        if (temporaryPassword == null) {
            log.info("email 존재하지 않음");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        try {
            emailService.sendTemporaryPasswordMessage(email, temporaryPassword);
        } catch (Exception e) {
            log.info("임시 비밀번호 email 전송 실패");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
