package commentsTests;

import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.Comment;
import utils.Constants;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

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
                .body("postId", hasItems(notNullValue()))
                .body("id", hasItems(notNullValue()))
                .body("name", hasItems(notNullValue()))
                .body("email", hasItems(notNullValue()))
                .body("body", hasItems(notNullValue()));
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
}