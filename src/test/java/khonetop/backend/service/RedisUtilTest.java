package khonetop.backend.service;

import khonetop.backend.member.service.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RedisUtilTest {
    @Autowired
    private RedisUtil redisUtil;

//    @Test
    public void redisTest() throws Exception{
        redisUtil.setDataExpire("test@gmail.com", "11111", 60 * 5L);

        assertThat(redisUtil.existData("test@gmail.com")).isTrue();
    }

}