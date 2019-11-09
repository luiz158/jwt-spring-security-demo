package de.coderspack.spring.boot.jwt.library.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrentJwtUserTest {

   @Test
   public void getCurrentUsername() {
      SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
      securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin"));
      SecurityContextHolder.setContext(securityContext);

      Optional<String> username = CurrentJwtUser.getUsername();

      assertThat(username).contains("admin");
   }

   @Test
   public void getCurrentUsernameForNoAuthenticationInContext() {
      Optional<String> username = CurrentJwtUser.getUsername();

      assertThat(username).isEmpty();
   }
}

