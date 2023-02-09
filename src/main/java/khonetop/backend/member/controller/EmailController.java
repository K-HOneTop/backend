package khonetop.backend.member.controller;

import khonetop.backend.member.service.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mail")
public class EmailController { //email 인증 코드

    private final EmailServiceImpl emailService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity sendMail(@RequestParam("rcv") String emailRcv) throws Exception { //email 인증 코드 보내기
        //주의할 것! 구글 이메일 보내기는 1회에 100건, 하루 500건으로 제한되어 있음
        //메일 주소를 팀 공용 메일로 하나 새로 생성해서 진행하는 것이 좋을 것 같음
        log.info("rcv: "+ emailRcv);
        emailService.sendMessage(emailRcv);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/auth")
    @ResponseBody
    public ResponseEntity checkMailCode(@RequestParam("rcv") String emailRcv, @RequestParam("code") String emailCode) throws Exception {
        //email 인증 코드 확인
        log.info("verify 전");
        boolean authCode = emailService.verifyEmailCode(emailRcv, emailCode);
        if (!authCode) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
