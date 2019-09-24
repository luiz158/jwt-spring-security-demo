package org.zerhusen.sample.app.security.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerhusen.sample.app.security.SecurityUtils;
import org.zerhusen.sample.app.security.model.User;
import org.zerhusen.sample.app.security.repository.UserRepository;

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
      return SecurityUtils.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
   }

}
