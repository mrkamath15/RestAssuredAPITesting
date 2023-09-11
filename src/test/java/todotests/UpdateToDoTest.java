package todotests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class UpdateToDoTest extends BaseTest {

  @Test
  public void updateToDoTest() {
    given()
      .spec(spec)
      .body("{\n" +
        "    \"userId\": 1,\n" +
        "    \"title\": \"RK delectus aut autem\",\n" +
        "    \"completed\": true\n" +
        "}")
      .when()
      .put("todos/1")
      .then()
      .statusCode(200)
      .body("id", equalTo(1))
      .body("userId", equalTo(1))
      .body("title", startsWith("RK"))
      .body("completed", equalTo(true));
  }
}
