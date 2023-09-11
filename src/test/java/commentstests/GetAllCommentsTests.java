package commentstests;

import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.Comment;
import utils.Constants;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;

public class GetAllCommentsTests extends BaseTest {

  @Test
  public void getAllCommentsTest() {
    given()
      .spec(spec)
      .when()
      .get("comments")
      .then()
      .statusCode(200)
      .body("$", hasSize(greaterThan(1)))
      .body("$.size()", equalTo(500))
      .body("postId", everyItem(notNullValue()))
      .body("id", everyItem(notNullValue()))
      .body("name", everyItem(notNullValue()))
      .body("email", everyItem(notNullValue()))
      .body("body", everyItem(notNullValue()));
  }

  @Test
  public void getAllCommentsUsingPojoTest() {
    Comment[] comments = given()
      .spec(spec)
      .when()
      .get("comments")
      .then()
      .statusCode(200)
      .extract()
      .response()
      .as(Comment[].class);

    for (Comment eachComment : comments) {
      Assert.assertNotNull(eachComment.getPostId());
      Assert.assertNotNull(eachComment.getId());
      Assert.assertNotNull(eachComment.getName());
      Assert.assertNotNull(eachComment.getEmail());
      Assert.assertNotNull(eachComment.getBody());
    }
  }

  @Test
  public void testSingleCommentValues() {
    given()
      .spec(spec)
      .when()
      .get("comments")
      .then()
      .statusCode(200)
      .body("postId[0]", equalTo(1))
      .body("id[0]", equalTo(1))
      .body("name[0]", startsWith("id labore"))
      .body("email[0]", endsWith("@gardner.biz"))
      .body("body[0]", containsString("quidem magnam voluptate"));
  }

  @Test
  public void getAllCommentsResponseTimeTest() {
    given()
      .spec(spec)
      .when()
      .get("comments")
      .then()
      .statusCode(200)
      .time(lessThan(5000L));
  }

  @Test
  public void getAllCommentsSchemaValidationTest() {
    given()
      .spec(spec)
      .when()
      .get("comments")
      .then()
      .statusCode(200)
      .body(JsonSchemaValidator.matchesJsonSchema(new File(Constants.GET_ALL_COMMENTS_SCHEMA_PATH)));
  }

  @Test
  public void getCommentsByPostIdTest() {
    given()
      .spec(spec)
      .when()
      .get("comments?postId={id}", 100)
      .then()
      .statusCode(200)
      .body("postId", everyItem(equalTo(100)))
      .body("id", everyItem(notNullValue()))
      .body("name", everyItem(notNullValue()))
      .body("email", everyItem(notNullValue()))
      .body("body", everyItem(notNullValue()));
  }

  @Test
  public void getCommentsByPostIdAsQueryParamTest() {
    given()
      .spec(spec)
      .queryParam("postId", 50)
      .when()
      .get("comments")
      .then()
      .statusCode(200)
      .body("postId", everyItem(equalTo(50)))
      .body("id", everyItem(notNullValue()))
      .body("name", everyItem(notNullValue()))
      .body("email", everyItem(notNullValue()))
      .body("body", everyItem(notNullValue()));
  }
}
