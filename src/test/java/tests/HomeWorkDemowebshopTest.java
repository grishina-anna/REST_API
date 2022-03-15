package tests;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class HomeWorkDemowebshopTest {

    @Test
    void addToCartWithCookieTest() {
        ValidatableResponse response =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .cookie("Nop.customer=359c8211-0cf7-496c-b8a7-7256023dedec; " +
                                "ARRAffinity=a1e87db3fa424e3b31370c78def315779c40ca259e77568bef2bb9544f63134e; " +
                                "__utmc=78382081; __utmz=78382081.1647281695.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); " +
                                "__utma=78382081.192859927.1647281695.1647345228.1647369628.4; __utmt=1; nop.CompareProducts=; " +
                                "NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=28&RecentlyViewedProductIds=74&RecentlyViewedProductIds=17&RecentlyViewedProductIds=72; " +
                                "__atuvc=22%7C11; __atuvs=6230dd97af706b5d00a; __utmb=78382081.32.10.1647369628; " +
                                "ARRAffinity=a1e87db3fa424e3b31370c78def315779c40ca259e77568bef2bb9544f63134e; Nop.customer=359c8211-0cf7-496c-b8a7-7256023dedec")
                        .body("product_attribute_28_7_10=25&product_attribute_28_1_11=29&addtocart_28.EnteredQuantity=1")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/28/2")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"))
                        .body("updatetopwishlistsectionhtml", is("(11)"));
    }
}

