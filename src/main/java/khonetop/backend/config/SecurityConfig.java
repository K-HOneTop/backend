package khonetop.backend.config;

import khonetop.backend.config.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity //security 필터가 등록됨
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private String[] ignoredMatcherPattern = {"/", "/img/**", "/lib/**", "/member/**"};

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // extends WebSecurityConfigurerAdapter 없어짐
    //원래 override 적용해줘야 했고, return super.auth- 였는데 없어져서 이렇게 됨
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable() //csrf 공격에 대한 방어 해제 -> 쿠키 사용하지 않으면 자동 방어됨
                .authorizeRequests() // url 따른 페이지에 대한 권한 부여 위한 메소드, antMatchers 기능 이용하기 위한 메소드
                .antMatchers().authenticated() //특정 URL 접근 시 인가 필요한 URL 설정 가능, .authenticated() 사용하면 해당 URL은 인증 필요한 URL이라고 명시 가능
                .anyRequest().permitAll()//특정 URL 제외 나머지 URL 전부 인가해줌
                .and()
                .formLogin() //아이디, 비번 입력해서 들어오는 로그인 형태 지원하는 spring security 기능
                .loginPage("/login")//로그인 페이지로 redirect해줌 (인가 안된 사용자에게 보여줄 페이지 설정)
                .loginProcessingUrl("/member/signin") //form login의 자동 로그인 방식 이용함
                .defaultSuccessUrl("/"); //로그인 성공 시 기본 url 설정


//        http
//                .authorizeRequests()
//                .antMatchers().authenticated()
//                .anyRequest().authenticated();
//
//        http.formLogin("/login") //로그인
//                .loginPage("/member/signin")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .loginProcessingUrl("/member/signin")
//                .permitAll();

        return http.build();
    }

    //정적 자원 및 루트 페이지 ignore
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .antMatchers(ignoredMatcherPattern);
    }
}
