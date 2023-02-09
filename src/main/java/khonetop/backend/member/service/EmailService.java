package khonetop.backend.member.service;

public interface EmailService {
    void sendMessage(String rcv) throws Exception;
    boolean verifyEmailCode(String email, String code);
}
