package test;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.lang.module.Configuration;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeEach
    static void setup() {
        Configuration.baseUrl = "https://leroymerlin.ru";
        RestAssured.baseURI = "https://leroymerlin.ru";
        Configuration.pageLoadStrategy = "eager";
    }
    @AfterEach
    void shutDown() {
        closeWebDriver();
    }
}
