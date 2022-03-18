package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class BookstoreTest {
    @BeforeAll
    static void setup() {
        RestAssured.baseURI= "https://demoqa.com";
    }

    @Test
    void getBookTest() {
        get("/BookStore/v1/Books")
                .then()
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    void getBookWithAllLogsTest() {
        given()
                .log().all()
                .when()
                .log().body()
                .get("/BookStore/v1/Books")
                .then()
                .log().all()
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    void getBookWithSomeLogsTest() {
        given()
                .log().uri()
                .log().body()
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .log().status()
                .log().body()
                .log().ifError()
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    void generateTokenTest() {
        String data = "{ \\\"userName\\\": \\\"alex\\\"," +
                " \\\"password\\\": \\\"asdsad#frew_DFS2\\\"}";

        given()
                .contentType(JSON)
                .body(data)
                .log().uri()
                .log().body()
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("books", is("Success"))
                .body("result", is("User authorized successfully."))
                .body("token.size()", greaterThan(10));
    }
}

