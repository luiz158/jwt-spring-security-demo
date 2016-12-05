package org.techytax.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.techytax.domain.Activum;
import org.techytax.repository.ActivumRepository;
import org.techytax.security.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
public class ActivumRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ActivumRepository activumRepository;

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/activum", method = RequestMethod.GET)
    public Collection<Activum> getActiva(HttpServletRequest request) {
        String username = getUser(request);
        return activumRepository.findByUser(username);
    }

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/activum", method = RequestMethod.POST)
    public void addActivum(HttpServletRequest request, @RequestBody Activum activum) {
        String username = getUser(request);
        activum.setUser(username);
        activumRepository.save(activum);
    }

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/activum/{id}", method = RequestMethod.DELETE)
    public void deleteActivum(HttpServletRequest request, @PathVariable Long id) {
        activumRepository.delete(id);
    }

    private String getUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        return jwtTokenUtil.getUsernameFromToken(token);
    }

}
