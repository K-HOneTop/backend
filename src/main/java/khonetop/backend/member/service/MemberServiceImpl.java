package khonetop.backend.member.service;

import khonetop.backend.config.security.UserDetailsImpl;
import khonetop.backend.member.domain.Member;
import khonetop.backend.member.dto.MemberSignInRequestDto;
import khonetop.backend.member.dto.MemberSignInResponseDto;
import khonetop.backend.member.dto.MemberSignUpRequestDto;
import khonetop.backend.member.repository.JpaMemberRepository;
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
public class MemberServiceImpl implements MemberService{ //로그인, 회원 가입 관련 서비스

    private final JpaMemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public boolean signUp(MemberSignUpRequestDto request) {
        if (isDuplicateNickname(request.getNickname())) {
            log.info("이미 존재하는 닉네임");
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
    public Optional<MemberSignInResponseDto> signIn(MemberSignInRequestDto request) {
        try {
            Optional<Member> byEmail = memberRepository.findByEmail(request.getEmail());
            if (byEmail.isEmpty()) {
                return null;
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();

            return Optional.of(new MemberSignInResponseDto(byEmail.get().getName(), principal.getUsername(), byEmail.get().getNickname()));
        } catch (BadCredentialsException e) {
            return null;
        }
    }

    @Override
    public boolean isExistMember(String email){
        boolean isExistMember = memberRepository.existsByEmail(email);
        if (isExistMember) {
            log.info("이미 존재하는 회원");
            return true;
        }
        return false;
    }


    @Override
    public boolean isDuplicateNickname(String nickname){
        Optional<Member> member = memberRepository.findByNickname(nickname);
        if(member.isPresent()){
            return true;
        }
        return false;
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
