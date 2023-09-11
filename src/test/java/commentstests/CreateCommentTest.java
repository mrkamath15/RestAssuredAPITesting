package commentstests;

import base.BaseTest;
import org.testng.annotations.Test;
import pojos.Comment;
import utils.Constants;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateCommentTest extends BaseTest {

  @Test
  public void createCommentUsingStringBody() {
    given().spec(spec)
      .body("{\n" +
        "    \"postId\": 1,\n" +
        "    \"name\": \"RK labore ex et quam laborum\",\n" +
        "    \"email\": \"RK@gardner.biz\",\n" +
        "    \"body\": \"RK\"\n" +
        "}")
      .when()
      .post("comments")
      .then()
      .statusCode(201)
      .body("postId", greaterThanOrEqualTo(1))
      .body("name", notNullValue())
      .body("email", notNullValue())
      .body("body", notNullValue())
      .body("id", greaterThanOrEqualTo(1));
  }

  @Test
  public void createCommentUsingObjectBody() {
    Comment comment = new Comment();
    comment.setPostId(99);
    comment.setName("RK");
    comment.setEmail("RK@RK.com");
    comment.setBody("RK body");

    given()
      .spec(spec)
      .body(comment)
      .when()
      .post("comments")
      .then()
      .statusCode(201)
      .body("postId", greaterThanOrEqualTo(1))
      .body("name", notNullValue())
      .body("email", notNullValue())
      .body("body", notNullValue())
      .body("id", greaterThanOrEqualTo(1));
  }

  @Test
  public void createCommentUsingExternalFileBody() {
    given()
      .spec(spec)
      .body(new File(Constants.CREATE_COMMENT_JSON_PATH))
      .when()
      .post("comments")
      .then()
      .statusCode(201)
      .body("postId", greaterThanOrEqualTo(1))
      .body("name", notNullValue())
      .body("email", notNullValue())
      .body("body", notNullValue())
      .body("id", greaterThanOrEqualTo(1));
  }
}
