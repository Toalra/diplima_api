package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static spec.Specs.deleteUserRequestSpec;
import static spec.Specs.deleteUserResponseSpec;

@Tag("deleteUser")
public class DeleteUserTest extends TestBase {
    @Test
    @DisplayName("Успешное удаление пользователя")
    void successDeleteUser() {
        step("Удаление пользователя", () -> {
            given(deleteUserRequestSpec)
                    .delete("/users/2")
                    .then()
                    .spec(deleteUserResponseSpec);
        });
    }
}
