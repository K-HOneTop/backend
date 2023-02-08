package khonetop.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender emailSender;
    public static final String emailCode = createKey();

    @Override
    public String sendMessage(String rcv) throws Exception {
        MimeMessage message = createMessage(rcv);
        try {
            emailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }

        return emailCode;
    }

    private MimeMessage createMessage(String rcv) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, rcv); //보내는 대상
        message.setSubject("이메일 인증 코드"); //제목 설정

        String mailMsg="";
        mailMsg+="<div>";
        mailMsg+="CODE: <strong>";
        mailMsg+=emailCode;
        mailMsg+="</strong></div>";

        message.setText(mailMsg, "utf-8", "html");
        message.setFrom(new InternetAddress("pepepongpo@gmail.com", "LimGaYoung")); //보내는 사람
        return message;
    }

    private static String createKey(){
        StringBuffer key = new StringBuffer();
        Random random = new Random();
        for(int i=0;i<6;i++){ //6자리 인증번호
            int index = random.nextInt(10); //0~9까지 랜덤
            key.append(index);
        }
        return key.toString();
    }

}
