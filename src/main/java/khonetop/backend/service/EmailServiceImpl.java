package khonetop.backend.service;

import lombok.RequiredArgsConstructor;
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
    private final RedisUtil redisUtil;
    public static final String emailCode = createKey();

    @Override
    public void sendMessage(String rcv) throws Exception {
        MimeMessage message = createMessage(rcv);
        if (redisUtil.existData(rcv)) {
            redisUtil.deleteData(rcv);
        }
        try {
            emailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        redisUtil.setDataExpire(rcv, emailCode, 60 * 5L);
    }

    @Override
    public boolean verifyEmailCode(String email, String code) {
        String codeFoundByEmail = redisUtil.getData(email);
        if (codeFoundByEmail == null) {
            return false;
        }
        return redisUtil.getData(email).equals(code);
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
        for(int i=0;i<6;i++){ //6자리 인증번호, 알파벳을 섞게 될 수도 있어서 한개씩 만들었음
            int index = random.nextInt(10); //0~9까지 랜덤
            key.append(index);
        }
        return key.toString();
    }

}
