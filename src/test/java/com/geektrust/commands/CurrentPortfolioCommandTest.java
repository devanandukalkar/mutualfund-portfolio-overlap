package com.geektrust.commands;

import java.util.Collections;

import com.geektrust.services.impl.PortfolioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CurrentPortfolioCommandTest {

    @Mock
    PortfolioService portfolioService;

    @InjectMocks
    CurrentPortfolioCommand currentPortfolioCommand;

    @Test
    @DisplayName("CurrentPortfolioCommand test should throw exception is funds are not added to portfolio")
    void currentPortfolioCommandTest() {
        // Assert
        Assertions.assertThrows(RuntimeException.class,
                () -> currentPortfolioCommand.invoke(Collections.singletonList("")));
    }
}
