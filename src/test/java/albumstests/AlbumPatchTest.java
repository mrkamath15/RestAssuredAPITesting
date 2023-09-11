package albumstests;

import base.BaseTest;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AlbumPatchTest extends BaseTest {

  @Test
  public void albumPatchTest() {
    given()
      .spec(spec)
      .body("{\n" +
        "    \"title\": \"RK molestiae enim\"\n" +
        "}")
      .when()
      .patch("albums/1")
      .then()
      .statusCode(200)
      .body("title", Matchers.startsWith("RK"));
  }
}
