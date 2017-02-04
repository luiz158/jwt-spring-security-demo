package org.techytax.saas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.techytax.model.security.Authority;
import org.techytax.model.security.AuthorityName;
import org.techytax.model.security.User;
import org.techytax.saas.domain.Registration;
import org.techytax.saas.repository.RegistrationRepository;
import org.techytax.security.JwtTokenUtil;
import org.techytax.security.repository.AuthorityRepository;
import org.techytax.security.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;

@RestController
public class RegisterRestController {

  @Value("${jwt.header}")
  private String tokenHeader;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private RegistrationRepository registrationRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthorityRepository authorityRepository;

  @CrossOrigin(origins = "http://localhost:5555")
  @RequestMapping(value = "register", method = RequestMethod.POST)
  @Transactional
  public void addRegistration(HttpServletRequest request, @RequestBody Registration registration) {
    if (!registrationRepository.findByUser(registration.getUser()).isEmpty()) {
      throw new RuntimeException("Gebruiker bestaat al");
    }
    User user = new User();
    String password = new BCryptPasswordEncoder().encode(registration.getPassword());
    user.setPassword(password);
    user.setUsername(registration.getUser());
    user.setEmail(registration.getPersonalData().getEmail());
    Authority authority = authorityRepository.findByName(AuthorityName.ROLE_USER);
    user.setAuthorities(Arrays.asList(authority));
    user.setEnabled(Boolean.TRUE);
    user.setFirstname(registration.getPersonalData().getInitials());
    user.setLastname(registration.getPersonalData().getSurname());
    user.setLastPasswordResetDate(new Date());
    userRepository.save(user);
    registrationRepository.save(registration);
  }

  @CrossOrigin(origins = "http://localhost:5555")
  @RequestMapping(value = "auth/register", method = RequestMethod.PUT)
  @Transactional
  public void updateRegistration(HttpServletRequest request, @RequestBody Registration registration) {
    registrationRepository.save(registration);
  }

  @CrossOrigin(origins = "http://localhost:5555")
  @RequestMapping(value = "auth/register", method = RequestMethod.GET)
  public Registration getRegistration(HttpServletRequest request) {
    String username = getUser(request);
    return registrationRepository.findByUser(username).stream().findFirst().get();
  }

//  @CrossOrigin(origins = "http://localhost:5555")
//  @RequestMapping(value = "auth/register", method = RequestMethod.GET)
//  @PreAuthorize("hasRole('ADMIN')")
//  public Collection<Registration> getRegistrations() {
//    return registrationRepository.findAll();
//  }

  @CrossOrigin(origins = "http://localhost:5555")
  @RequestMapping(value = "auth/register/{id}", method = RequestMethod.DELETE)
  public void deleteRegistration(HttpServletRequest request, @PathVariable Long id) {
    registrationRepository.delete(id);
  }

  private String getUser(HttpServletRequest request) {
    String token = request.getHeader(tokenHeader);
    return jwtTokenUtil.getUsernameFromToken(token);
  }
}
