package org.techytax.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.techytax.domain.Cost;
import org.techytax.repository.CostRepository;
import org.techytax.security.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
public class CostRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    private final JwtTokenUtil jwtTokenUtil;

    private final CostRepository costRepository;

    @Autowired
    public CostRestController(JwtTokenUtil jwtTokenUtil, CostRepository costRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.costRepository = costRepository;
    }

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/costs", method = RequestMethod.GET)
    public Collection<Cost> getCosts(HttpServletRequest request) {
        String username = getUser(request);
        System.out.println("Hoi " + username);
        return costRepository.findByUser(username);
    }

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/costs/{id}", method = RequestMethod.GET)
    public Cost getCost(HttpServletRequest request, @PathVariable Long id) {
        return costRepository.getOne(id);
    }

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/cost", method = RequestMethod.POST)
    public void addCost(HttpServletRequest request, @RequestBody Cost cost) {
        String username = getUser(request);
        cost.setUser(username);
        costRepository.save(cost);
    }

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/cost/{id}", method = RequestMethod.DELETE)
    public void deleteCost(HttpServletRequest request, @PathVariable Long id) {
        costRepository.delete(id);
    }

    private String getUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        return jwtTokenUtil.getUsernameFromToken(token);
    }

}
