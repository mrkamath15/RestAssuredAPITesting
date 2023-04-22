package postTests;

import base.BaseTest;
import io.restassured.internal.http.Status;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetPostsTest extends BaseTest {

    @Test
    public void getPostsTest() {
        given()
                .spec(spec)
                .when()
                .get("posts")
                .then()
                .statusCode(200)
                .body("$", hasSize(greaterThan(1)))
                .body("userId", hasItems(notNullValue()))
                .body("id", hasItems(notNullValue()))
                .body("title", hasItems(notNullValue()))
                .body("body", hasItems(notNullValue()));
    }
}
