package albumsTests;

import base.BaseTest;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.Album;
import utils.Constants;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetAllAlbumsTest extends BaseTest {

    @Test
    public void getAllAlbumsTest() {
        given()
                .spec(spec)
                .when()
                .get("albums")
                .then()
                .statusCode(200)
                .body("$", hasSize(greaterThan(1)))
                .body("$.size()", equalTo(100))
                .body("userId", everyItem(notNullValue()))
                .body("id", everyItem(notNullValue()))
                .body("title", everyItem(notNullValue()));
    }

    @Test
    public void getAllAlbumsUsingPojo() {
        Album[] albums = given()
                .spec(spec)
                .when()
                .get("albums")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(Album[].class);

        for (Album eachAlbum : albums) {
            Assert.assertNotNull(eachAlbum.getUserId());
            Assert.assertNotNull(eachAlbum.getId());
            Assert.assertNotNull(eachAlbum.getTitle());
        }
    }

    @Test
    public void testSingleAlbumValues() {
        given()
                .spec(spec)
                .when()
                .get("albums")
                .then()
                .statusCode(200)
                .body("userId[0]", equalTo(1))
                .body("id[0]", equalTo(1))
                .body("title[0]", startsWith("quidem"));
    }

    @Test
    public void getAllAlbumsResponseTimeTest() {
        given()
                .spec(spec)
                .when()
                .get("albums")
                .then()
                .statusCode(200)
                .time(lessThan(5000L));
    }

    @Test
    public void getAllAlbumsSchemaValidationTest() {
        given()
                .spec(spec)
                .when()
                .get("albums")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(Constants.GET_ALL_ALBUMS_SCHEMA_PATH)));
    }

    @Test
    public void getAlbumByIdTest() {
        given()
                .spec(spec)
                .queryParam("id", 5)
                .when()
                .get("albums")
                .then()
                .statusCode(200)
                .body("$.size()", equalTo(1))
                .body("userId[0]", notNullValue())
                .body("id[0]", equalTo(5))
                .body("title[0]", notNullValue());
    }

    @Test
    public void getAlbumsByUserIdTest() {
        given()
                .spec(spec)
                .queryParam("userId", 5)
                .when()
                .get("albums")
                .then()
                .statusCode(200)
                .body("$.size()", greaterThan(1))
                .body("id", everyItem(notNullValue()))
                .body("userId" , everyItem(equalTo(5)))
                .body("title", everyItem(notNullValue()));
    }
}
