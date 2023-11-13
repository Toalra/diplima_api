package test;

import models.RegisterUserRequestModel;
import models.RegisterResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spec.Specs.registerRequestSpec;
import static spec.Specs.registerResponseSpec;

@Tag("registerUser")
public class RegisterUserTest extends TestBase {
    @Test
    @DisplayName("Успешная регистрация пользователя")
    void successRegistrationUserTest() {
        RegisterUserRequestModel regBody = new RegisterUserRequestModel();
        regBody.setEmail("eve.holt@reqres.in");
        regBody.setPassword("pistol");

        RegisterResponseModel response = step("Запрос на регистрацию пользователя", () ->
                given(registerRequestSpec)
                        .body(regBody)
                        .post("/register")
                        .then()
                        .spec(registerResponseSpec)
                        .extract().as(RegisterResponseModel.class));
        step("Верификация зарегистрированного пользователя", () -> {
            assertEquals("4", response.getId());
            assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        });
    }

}
