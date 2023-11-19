package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static spec.SpecsDelete.deleteUserRequestSpec;
import static spec.SpecsDelete.deleteUserResponseSpec;

@Tag("deleteUser")
public class DeleteUserTest extends TestBase {
    @Test
    @DisplayName("Success delete user")
    void successDeleteUser() {
        step("Delete user", () -> {
            given(deleteUserRequestSpec)
                    .delete("/users/2")
                    .then()
                    .spec(deleteUserResponseSpec);
        });
    }
}
