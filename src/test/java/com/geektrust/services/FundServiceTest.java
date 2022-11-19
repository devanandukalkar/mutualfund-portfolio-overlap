package com.geektrust.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.geektrust.entities.Fund;
import com.geektrust.repositories.IFundRepository;
import com.geektrust.services.impl.FundService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FundServiceTest {

    @Mock
    private IFundRepository fundRepositoryMock;

    @Mock
    private IStockService stockServiceMock;

    @InjectMocks
    private FundService fundService;

    @Test
    @DisplayName("createFund method should create fund")
    void createFundTestShouldReturnFund() {
        // Arrange
        List<String> stocksInFund = new ArrayList<>(Arrays.asList("MPHASIS LIMITED",
        "COMPUTER AGE MANAGEMENT SERVICES LIMITED",
        "AMAZON.COM INC",
        "SUZUKI MOTOR CORPORATION*",
        "FACEBOOK INC",
        "INDIAN ENERGY EXCHANGE LIMITED",
        "BAJAJ HOLDINGS & INVESTMENT LIMITED",
        "MICROSOFT CORPORATION",
        "MULTI COMMODITY EXCHANGE OF INDIA LIMITED",
        "CENTRAL DEPOSITORY SERVICES (I) LIMITED",
        "PERSISTENT SYSTEMS LIMITED",
        "HCL TECHNOLOGIES LIMITED",
        "DR. REDDY'S LABORATORIES LIMITED",
        "ALPHABET INC.",
        "SUN PHARMACEUTICAL INDUSTRIES LIMITED",
        "HERO MOTOCORP LIMITED",
        "AXIS BANK LIMITED",
        "ICICI BANK LIMITED",
        "TATA MOTORS LIMITED",
        "HDFC BANK LIMITED",
        "CIPLA LIMITED",
        "LUPIN LIMITED",
        "IPCA LABORATORIES LIMITED",
        "MARUTI SUZUKI INDIA LIMITED",
        "ORACLE FINANCIAL SERVICES SOFTWARE LIMITED",
        "CADILA HEALTHCARE LIMITED",
        "ICRA LIMITED",
        "ITC LIMITED",
        "TATA STEEL LIMITED",
        "BALKRISHNA INDUSTRIES LIMITED"));

        Fund expectedFundOutput = new Fund(1, "PARAG_PARIKH_FLEXI_CAP", stocksInFund);
        when(fundRepositoryMock.save(any(Fund.class))).thenReturn(expectedFundOutput);

        // Act
        Fund actualFundOutput = fundService.createFund("PARAG_PARIKH_FLEXI_CAP");

        // Assert
        Assertions.assertEquals(expectedFundOutput, actualFundOutput);
        verify(fundRepositoryMock, times(1)).save(any(Fund.class));
    }
}
