package khonetop.backend.repository;

import khonetop.backend.member.domain.Member;
import khonetop.backend.member.repository.JpaMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class JpaMemberRepositoryTest {

    @Autowired
    JpaMemberRepository jpaMemberRepository;

    @AfterEach
    void afterEach(){
        jpaMemberRepository.deleteAll();
    }

    @Test
    public void saveTest(){
        Member member = Member.builder().email("email@gamil.com")
                .nickname("닉네임")
                .password("password")
                .build();

        jpaMemberRepository.save(member);

        Assertions.assertThat(jpaMemberRepository.count()).isEqualTo(1L);
    }

}