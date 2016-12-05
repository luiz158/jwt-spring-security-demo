package org.techytax.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.techytax.domain.CostMatch;
import org.techytax.repository.CostMatchRepository;
import org.techytax.security.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
public class CostMatchRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CostMatchRepository costMatchRepository;

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/costmatches", method = RequestMethod.GET)
    public Collection<CostMatch> getCostMatches(HttpServletRequest request) {
        String username = getUser(request);
        System.out.println("Hoi " + username);
        return costMatchRepository.findByUser(username);
    }

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/match", method = RequestMethod.POST)
    public void addCostMatch(HttpServletRequest request, @RequestBody CostMatch costMatch) {
        String username = getUser(request);
        System.out.println("Hoi " + username);
        costMatch.setUser(username);
        costMatchRepository.save(costMatch);

//        return costMatchRepository.findAll();
    }

    private String getUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        return jwtTokenUtil.getUsernameFromToken(token);
    }

}
