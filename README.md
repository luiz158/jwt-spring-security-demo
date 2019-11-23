# Spring Boot JWT Starter ![Build Status](https://travis-ci.org/coderspack/spring-boot-starter-jwt.svg?branch=master) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This is a spring boot starter based on [Stephan's Spring Boot JWT Demo](https://github.com/szerhusenBC/jwt-spring-security-demo).
This starter is still in progress and not production ready.

## Getting started

Coders pack spring boot jwt starter is available in [maven central repository](https://search.maven.org/search?q=coderspack)

_Maven_
```xml
<dependency>
  <groupId>de.coderspack</groupId>
  <artifactId>jwt-spring-boot-starter</artifactId>
  <version>0.0.1</version>
</dependency>
```

_Gradle_
```
implementation 'de.coderspack:spring-boot-starter-jwt:0.0.1'
```

_Code_
```java
import de.coderspack.spring.boot.jwt.library.security.JWTConfigurer;
import de.coderspack.spring.boot.jwt.library.security.web.access.JwtAccessDeniedHandler;
import de.coderspack.spring.boot.jwt.library.security.web.access.JwtAuthenticationEntryPoint;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   
    private final JWTConfigurer jwtConfigurer;
    private final JwtAuthenticationEntryPoint authenticationErrorHandler;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
 
    public WebSecurityConfig(JWTConfigurer jwtConfigurer,
                             JwtAuthenticationEntryPoint authenticationErrorHandler,
                             JwtAccessDeniedHandler jwtAccessDeniedHandler) {
       this.jwtConfigurer = jwtConfigurer;
       this.corsFilter = corsFilter;
       this.authenticationErrorHandler = authenticationErrorHandler;
       this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    // PasswordEncoder is required. Choose any!
    @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
       // Apply matchers and all the stuff you need!
        httpSecurity
            .authenticationEntryPoint(authenticationErrorHandler)
            .accessDeniedHandler(jwtAccessDeniedHandler)
            .apply(jwtConfigurer);
    }
}
```

**TODO** Migrate TODO Notes from https://github.com/coderspack/spring-boot-starter-jwt-java-demo 
to this documentation.
