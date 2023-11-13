package test;

import models.CreateUserRequestModel;
import models.CreateUserResponseModel;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static spec.Specs.createUserRequestSpec;
import static spec.Specs.createUserResponseSpec;

@Tag("createUser")
public class CreateUserTest extends TestBase {
    @Test
    @DisplayName("Успешное создание пользователя")
    void successCreateUser() {
        CreateUserRequestModel regBody = new CreateUserRequestModel();
        regBody.setName("morpheus");
        regBody.setJob("leader");

        CreateUserResponseModel userCreateResponse = step("Запрос на создание пользователя", () ->
                given(createUserRequestSpec)
                        .body(regBody)
                        .post("/users")
                        .then()
                        .spec(createUserResponseSpec)
                        .extract().as(CreateUserResponseModel.class));

        step("Верификация созданного пользователя", () -> {
            assertEquals("morpheus", userCreateResponse.getName());
            assertEquals("leader", userCreateResponse.getJob());
            assertNotNull(userCreateResponse.getId());
            assertNotNull(userCreateResponse.getCreatedAt());
        });
    }
}
