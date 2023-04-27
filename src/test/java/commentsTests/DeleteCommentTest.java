package commentsTests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DeleteCommentTest extends BaseTest {

    @Test
    public void deleteCommentTest() {
        given()
                .spec(spec)
                .when()
                .delete("comments/1")
                .then().statusCode(200)
                .body("isEmpty()", is(true));
    }
}
