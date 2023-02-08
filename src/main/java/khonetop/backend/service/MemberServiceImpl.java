package khonetop.backend.service;

import khonetop.backend.config.auth.UserDetailsImpl;
import khonetop.backend.domain.Member;
import khonetop.backend.dto.MemberSignInRequestDto;
import khonetop.backend.dto.MemberSignUpRequestDto;
import khonetop.backend.repository.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final JpaMemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public boolean signUp(MemberSignUpRequestDto request) {
        boolean isExistMember = memberRepository.existsByEmail(request.getEmail());
        if (isExistMember) {
            log.info("이미 존재하는 회원");
            return false;
        }
        Member member = Member.builder().email(request.getEmail())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .build();
        member.encryptPassword(passwordEncoder);
        memberRepository.save(member);
        return true;
    }

    @Override
    public String signIn(MemberSignInRequestDto request) { //이해 완벽하게 못함. 정리가 필요
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return principal.getUsername();
    }

    public Optional<Member> findMemberByEmail(String email){
        return null;
    }

    public void clearRepo(){
        memberRepository.deleteAll();
    }

    public Long countRepo(){
        return memberRepository.count();
    }
}
