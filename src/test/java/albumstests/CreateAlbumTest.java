package albumstests;

import base.BaseTest;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import pojos.Album;
import utils.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;

public class CreateAlbumTest extends BaseTest {

  @Test
  public void createAlbumUsingStringBody() {
    given()
      .spec(spec)
      .body("{\n" +
        "    \"userId\": 10,\n" +
        "    \"title\": \"RK\"\n" +
        "}")
      .when()
      .post("albums")
      .then()
      .statusCode(201)
      .body("userId", Matchers.greaterThanOrEqualTo(1))
      .body("title", Matchers.notNullValue())
      .body("id", Matchers.greaterThanOrEqualTo(1));
  }

  @Test
  public void createAlbumUsingObjectBody() {
    Album createAlbum = new Album();
    createAlbum.setUserID(5);
    createAlbum.setTitle("RK1");

    given()
      .spec(spec)
      .body(createAlbum)
      .when()
      .post("albums")
      .then()
      .statusCode(201)
      .body("userId", Matchers.greaterThanOrEqualTo(1))
      .body("title", Matchers.notNullValue())
      .body("id", Matchers.greaterThanOrEqualTo(1));
  }

  @Test
  public void createAlbumUsingExternalFileBody() throws FileNotFoundException {
    given()
      .spec(spec)
      .body(new FileInputStream(new File(Constants.CREATE_ALBUM_JSON_PATH)))
      .when()
      .post("albums")
      .then()
      .statusCode(201)
      .body("userId", Matchers.greaterThanOrEqualTo(1))
      .body("title", Matchers.notNullValue())
      .body("id", Matchers.greaterThanOrEqualTo(1));
  }
}
