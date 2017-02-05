package org.techytax.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.techytax.domain.Customer;
import org.techytax.repository.CustomerRepository;
import org.techytax.security.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
public class CustomerRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomerRepository customerRepository;

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/customer", method = RequestMethod.GET)
    public Collection<Customer> getCustomers(HttpServletRequest request) {
        String username = getUser(request);
        return customerRepository.findByUser(username);
    }

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/customer", method = { RequestMethod.PUT, RequestMethod.POST })
    public void saveCustomer(HttpServletRequest request, @RequestBody Customer customer) {
        String username = getUser(request);
        customer.setUser(username);
        customerRepository.save(customer);
    }

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/customer/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(HttpServletRequest request, @PathVariable Long id) {
        customerRepository.delete(id);
    }

    private String getUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        return jwtTokenUtil.getUsernameFromToken(token);
    }

}
