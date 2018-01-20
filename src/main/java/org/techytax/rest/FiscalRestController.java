package org.techytax.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.fiscal.FiscalOverview;
import org.techytax.domain.fiscal.VatReport;
import org.techytax.helper.FiscalOverviewHelper;
import org.techytax.repository.BookRepository;
import org.techytax.repository.CostRepository;
import org.techytax.security.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

import static java.math.BigDecimal.ZERO;

@RestController
public class FiscalRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    private final JwtTokenUtil jwtTokenUtil;

    private FiscalOverviewHelper fiscalOverviewHelper;

    private CostRepository costRepository;

    private BookRepository bookRepository;

    @Autowired
    public FiscalRestController(
      JwtTokenUtil jwtTokenUtil,
      FiscalOverviewHelper fiscalOverviewHelper,
      CostRepository costRepository,
      BookRepository bookRepository
    ) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.fiscalOverviewHelper = fiscalOverviewHelper;
        this.costRepository = costRepository;
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "auth/fiscal-overview", method = RequestMethod.GET)
    public FiscalOverview getFiscalOverview(HttpServletRequest request) throws Exception {
        String username = getUser(request);
        return fiscalOverviewHelper.createFiscalOverview(null, null, username);
    }

    @RequestMapping(value = "auth/fiscal-overview", method = RequestMethod.POST)
    public void sendFiscalData(HttpServletRequest request, @RequestBody VatReport vatReport) {
        String username = getUser(request);
        if (vatReport.getTotalCarCosts().compareTo(ZERO) > 0) {
            Cost cost = new Cost();
            cost.setUser(username);
            cost.setDate(vatReport.getLatestTransactionDate());
            cost.setCostType(CostConstants.BUSINESS_CAR);
            cost.setAmount(vatReport.getTotalCarCosts());
            cost.setDescription("Total car costs");
            costRepository.save(cost);
        }
        if (vatReport.getTotalTransportCosts().compareTo(ZERO) > 0) {
            Cost cost = new Cost();
            cost.setUser(username);
            cost.setDate(vatReport.getLatestTransactionDate());
            cost.setCostType(CostConstants.TRAVEL_WITH_PUBLIC_TRANSPORT);
            cost.setAmount(vatReport.getTotalTransportCosts());
            cost.setDescription("Total transport costs");
            costRepository.save(cost);
        }
        if (vatReport.getTotalOfficeCosts().compareTo(ZERO) > 0) {
            Cost cost = new Cost();
            cost.setUser(username);
            cost.setDate(vatReport.getLatestTransactionDate());
            cost.setCostType(CostConstants.SETTLEMENT);
            cost.setAmount(vatReport.getTotalOfficeCosts());
            cost.setDescription("Total office costs");
            costRepository.save(cost);
        }
        if (vatReport.getTotalOtherCosts().compareTo(ZERO) > 0) {
            Cost cost = new Cost();
            cost.setUser(username);
            cost.setDate(vatReport.getLatestTransactionDate());
            cost.setCostType(CostConstants.EXPENSE_CURRENT_ACCOUNT);
            cost.setAmount(vatReport.getTotalOtherCosts());
            cost.setDescription("Total other costs");
            costRepository.save(cost);
        }
        if (vatReport.getTotalFoodCosts().compareTo(ZERO) > 0) {
            Cost cost = new Cost();
            cost.setUser(username);
            cost.setDate(vatReport.getLatestTransactionDate());
            cost.setCostType(CostConstants.BUSINESS_FOOD);
            cost.setAmount(vatReport.getTotalFoodCosts());
            cost.setDescription("Total food costs");
            costRepository.save(cost);
        }
        if (vatReport.getTotalVatOut().compareTo(ZERO) > 0) {
            Cost cost = new Cost();
            cost.setUser(username);
            cost.setDate(vatReport.getLatestTransactionDate());
            cost.setCostType(CostConstants.EXPENSE_CURRENT_ACCOUNT);
            cost.setVat(vatReport.getTotalVatOut());
            cost.setDescription("Total VAT out");
            costRepository.save(cost);
        }
        if (vatReport.getTotalVatIn().compareTo(ZERO) > 0) {
            Cost cost = new Cost();
            cost.setUser(username);
            cost.setDate(vatReport.getLatestTransactionDate());
            cost.setCostType(CostConstants.INCOME_CURRENT_ACCOUNT);
            cost.setVat(vatReport.getTotalVatIn());
            cost.setDescription("Total VAT in");
            costRepository.save(cost);
        }
        if (vatReport.getLatestTransactionDate().getYear() < LocalDate.now().getYear()) {
            if (vatReport.getVatSaldo().compareTo(ZERO) > 0) {
                BookValue bookValue = new BookValue();
                bookValue.setUser(username);
                bookValue.setBalanceType(BalanceType.VAT_TO_BE_PAID);
                bookValue.setSaldo(vatReport.getVatSaldo().toBigInteger());
                bookValue.setBookYear(LocalDate.now().getYear() - 1);
                bookRepository.save(bookValue);
            }
        }
    }

    private String getUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        return jwtTokenUtil.getUsernameFromToken(token);
    }
}
