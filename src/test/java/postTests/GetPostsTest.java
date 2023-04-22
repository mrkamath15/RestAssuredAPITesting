package postTests;

import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.Post;
import utils.Constants;

import java.io.File;

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
                .body("$.size()", equalTo(100))
                .body("userId", hasItems(notNullValue()))
                .body("id", hasItems(notNullValue()))
                .body("title", hasItems(notNullValue()))
                .body("body", hasItems(notNullValue()));
    }

    @Test
    public void getPostsUsingPojo() {
        Post[] post = given()
                .spec(spec)
                .when()
                .get("posts")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(Post[].class);

        for (Post eachPost : post) {
            Assert.assertNotNull(eachPost.getUserId());
            Assert.assertNotNull(eachPost.getId());
            Assert.assertNotNull(eachPost.getTitle());
            Assert.assertNotNull(eachPost.getBody());
            System.out.println(eachPost.getId() + " => " + eachPost.getTitle());
        }
    }

    @Test
    public void getPostsResponseTimeTest() {
        given()
                .spec(spec)
                .when()
                .get("posts")
                .then()
                .statusCode(200)
                .time(lessThan(5000L));
    }

    @Test
    public void getPostsSchemaValidationTest() {
        given()
                .spec(spec)
                .when()
                .get("posts")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(Constants.GET_POSTS_SCHEMA_PATH)));
    }
}
