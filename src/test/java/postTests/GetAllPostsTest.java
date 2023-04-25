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

public class GetAllPostsTest extends BaseTest {

    @Test
    public void getAllPostsTest() {
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
    public void getAllPostsUsingPojoTest() {
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
    public void testSinglePostValues() {
        given()
                .spec(spec)
                .when()
                .get("posts")
                .then()
                .statusCode(200)
                .body("userId[0]", equalTo(1))
                .body("id[0]",equalTo(1))
                .body("title[0]", startsWith("sunt aut facere"))
                .body("body[0]", endsWith("eveniet architecto"));
    }

    @Test
    public void getAllPostsResponseTimeTest() {
        given()
                .spec(spec)
                .when()
                .get("posts")
                .then()
                .statusCode(200)
                .time(lessThan(5000L));
    }

    @Test
    public void getAllPostsSchemaValidationTest() {
        given()
                .spec(spec)
                .when()
                .get("posts")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(Constants.GET_ALL_POSTS_SCHEMA_PATH)));
    }

    @Test
    public void getPostByIdTest() {
        given()
                .spec(spec)
                .queryParam("id", 5)
                .when()
                .get("posts")
                .then()
                .statusCode(200)
                .body("userId[0]", notNullValue())
                .body("id[0]", equalTo(5))
                .body("title[0]", notNullValue())
                .body("body[0]", notNullValue());
    }

    @Test
    public void getPostByUserIdTest() {
        given()
                .spec(spec)
                .queryParam("userId", 5)
                .when()
                .get("posts")
                .then()
                .statusCode(200)
                .body("userId", everyItem(equalTo(5)))
                .body("id", everyItem(notNullValue()))
                .body("title", everyItem(notNullValue()))
                .body("body", everyItem(notNullValue()));
    }
}
