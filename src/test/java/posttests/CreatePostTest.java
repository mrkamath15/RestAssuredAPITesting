package posttests;

import base.BaseTest;
import org.testng.annotations.Test;
import pojos.Post;
import utils.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreatePostTest extends BaseTest {

  @Test
  public void createPostUsingStringBody() {
    given()
      .spec(spec)
      .body("{\n" +
        "    \"userId\": 5,\n" +
        "    \"title\": \"RK Title\",\n" +
        "    \"body\": \"RK Body\"\n" +
        "}")
      .when()
      .post("posts")
      .then()
      .statusCode(201)
      .body("userId", greaterThanOrEqualTo(1))
      .body("title", notNullValue())
      .body("body", notNullValue())
      .body("id", equalTo(101));
  }

  @Test
  public void createPostUsingObjectBody() {
    Post createPost = new Post();
    createPost.setUserId(5);
    createPost.setTitle("RK1");
    createPost.setBody("RK2");

    given()
      .spec(spec)
      .body(createPost)
      .when()
      .post("posts")
      .then()
      .statusCode(201)
      .body("userId", greaterThanOrEqualTo(1))
      .body("title", notNullValue())
      .body("body", notNullValue())
      .body("id", equalTo(101));
  }

  @Test
  public void createPostUsingExternalFileBody() throws FileNotFoundException {
    given()
      .spec(spec)
      .body(new FileInputStream(new File(Constants.CREATE_POST_JSON_PATH)))
      .when()
      .post("posts")
      .then()
      .statusCode(201)
      .body("userId", greaterThanOrEqualTo(1))
      .body("title", notNullValue())
      .body("body", notNullValue())
      .body("id", equalTo(101));
  }
}
