package photostests;

import base.BaseTest;
import org.testng.annotations.Test;
import pojos.Photo;
import utils.Constants;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;

public class CreatePhotoTest extends BaseTest {

  @Test
  public void createPhotoUsingStringBody() {
    given().spec(spec)
      .body("{\n" +
        "    \"albumId\": 1,\n" +
        "    \"title\": \"RK1\",\n" +
        "    \"url\": \"https://via.placeholder.com/600/92c959\",\n" +
        "    \"thumbnailUrl\": \"https://via.placeholder.com/150/92c959\"\n" +
        "}")
      .when()
      .post("photos")
      .then()
      .statusCode(201)
      .body("albumId", greaterThanOrEqualTo(1))
      .body("title", notNullValue())
      .body("url", startsWith("https://"))
      .body("thumbnailUrl", startsWith("https://"))
      .body("id", greaterThanOrEqualTo(1));
  }

  @Test
  public void createPhotoUsingObjectBody() {
    Photo photo = new Photo();
    photo.setAlbumId(99);
    photo.setTitle("RK");
    photo.setUrl("https://rk.url");
    photo.setThumbnailUrl("https://rk.thumbnail");

    given()
      .spec(spec)
      .body(photo)
      .when()
      .post("photos")
      .then()
      .statusCode(201)
      .body("albumId", greaterThanOrEqualTo(1))
      .body("title", notNullValue())
      .body("url", startsWith("https://"))
      .body("thumbnailUrl", startsWith("https://"))
      .body("id", greaterThanOrEqualTo(1));
  }

  @Test
  public void createPhotoUsingExternalFileBody() {
    given()
      .spec(spec)
      .body(new File(Constants.CREATE_PHOTO_JSON_PATH))
      .when()
      .post("photos")
      .then()
      .statusCode(201)
      .body("albumId", greaterThanOrEqualTo(1))
      .body("title", notNullValue())
      .body("url", startsWith("https://"))
      .body("thumbnailUrl", startsWith("https://"))
      .body("id", greaterThanOrEqualTo(1));
  }
}
