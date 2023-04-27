package commentsTests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.startsWith;

public class CommentPatchTest extends BaseTest {

    @Test
    public void commentPatchTest() {
        given()
                .spec(spec)
                .body("{\n" +
                        "    \"email\": \"RKEliseo@gardner.biz\"\n" +
                        "}")
                .when()
                .patch("comments/1")
                .then()
                .statusCode(200)
                .body("email", startsWith("RK"));
    }
}
