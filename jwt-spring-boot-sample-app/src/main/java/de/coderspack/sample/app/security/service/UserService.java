package de.coderspack.sample.app.security.service;

import de.coderspack.sample.app.database.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import de.coderspack.spring.boot.jwt.library.security.CurrentJwtUser;
import de.coderspack.sample.app.database.model.User;

import java.util.Optional;

@Service
@Transactional
public class UserService {

   private final UserRepository userRepository;

   public UserService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Transactional(readOnly = true)
   public Optional<User> getUserWithAuthorities() {
      return CurrentJwtUser.getUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
   }

}
