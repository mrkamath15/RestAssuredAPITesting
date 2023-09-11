package userstests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DeleteUsersTest extends BaseTest {

  @Test
  public void deleteUsersTest() {
    given()
      .spec(spec)
      .when()
      .delete("users/1")
      .then().statusCode(200)
      .body("isEmpty()", is(true));
  }
}
