package com.spring.jpatest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 필터 인증 끄기
        // spring security을 gradle에서 주입한 경우 하단의 설정을 안하면 
        // 강제로 로그인하라는 입력페이지로 이동한다.
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());    
        
        http.sessionManagement(session -> session.invalidSessionUrl("/") // 세션없는 경우 이동하는 경로
                                                 .maximumSessions(1) // 여러번 로그인 하는 것을 방지목적으로 세팅
                                                 .maxSessionsPreventsLogin(true)); // 두 번이상 로그인 방지

        return http.build();
    }

}
