package postTests;

import base.BaseTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetSinglePostTest extends BaseTest {

    @Test
    public void getSinglePostTest() {
        given()
                .spec(spec)
                .when()
                .get("posts/{postId}", 1)
                .then()
                .statusCode(200)
                .body("userId", notNullValue())
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue());
    }

    @Test
    public void getSinglePostUsingPathParamTest() {
        given()
                .spec(spec)
                .pathParam("postId", 1)
                .when()
                .get("posts/{postId}")
                .then()
                .statusCode(200)
                .body("userId", notNullValue())
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue());
    }
}
