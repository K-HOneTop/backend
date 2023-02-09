package khonetop.backend.member.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@ToString
public class Member { //실제 DB와 연결된 member

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String name;
    private String nickname;
    private String email; //로그인할 때 id로 사용됨
//    private boolean mail_auth; //회원가입 할 때 이메일 인증을 하지 않으면 가입이 안돼서 일단 뺌
    private String password;
//    private String role; //사용자 권한
    
//    @CreationTimestamp
//    private Timestamp createDate; //회원가입한 날짜

    @Builder
    public Member(String name, String nickname, String email, String password) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }
}
