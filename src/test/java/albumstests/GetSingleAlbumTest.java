package albumstests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetSingleAlbumTest extends BaseTest {

  @Test
  public void getSingleAlbumTest() {
    given()
      .spec(spec)
      .when()
      .get("albums/{id}", 50)
      .then()
      .statusCode(200)
      .body("userId", notNullValue())
      .body("id", equalTo(50))
      .body("title", notNullValue());
  }

}
