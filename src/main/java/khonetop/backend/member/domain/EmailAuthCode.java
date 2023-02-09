package khonetop.backend.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@RedisHash(value = "emailCode", timeToLive = 300) //value: Redis의 keyspace 값  timeToLive: sec 단위로 만료시간 설정
@NoArgsConstructor
public class EmailAuthCode {

    @Id
    @GeneratedValue
    private String email;
    private String code;
}
