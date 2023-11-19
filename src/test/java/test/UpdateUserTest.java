package test;

import models.UpdateUserRequestModel;
import models.UpdateUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static spec.SpecsUpdate.updateUserRequestSpec;
import static spec.SpecsUpdate.updateUserResponseSpec;

@Tag("updateUser")
public class UpdateUserTest extends TestBase {
    @Test
    @DisplayName("Success update user")
    void successUpdateUser() {
        UpdateUserRequestModel updateUser = new UpdateUserRequestModel();
        updateUser.setName("morpheus");
        updateUser.setJob("zion resident");

        UpdateUserResponseModel userUpdateResponse = step("Request update user", () ->
                given(updateUserRequestSpec)
                        .body(updateUser)
                        .when()
                        .put("/users/2")
                        .then()
                        .spec(updateUserResponseSpec)
                        .extract().as(UpdateUserResponseModel.class));

        step("Verification updated user", () -> {
            assertEquals("morpheus", userUpdateResponse.getName());
            assertEquals("zion resident", userUpdateResponse.getJob());
            assertNotNull(userUpdateResponse.getUpdatedAt());
        });
    }
}
