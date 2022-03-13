package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;

public class HomeWorkTest {

    @Test
    void listUsers() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("total", is(12))
                .body("data.email[0]", is("michael.lawson@reqres.in"));
    }

    @Test
    void singleUsers() {
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"));
    }

    @Test
    void createUsers()   {
        String data = "{\"name\": \"morpheus\",\"job\": \"leader\"}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void updateUsers()   {
        String data = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void deleteUsers()   {

        given()
                .contentType(JSON)
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);

    }
}