package org.techytax.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.CostMatch;
import org.techytax.domain.CostType;
import org.techytax.domain.Invoice;
import org.techytax.domain.fiscal.FiscalOverview;
import org.techytax.domain.fiscal.VatReport;
import org.techytax.helper.FiscalOverviewHelper;
import org.techytax.invoice.InvoiceCreator;
import org.techytax.repository.CostRepository;
import org.techytax.repository.InvoiceRepository;
import org.techytax.saas.domain.Registration;
import org.techytax.saas.repository.RegistrationRepository;
import org.techytax.security.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@RestController
public class FiscalRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    private final JwtTokenUtil jwtTokenUtil;

    private FiscalOverviewHelper fiscalOverviewHelper;

    private final CostRepository costRepository;

    @Autowired
    public FiscalRestController(JwtTokenUtil jwtTokenUtil, FiscalOverviewHelper fiscalOverviewHelper, CostRepository costRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.fiscalOverviewHelper = fiscalOverviewHelper;
        this.costRepository = costRepository;
    }

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/fiscal-overview", method = RequestMethod.GET)
    public FiscalOverview getFiscalOverview(HttpServletRequest request) throws Exception {
        String username = getUser(request);
        return fiscalOverviewHelper.createFiscalOverview(null, null, username);
    }

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/fiscal-overview", method = RequestMethod.POST)
    public void sendFiscalData(HttpServletRequest request, @RequestBody VatReport vatReport) {
        String username = getUser(request);
        if (vatReport.getTotalCarCosts().compareTo(BigDecimal.ZERO) == 1) {
            Cost cost = new Cost();
            cost.setUser(username);
            cost.setDate(vatReport.getLatestTransactionDate());
            cost.setCostType(CostConstants.BUSINESS_CAR);
            cost.setAmount(vatReport.getTotalCarCosts());
            cost.setDescription("Total car costs");
            costRepository.save(cost);
        }
        if (vatReport.getTotalTransportCosts().compareTo(BigDecimal.ZERO) == 1) {
            Cost cost = new Cost();
            cost.setUser(username);
            cost.setDate(vatReport.getLatestTransactionDate());
            cost.setCostType(CostConstants.TRAVEL_WITH_PUBLIC_TRANSPORT);
            cost.setAmount(vatReport.getTotalTransportCosts());
            cost.setDescription("Total transport costs");
            costRepository.save(cost);
        }
        if (vatReport.getTotalOfficeCosts().compareTo(BigDecimal.ZERO) == 1) {
            Cost cost = new Cost();
            cost.setUser(username);
            cost.setDate(vatReport.getLatestTransactionDate());
            cost.setCostType(CostConstants.SETTLEMENT);
            cost.setAmount(vatReport.getTotalOfficeCosts());
            cost.setDescription("Total office costs");
            costRepository.save(cost);
        }
        if (vatReport.getTotalOtherCosts().compareTo(BigDecimal.ZERO) == 1) {
            Cost cost = new Cost();
            cost.setUser(username);
            cost.setDate(vatReport.getLatestTransactionDate());
            cost.setCostType(CostConstants.EXPENSE_CURRENT_ACCOUNT);
            cost.setAmount(vatReport.getTotalOtherCosts());
            cost.setDescription("Total other costs");
            costRepository.save(cost);
        }
        if (vatReport.getTotalFoodCosts().compareTo(BigDecimal.ZERO) == 1) {
            Cost cost = new Cost();
            cost.setUser(username);
            cost.setDate(vatReport.getLatestTransactionDate());
            cost.setCostType(CostConstants.BUSINESS_FOOD);
            cost.setAmount(vatReport.getTotalFoodCosts());
            cost.setDescription("Total food costs");
            costRepository.save(cost);
        }
        if (vatReport.getTotalVatOut().compareTo(BigDecimal.ZERO) == 1) {
            Cost cost = new Cost();
            cost.setUser(username);
            cost.setDate(vatReport.getLatestTransactionDate());
            cost.setCostType(CostConstants.EXPENSE_CURRENT_ACCOUNT);
            cost.setVat(vatReport.getTotalVatOut());
            cost.setDescription("Total VAT out");
            costRepository.save(cost);
        }
        if (vatReport.getTotalVatIn().compareTo(BigDecimal.ZERO) == 1) {
            Cost cost = new Cost();
            cost.setUser(username);
            cost.setDate(vatReport.getLatestTransactionDate());
            cost.setCostType(CostConstants.INCOME_CURRENT_ACCOUNT);
            cost.setVat(vatReport.getTotalVatIn());
            cost.setDescription("Total VAT in");
            costRepository.save(cost);
        }
    }

    private String getUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        return jwtTokenUtil.getUsernameFromToken(token);
    }

}
