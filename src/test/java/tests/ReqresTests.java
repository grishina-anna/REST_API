package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;

public class ReqresTests {

    @Test
    void successfulLogin()   {
/*
   Request url: - запрос на сайте
   https://reqres.in/api/login

   Data: - данные
{
    "email": "eve.holt@reqres.in",
    "password": "cityslicka"
}

Respons: - результат
{
    "token": "QpwL5tke4Pnpja7X4"
}
 */

String data = "{ \"email\": \"eve.holt@reqres.in\", " +
        "\"password\": \"cityslicka\" }";

//делаем запрос
        given()
                .contentType(JSON) // необходимо указать тип контента
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }


    @Test
    void negativeLogin()   {

        String data = "{\"email\": \"peter@klaven\"}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
