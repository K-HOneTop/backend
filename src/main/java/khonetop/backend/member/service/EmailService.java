package khonetop.backend.member.service;

import javax.mail.internet.MimeMessage;

public interface EmailService {
    void sendMessage(String rcv) throws Exception;
    boolean verifyEmailCode(String email, String code);
    void sendTemporaryPasswordMessage(String rcv, String temporaryPassword) throws Exception;
}
