package com.geektrust.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.geektrust.entities.Fund;
import com.geektrust.repositories.impl.FundRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FundRepositoryTest {

    private IFundRepository fundRepository;

    @BeforeEach
    void setup() {
        List<String> stocksList = new ArrayList<>(Arrays.asList("MPHASIS LIMITED",
                "COMPUTER AGE MANAGEMENT SERVICES LIMITED", "AMAZON.COM INC",
                "SUZUKI MOTOR CORPORATION*", "FACEBOOK INC", "INDIAN ENERGY EXCHANGE LIMITED",
                "BAJAJ HOLDINGS & INVESTMENT LIMITED", "MICROSOFT CORPORATION",
                "MULTI COMMODITY EXCHANGE OF INDIA LIMITED",
                "CENTRAL DEPOSITORY SERVICES (I) LIMITED", "PERSISTENT SYSTEMS LIMITED",
                "HCL TECHNOLOGIES LIMITED", "DR. REDDY'S LABORATORIES LIMITED", "ALPHABET INC.",
                "SUN PHARMACEUTICAL INDUSTRIES LIMITED", "HERO MOTOCORP LIMITED",
                "AXIS BANK LIMITED", "ICICI BANK LIMITED", "TATA MOTORS LIMITED",
                "HDFC BANK LIMITED", "CIPLA LIMITED", "LUPIN LIMITED", "IPCA LABORATORIES LIMITED",
                "MARUTI SUZUKI INDIA LIMITED", "ORACLE FINANCIAL SERVICES SOFTWARE LIMITED",
                "CADILA HEALTHCARE LIMITED", "ICRA LIMITED", "ITC LIMITED", "TATA STEEL LIMITED",
                "BALKRISHNA INDUSTRIES LIMITED"));

        final Map<String, Fund> fundMap = new HashMap<String, Fund>() {
            {
                put("PARAG_PARIKH_FLEXI_CAP", new Fund(1, "PARAG_PARIKH_FLEXI_CAP", stocksList));
            }
        };

        fundRepository = new FundRepository(fundMap);
    }

    @Test
    @DisplayName("Save method should save create and return the fund")
    void saveRepositoryTest() {
        // Arrange
        List<String> stocks = new ArrayList<>(Arrays.asList("INFOSYS LIMITED",
                "BHARTI AIRTEL LIMITED", "MOTHERSON SUMI SYSTEMS LIMITED",
                "TATA CONSUMER PRODUCTS LIMITED", "SHREE CEMENT LIMITED", "NESTLE INDIA LIMITED",
                "HDFC LIFE INSURANCE COMPANY LIMITED", "ULTRATECH CEMENT LIMITED",
                "RELIANCE INDUSTRIES LIMITED", "KOTAK MAHINDRA BANK LIMITED",
                "ASIAN PAINTS LIMITED", "GLAND PHARMA LIMITED", "TATA CONSULTANCY SERVICES LIMITED",
                "AVENUE SUPERMARTS LIMITED", "DR. REDDY'S LABORATORIES LIMITED",
                "CHOLAMANDALAM INVESTMENT AND FINANCE COMPANY LIMITED",
                "HINDUSTAN UNILEVER LIMITED", "WIPRO LIMITED", "LARSEN & TOUBRO LIMITED",
                "ICICI BANK LIMITED", "ICICI LOMBARD GENERAL INSURANCE COMPANY LIMITED",
                "TATA MOTORS LIMITED", "HDFC BANK LIMITED", "CIPLA LIMITED",
                "MARUTI SUZUKI INDIA LIMITED", "HOUSING DEVELOPMENT FINANCE CORPORATION LIMITED",
                "PIDILITE INDUSTRIES LIMITED", "RELIANCE INDUSTRIES LIMITED - PARTLY PAID SHARES",
                "TORRENT PHARMACEUTICALS LIMITED", "BAJAJ FINANCE LIMITED", "TITAN COMPANY LIMITED",
                "MAHINDRA & MAHINDRA LIMITED", "DIVI'S LABORATORIES LIMITED"));

        final Fund fundToInsert = new Fund("AXIS_BLUECHIP", stocks);
        Fund expectedOutput = new Fund(2, "AXIS_BLUECHIP", stocks);

        // Act
        Fund actualOutput = fundRepository.save(fundToInsert);

        // Assert
        Assertions.assertTrue(expectedOutput.getFundName().equals(actualOutput.getFundName())
                && expectedOutput.getStocksInFund().equals(actualOutput.getStocksInFund()));
    }
}
