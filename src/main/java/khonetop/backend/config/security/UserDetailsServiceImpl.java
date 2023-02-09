package khonetop.backend.config.security;

import khonetop.backend.member.domain.Member;
import khonetop.backend.member.repository.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final JpaMemberRepository memberRepository;

    //spring 에서 로그인 요청 가로챌 때 username, password 2개를 가로채는데 password 부분은 알아서 처리해줌
    //username 이 DB에 있는지는 직접 확인해줘야함
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("Invalid authentication!")
        );

        return new UserDetailsImpl(member);
    }
}
