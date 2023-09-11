package posttests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.startsWith;

public class PostPatchTest extends BaseTest {

  @Test
  public void postPatchTest() {
    given()
      .spec(spec)
      .body("{\n" +
        "    \"title\": \"RK molestiae enim\"\n" +
        "}")
      .when()
      .patch("posts/1")
      .then()
      .statusCode(200)
      .body("title", startsWith("RK"));
  }
}
