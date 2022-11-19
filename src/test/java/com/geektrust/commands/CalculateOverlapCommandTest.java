package com.geektrust.commands;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import com.geektrust.exceptions.FundNotFoundException;
import com.geektrust.services.IPortfolioService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CalculateOverlapCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @Mock
    IPortfolioService portfolioServiceMock;

    @InjectMocks
    CalculateOverlapCommand calculateOverlapCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("CalculateOverlapCommand should throw FundNotFoundException")
    void executeCalculateOverlapTestShouldPrintOutput() {
        // Arrange
        String expectedOutput = "FUND_NOT_FOUND";
        Mockito.doThrow(new FundNotFoundException(expectedOutput)).when(portfolioServiceMock).calculatePortfolioOverlap("NIPPON_INDIA_PHARMA_FUND");

        // Act
        calculateOverlapCommand.invoke(Arrays.asList("CALCULATE_OVERLAP", "NIPPON_INDIA_PHARMA_FUND"));

        // Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
        verify(portfolioServiceMock, times(1)).calculatePortfolioOverlap("NIPPON_INDIA_PHARMA_FUND");
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
