package usersTests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;

public class GetSingleUserTest extends BaseTest {

    @Test
    public void getSingleUserTest() {
        given()
                .spec(spec)
                .when()
                .get("users/5")
                .then()
                .statusCode(200)
                .body("id", equalTo(5))
                .body("name", notNullValue())
                .body("username", notNullValue())
                .body("email", notNullValue())
                .body("address.street", notNullValue())
                .body("address.suite", notNullValue())
                .body("address.city", notNullValue())
                .body("address.zipcode", notNullValue())
                .body("address.geo.lat", notNullValue())
                .body("address.geo.lng", notNullValue())
                .body("phone", notNullValue())
                .body("website", notNullValue())
                .body("company.name", notNullValue())
                .body("company.catchPhrase", notNullValue())
                .body("company.bs", notNullValue());
    }
}
