package api;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class GoodsApi {
    public void deleteAllGoods(LoginResponseModel loginResponse) {
        given()
                .contentType(JSON)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                // .body(deleteBookData)
                .queryParams("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .statusCode(204);
    }

    public void addGood(LoginResponseModel loginResponse, AddBooksListModel bookList) {
        given()
                .contentType(JSON)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(bookList)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .statusCode(201);
    }

    public void deleteOneGood(LoginResponseModel loginResponse, DeleteBookModel deleteBook) {
        given()
                .contentType(JSON)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .body(deleteBook)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .statusCode(204);
    }
}
