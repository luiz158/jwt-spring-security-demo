package org.zerhusen.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties(prefix = "jwt")
@Validated
public class JwtProperties {

   @NotBlank
   private String base64Secret;

   private long tokenValidityInSeconds = 86400;

   private long tokenValidityInSecondsForRememberMe = 108000;

   public String getBase64Secret() {
      return base64Secret;
   }

   public void setBase64Secret(String base64Secret) {
      this.base64Secret = base64Secret;
   }

   public long getTokenValidityInSeconds() {
      return tokenValidityInSeconds;
   }

   public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
      this.tokenValidityInSeconds = tokenValidityInSeconds;
   }

   public long getTokenValidityInSecondsForRememberMe() {
      return tokenValidityInSecondsForRememberMe;
   }

   public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
      this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
   }
}
