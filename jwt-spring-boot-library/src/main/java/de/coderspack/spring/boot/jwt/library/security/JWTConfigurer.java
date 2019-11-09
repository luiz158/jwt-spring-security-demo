package de.coderspack.spring.boot.jwt.library.security;

import de.coderspack.spring.boot.jwt.library.JWTFilter;
import de.coderspack.spring.boot.jwt.library.factory.TokenFactory;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private TokenFactory tokenFactory;

    public JWTConfigurer(TokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }

    @Override
    public void configure(HttpSecurity http) {
        JWTFilter customFilter = new JWTFilter(tokenFactory);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
