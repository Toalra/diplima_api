package test;

import models.RegisterUserRequestModel;
import models.RegisterResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spec.SpecsRegister.registerRequestSpec;
import static spec.SpecsRegister.registerResponseSpec;

@Tag("registerUser")
public class RegisterUserTest extends TestBase {
    @Test
    @DisplayName("Success register user")
    void successRegistrationUserTest() {
        RegisterUserRequestModel regBody = new RegisterUserRequestModel();
        regBody.setEmail("eve.holt@reqres.in");
        regBody.setPassword("pistol");

        RegisterResponseModel response = step("Request register user", () ->
                given(registerRequestSpec)
                        .body(regBody)
                        .post("/register")
                        .then()
                        .spec(registerResponseSpec)
                        .extract().as(RegisterResponseModel.class));
        step("Verification registered user", () -> {
            assertEquals("4", response.getId());
            assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        });
    }

}
