package com.geektrust;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AppIntegrationTest")
class AppTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


    @Test
    @DisplayName("Integration Test")
    void integrationTest() {
        // Arrange
        Path inputFile = Paths.get("sample_input/input2.txt");
        List<String> arguments = new ArrayList<>(Collections.singletonList(inputFile.toString()));
        String expectedOutput = "ICICI_PRU_NIFTY_NEXT_50_INDEX UTI_NIFTY_INDEX 20.37%\n"
                + "ICICI_PRU_NIFTY_NEXT_50_INDEX AXIS_MIDCAP 14.81%\n"
                + "ICICI_PRU_NIFTY_NEXT_50_INDEX PARAG_PARIKH_FLEXI_CAP 7.41%\n"
                + "FUND_NOT_FOUND\n" + "ICICI_PRU_NIFTY_NEXT_50_INDEX UTI_NIFTY_INDEX 20.37%\n"
                + "ICICI_PRU_NIFTY_NEXT_50_INDEX AXIS_MIDCAP 14.68%\n"
                + "ICICI_PRU_NIFTY_NEXT_50_INDEX PARAG_PARIKH_FLEXI_CAP 7.32%";

        // Act
        App.run(arguments);
        String actualOutput = outputStreamCaptor.toString().trim();
        actualOutput = actualOutput.replace("\r\n", " ").replace("\n", " ");
        expectedOutput = expectedOutput.replace("\n", " ");
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
