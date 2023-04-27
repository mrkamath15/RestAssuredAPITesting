package commentsTests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class UpdateCommentTest extends BaseTest {

    @Test
    public void updateCommentTest() {
        given()
                .spec(spec)
                .body("{\n" +
                        "    \"postId\": 1,\n" +
                        "    \"name\": \"RK labore ex et quam laborum\",\n" +
                        "    \"email\": \"RK@gardner.biz\",\n" +
                        "    \"body\": \"RK body\"\n" +
                        "}")
                .when()
                .put("comments/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("postId", equalTo(1))
                .body("name", startsWith("RK"))
                .body("email", startsWith("RK"))
                .body("body", startsWith("RK"));
    }
}
