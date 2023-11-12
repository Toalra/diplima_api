package api;

import static io.restassured.RestAssured.given;

public class AuthorizationApi {
    public LoginResponseModel login(CredentialsModel credentials) {
        return given(loginRequestSpec)
                .body(credentials)
                .when()
                .post("/login")
                .then()
                .spec(loginResponseSpec)
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
    }
}
