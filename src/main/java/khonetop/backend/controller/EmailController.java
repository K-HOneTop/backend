package khonetop.backend.controller;

import khonetop.backend.service.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mail")
public class EmailController {

    private final EmailServiceImpl emailService;

    @PostMapping("/auth")
    @ResponseBody
    public ResponseEntity sendMail(@RequestParam String emailRcv) throws Exception {
        //주의할 것! 구글 이메일 보내기는 1시간에 30건, 하루 100건으로 제한되어 있음
        //메일 주소를 팀 공용 메일로 하나 새로 생성해서 진행하는 것이 좋을 것 같음
        String emailCode = emailService.sendMessage(emailRcv);

        return new ResponseEntity(emailCode, HttpStatus.OK);
    }
}
