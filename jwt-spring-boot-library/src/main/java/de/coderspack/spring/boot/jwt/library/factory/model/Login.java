package de.coderspack.spring.boot.jwt.library.factory.model;

public class Login {

   private final String username;
   private final String password;
   private final boolean rememberMe;

   public Login(String username, String password, boolean rememberMe) {
      this.username = username;
      this.password = password;
      this.rememberMe = rememberMe;
   }

   public String getUsername() {
      return username;
   }

   public String getPassword() {
      return password;
   }

   public boolean isRememberMe() {
      return rememberMe;
   }
}
