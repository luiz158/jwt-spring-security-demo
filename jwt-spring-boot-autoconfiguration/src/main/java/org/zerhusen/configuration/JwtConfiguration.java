package org.zerhusen.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zerhusen.properties.JwtProperties;
import org.zerhusen.security.jwt.TokenProvider;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfiguration {

   @Bean
   @ConditionalOnMissingBean
   public TokenProvider tokenProvider(final JwtProperties jwtProperties) {
      return new TokenProvider(jwtProperties.getBase64Secret(), jwtProperties.getTokenValidityInSeconds(), jwtProperties.getTokenValidityInSecondsForRememberMe());
   }

}
