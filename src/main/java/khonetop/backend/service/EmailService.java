package khonetop.backend.service;

public interface EmailService {
    void sendMessage(String rcv) throws Exception;

    //emailCode를 redis에 저장해서 code와 사용자 정보 등을 조회해 일치하면 회원가입 되도록 하는 것 추가해주기
    boolean verifyEmailCode(String email, String code);
}
