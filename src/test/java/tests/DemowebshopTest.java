package tests;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class DemowebshopTest {
    @Test
    void addToCartTest(){
        Integer cartSize = 0;

        ValidatableResponse response =
       given()
               .contentType("application/x-www-form-urlencoded; charset=UTF-8")
               .cookie("Nop.customer=359c8211-0cf7-496c-b8a7-7256023dedec; " +
                       "ARRAffinity=a1e87db3fa424e3b31370c78def315779c40ca259e77568bef2bb9544f63134e;" +
                       " __utma=78382081.192859927.1647281695.1647281695.1647281695.1;" + " __utmc=78382081; __utmz=78382081.1647281695.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); " +
                       "NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=74&RecentlyViewedProductIds=72;" +
                       " __utmt=1; __atuvc=4%7C11; __atuvs=622f878a022c1205003; __utmb=78382081.7.10.1647281695")
               .body("product_attribute_74_5_26=81" +
                       "&product_attribute_74_6_27=83" +
                       "&product_attribute_74_3_28=86" +
                       "&addtocart_74.EnteredQuantity=1")
               .when()
               .post("http://demowebshop.tricentis.com/addproducttocart/details/74/1")
               .then()
               .log().all()
               .statusCode(200)
               .body("success", is(true))
               .body("message", is("The product has been added to your \u003ca href=\"/cart\"\u003eshopping cart\u003c/a\u003e"));

//    assertThat(response.extract().path("updatetopcartsectionhtml").toString())
//            .body("updatetopcartsectionhtml", is("(26)"));
    }
}
