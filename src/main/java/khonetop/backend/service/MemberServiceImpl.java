package khonetop.backend.service;

import khonetop.backend.config.security.UserDetailsImpl;
import khonetop.backend.domain.Member;
import khonetop.backend.dto.MemberSignInRequestDto;
import khonetop.backend.dto.MemberSignInResponseDto;
import khonetop.backend.dto.MemberSignUpRequestDto;
import khonetop.backend.repository.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
                .name(request.getName())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .build();
        member.encryptPassword(passwordEncoder);
        memberRepository.save(member);
        return true;
    }

    @Override
    public MemberSignInResponseDto signIn(MemberSignInRequestDto request) { //이해 완벽하게 못함. 정리가 필요
        //현재 비밀번호가 다르면 오류가 터짐 -> 예외처리 해줘야함!!
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        Optional<Member> byEmail = memberRepository.findByEmail(principal.getUsername());
        if (byEmail.isEmpty()) {
            return null;
        }
        return new MemberSignInResponseDto(byEmail.get().getName(), principal.getUsername(), byEmail.get().getNickname());
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
