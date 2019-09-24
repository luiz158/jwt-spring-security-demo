package org.zerhusen.jwt.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zerhusen.jwt.autoconfigure.properties.JwtProperties;
import org.zerhusen.jwt.library.JWTFilter;
import org.zerhusen.jwt.library.TokenProvider;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfiguration {

   @Bean
   @ConditionalOnMissingBean
   public TokenProvider tokenProvider(final JwtProperties jwtProperties) {
      return new TokenProvider(jwtProperties.getBase64Secret(), jwtProperties.getTokenValidityInSeconds(), jwtProperties.getTokenValidityInSecondsForRememberMe());
   }

   @Bean
   @ConditionalOnMissingBean
   public JWTFilter jwtFilter(final TokenProvider tokenProvider) {
      return new JWTFilter(tokenProvider);
   }

}
