package todotests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.startsWith;

public class TodoPatchTest extends BaseTest {

  @Test
  public void todoPatchTest() {
    given()
      .spec(spec)
      .body("{\n" +
        "    \"title\": \"RK molestiae enim\"\n" +
        "}")
      .when()
      .patch("todos/1")
      .then()
      .statusCode(200)
      .body("title", startsWith("RK"));
  }
}
