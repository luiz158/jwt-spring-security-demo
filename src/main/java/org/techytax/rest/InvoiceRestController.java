package org.techytax.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.techytax.domain.Invoice;
import org.techytax.repository.InvoiceRepository;
import org.techytax.security.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
public class InvoiceRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/invoice", method = RequestMethod.GET)
    public Collection<Invoice> getInvoices(HttpServletRequest request) {
        String username = getUser(request);
        return invoiceRepository.findByUser(username);
    }

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/invoice", method = { RequestMethod.PUT, RequestMethod.POST })
    public void saveInvoice(HttpServletRequest request, @RequestBody Invoice invoice) {
        String username = getUser(request);
        invoice.setUser(username);
        invoiceRepository.save(invoice);
    }

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/invoice/{id}", method = RequestMethod.DELETE)
    public void deleteInvoice(HttpServletRequest request, @PathVariable Long id) {
        invoiceRepository.delete(id);
    }

    private String getUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        return jwtTokenUtil.getUsernameFromToken(token);
    }

}
