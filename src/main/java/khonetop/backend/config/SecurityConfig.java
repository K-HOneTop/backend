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
                .authorizeRequests()
                .antMatchers().authenticated()
                .anyRequest().authenticated();

        http.formLogin() //로그인
                .loginPage("/member/signin")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("member")
                .failureHandler(
                        new AuthenticationFailureHandler() {
                            @Override
                            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                                response.sendRedirect("/member/signin/fail");
                            }
                        }
                )
                .permitAll();

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
