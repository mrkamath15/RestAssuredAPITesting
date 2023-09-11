package posttests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DeletePostTest extends BaseTest {

  @Test
  public void deletePostTest() {
    given()
      .spec(spec)
      .when()
      .delete("posts/1")
      .then().statusCode(200)
      .body("isEmpty()", is(true));
  }
}
