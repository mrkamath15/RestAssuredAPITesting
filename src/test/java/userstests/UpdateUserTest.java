package userstests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class UpdateUserTest extends BaseTest {

  @Test
  public void updateUserTest() {
    given()
      .spec(spec)
      .body("{\n" +
        "    \"name\": \"RK Leanne Graham\",\n" +
        "    \"username\": \"RKBret\",\n" +
        "    \"email\": \"RKSincere@april.biz\"\n" +
        "}")
      .when()
      .put("users/1")
      .then()
      .statusCode(200)
      .body("id", equalTo(1))
      .body("name", startsWith("RK"))
      .body("username", startsWith("RK"))
      .body("email", startsWith("RK"));
  }
}
