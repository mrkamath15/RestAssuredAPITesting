package commentstests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetSingleCommentTest extends BaseTest {

  @Test
  public void getSingleCommentTest() {
    given()
      .spec(spec)
      .when()
      .get("comments/{commentId}", 50)
      .then()
      .statusCode(200)
      .body("postId", notNullValue())
      .body("id", equalTo(50))
      .body("name", notNullValue())
      .body("email", notNullValue())
      .body("body", notNullValue());
  }

}
