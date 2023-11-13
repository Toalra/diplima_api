package test;

import models.LoginUserRequestModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spec.Specs.loginRequestSpec;
import static spec.Specs.loginResponseSpec;

@Tag("loginUser")
public class LoginUserTest extends TestBase {
    @Test
    @DisplayName("Успешный вход пользователя")
    void successLoginUser() {
        LoginUserRequestModel regBody = new LoginUserRequestModel();
        regBody.setEmail("eve.holt@reqres.in");
        regBody.setPassword("cityslicka");

        LoginResponseModel loginResponse = step("Запрос на логин пользователя", () ->
                given(loginRequestSpec)
                        .body(regBody)
                        .post("/login")
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseModel.class));
        step("Верификация залогиненного пользователя", () ->
                assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken()));
    }
}
