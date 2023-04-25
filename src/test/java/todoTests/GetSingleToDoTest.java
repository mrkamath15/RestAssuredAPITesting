package todoTests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetSingleToDoTest extends BaseTest {

    @Test
    public void getSingleToDoTest() {
        given()
                .spec(spec)
                .when()
                .get("todos/{id}", 5)
                .then()
                .statusCode(200)
                .body("userId", notNullValue())
                .body("id", equalTo(5))
                .body("title", notNullValue())
                .body("completed", notNullValue());
    }
}
