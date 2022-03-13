package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SelenoinTests {

    // обращаемся к https://selenoid.autotests.cloud/status
    // total is 20 - всего 20 сессий

    @Test
    void checkTotal20(){
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status") //-отправили запрос на сайт
                .then()
                .statusCode(200) //получили ответ, что всё ок
                .body("total", is(20)); //проверили данные
    }

    @Test
    void checkTotalWithoutGiven(){
       get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkChromeVersion(){
        get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("browsers.chrome", hasKey ("91.0"));
    }

    @Test
    void checkTotalBadPractice(){ //нестабильный, не универсальный тест - не надо там делать
        String response =
                get("https://selenoid.autotests.cloud/status")
                        .then()
                        .statusCode(200)
                        .extract().response().asString();

    System.out.println("Respons: " + response);
        String expectedResponse = "{\"total\":20,\"used\":0,\"queued\":0,\"pending\":0," +
                "\"browsers\":" +
                "{\"android\":{\"8.1\":{}}," +
                "\"chrome\":{\"90.0\":{},\"91.0\":{}}," +
                "\"firefox\":{\"88.0\":{},\"89.0\":{}}," +
                "\"opera\":{\"76.0\":{},\"77.0\":{}}}}\n";
        assertEquals(expectedResponse, response);
    }

    @Test
    void checkTotalGoodPractice(){ //стабильный, универсальный тест
        Integer response =
                get("https://selenoid.autotests.cloud/status")
                        .then()
                        .statusCode(200)
                        .extract().path("total");

        System.out.println("Respons: " + response);

        assertEquals(20, response); //
    }

    @Test
    void responseExamples(){
        Response response =
                get("https://selenoid.autotests.cloud/status")
                        .then()
                        .extract().response();

        System.out.println("Respons: " + response);
        System.out.println("Respons .toString(): " + response.asString());
        System.out.println("Respons .path(\"total\"): " + response.path("total"));
        System.out.println("Respons .path(\"browsers.chrome\"): " + response.path("browsers.chrome"));
    }

    @Test
    void checkStatus200(){
       get("https://user1:1234@selenoid.autotests.cloud/wd/hub/status")
                .then()
                .statusCode(200);
    }

    @Test
    void checkStatus200WithAuth(){
        given()
                .auth().basic("user1", "1234")
                .when()
                .get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .statusCode(200);


    }
}

