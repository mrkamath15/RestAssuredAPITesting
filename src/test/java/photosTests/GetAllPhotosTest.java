package photosTests;

import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.Comment;
import pojos.Photo;
import utils.Constants;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetAllPhotosTest extends BaseTest {
    @Test
    public void getAllPhotosTest() {
        given()
                .spec(spec)
                .when()
                .get("photos")
                .then()
                .statusCode(200)
                .body("$", hasSize(greaterThan(1)))
                .body("$.size()", equalTo(5000))
                .body("albumId", everyItem(notNullValue()))
                .body("id", everyItem(notNullValue()))
                .body("title", everyItem(notNullValue()))
                .body("url", everyItem(notNullValue()))
                .body("thumbnailUrl", everyItem(notNullValue()));
    }

    @Test
    public void getAllPhotosUsingPojoTest() {
        Photo[] photos = given()
                .spec(spec)
                .when()
                .get("photos")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(Photo[].class);

        for (Photo eachPhoto : photos) {
            Assert.assertNotNull(eachPhoto.getAlbumId());
            Assert.assertNotNull(eachPhoto.getId());
            Assert.assertNotNull(eachPhoto.getTitle());
            Assert.assertNotNull(eachPhoto.getUrl());
            Assert.assertNotNull(eachPhoto.getThumbnailUrl());
        }
    }

    @Test
    public void testSinglePhotoValues() {
        given()
                .spec(spec)
                .when()
                .get("photos")
                .then()
                .statusCode(200)
                .body("albumId[0]", equalTo(1))
                .body("id[0]", equalTo(1))
                .body("title[0]", startsWith("accusamus"))
                .body("url[0]", startsWith("https://via.placeholder.com"))
                .body("thumbnailUrl[0]", containsString("https://via.placeholder.com"));
    }

    @Test
    public void getAllPhotosResponseTimeTest() {
        given()
                .spec(spec)
                .when()
                .get("photos")
                .then()
                .statusCode(200)
                .time(lessThan(5000L));
    }

    @Test
    public void getAllPhotosSchemaValidationTest() {
        given()
                .spec(spec)
                .when()
                .get("photos")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(Constants.GET_ALL_PHOTOS_SCHEMA_PATH)));
    }

    @Test
    public void getPhotosByIdTest() {
        given()
                .spec(spec)
                .queryParam("id", 100)
                .when()
                .get("photos")
                .then()
                .statusCode(200)
                .body("albumId[0]", notNullValue())
                .body("id[0]", equalTo(100))
                .body("title[0]", notNullValue())
                .body("url[0]", notNullValue())
                .body("thumbnailUrl[0]", notNullValue());
    }

    @Test
    public void getPhotosByAlbumIdTest() {
        given()
                .spec(spec)
                .when()
                .get("photos?albumId={id}", 5)
                .then()
                .statusCode(200)
                .body("albumId", everyItem(equalTo(5)))
                .body("id", everyItem(notNullValue()))
                .body("title", everyItem(notNullValue()))
                .body("url", everyItem(notNullValue()))
                .body("thumbnailUrl", everyItem(notNullValue()));
    }
}
