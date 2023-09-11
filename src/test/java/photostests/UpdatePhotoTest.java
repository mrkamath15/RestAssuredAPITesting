package photostests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class UpdatePhotoTest extends BaseTest {

  @Test
  public void updatePhotoTest() {
    given()
      .spec(spec)
      .body("{\n" +
        "    \"albumId\": 1,\n" +
        "    \"title\": \"RK beatae ad facilis cum similique qui sunt\",\n" +
        "    \"url\": \"https://via.placeholder.com/600/RK\",\n" +
        "    \"thumbnailUrl\": \"https://via.placeholder.com/150/RK\"\n" +
        "}")
      .when()
      .put("photos/1")
      .then()
      .statusCode(200)
      .body("id", equalTo(1))
      .body("albumId", equalTo(1))
      .body("title", startsWith("RK"))
      .body("url", endsWith("RK"))
      .body("thumbnailUrl", endsWith("RK"));
  }
}
