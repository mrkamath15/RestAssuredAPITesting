package albumstests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DeleteAlbumTest extends BaseTest {

  @Test
  public void deleteAlbumTest() {
    given()
      .spec(spec)
      .when()
      .delete("albums/1")
      .then().statusCode(200)
      .body("isEmpty()", is(true));
  }
}
