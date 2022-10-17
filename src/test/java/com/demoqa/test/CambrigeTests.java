package com.demoqa.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class CambrigeTests {

    @BeforeEach
    void setUp() {
        open("https://dictionary.cambridge.org/");
    }

    @Disabled("It is very simple test")
    @Test
    @DisplayName("Check a number of result for Cambridge dictionary search for \"guttersnipe\" request")
    void cambridgeSearchOneWordTest() {
        String testData = "guttersnipe";
        $("#searchword").setValue(testData);
        $("button[type='submit']").click();
        $x("//h2[contains(text(),'Examples')]")
                .shouldHave(text("EXAMPLES of " + testData));
    }

    @ValueSource(strings = {"guttersnipe", "advantage"})
    // TEST DATA: ["guttersnipe", "advantage"]
    @ParameterizedTest
    @DisplayName("Check a number of result for Cambridge dictionary search for [test_data][0] request")
        // [test_data] == (String testData)
    void cambridgeSearchTwoWordTest(String testData) {
        $("#searchword").setValue(testData);
        $("button[type='submit']").click();
        $x("//h2[contains(text(),'Examples')]")
                .shouldHave(text("EXAMPLES of " + testData));
    }

    @CsvSource(value = {
            "guttersnipe, a child from a poor area of a town who is dirty and badly dressed :",
            "advantage,  a condition giving a greater chance of success:"
    }

    )
    @ParameterizedTest
    @DisplayName("Check a number of result for Cambridge dictionary search for [test_data][0] request")
        // [test_data] == (String testData)
    void cambridgeSearchTwoParametersTest(String searchQuery, String expectedText) {
        $("#searchword").setValue(searchQuery);
        $("button[type='submit']").click();
        $("div.ddef_h")
                .shouldHave(text(expectedText));
    }

}
