package userstests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.startsWith;

public class UserPatchTest extends BaseTest {

  @Test
  public void userPatchTest() {
    given()
      .spec(spec)
      .body("{\n" +
        "    \"name\": \"RKsingh\"\n" +
        "}")
      .when()
      .patch("users/1")
      .then()
      .statusCode(200)
      .body("name", startsWith("RK"));
  }
}
