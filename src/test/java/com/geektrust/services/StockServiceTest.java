package com.geektrust.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.geektrust.services.impl.StockService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StockServiceTest {

    private final IStockService stockServiceMock = new StockService();

    @Test
    @DisplayName("stock service should return list of stocks in given fund")
    void stockServiceTest() {
        // Arrange
        List<String> expectedOutput = new ArrayList<>(Arrays.asList("MPHASIS LIMITED",
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
        
        // Act
        List<String> actualOutput = stockServiceMock.getStocksByFundName("PARAG_PARIKH_FLEXI_CAP");

        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}
