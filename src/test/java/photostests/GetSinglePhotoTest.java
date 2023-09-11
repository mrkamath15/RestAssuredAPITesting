package photostests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetSinglePhotoTest extends BaseTest {

  @Test
  public void getSinglePhotoTest() {
    given()
      .spec(spec)
      .when()
      .get("photos/{id}", 50)
      .then()
      .statusCode(200)
      .body("albumId", notNullValue())
      .body("id", equalTo(50))
      .body("title", notNullValue())
      .body("url", notNullValue())
      .body("thumbnailUrl", notNullValue());
  }

}
