package posttests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class UpdatePostTest extends BaseTest {

  @Test
  public void updatePostTest() {
    given()
      .spec(spec)
      .body("{\n" +
        "    \"userId\": 1,\n" +
        "    \"title\": \"RK aut facere\",\n" +
        "    \"body\": \"RK et suscipit\"\n" +
        "}")
      .when()
      .put("posts/1")
      .then()
      .statusCode(200)
      .body("id", equalTo(1))
      .body("userId", equalTo(1))
      .body("title", startsWith("RK"))
      .body("body", startsWith("RK"));
  }
}
