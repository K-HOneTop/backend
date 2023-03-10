package khonetop.backend.config.security;

import khonetop.backend.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails { //security 사용하기 위한 UserDetails

    private static final String ROLE_PREFIX = "ROLE_";
    private Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //user 권한 리턴
        //member의 role을 manager, user 등으로 세분화할 경우 getRole 추가해줄 것
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(ROLE_PREFIX + "USER");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    //true가 기본값
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
