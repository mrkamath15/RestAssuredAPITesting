package usersTests;

import base.BaseTest;
import org.testng.annotations.Test;
import pojos.User;
import utils.Constants;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.startsWith;

public class CreateUserTest extends BaseTest {

    @Test
    public void createUserUsingStringBody() {
        given().spec(spec)
                .body("{\n" +
                        "    \"name\": \"RK Graham\",\n" +
                        "    \"username\": \"mrk\",\n" +
                        "    \"email\": \"RK@april.biz\"\n" +
                        "}")
                .when()
                .post("users")
                .then()
                .statusCode(201)
                .body("name", startsWith("RK"));
    }

    @Test
    public void createUserUsingObjectBody() {
        User user = new User();
        user.setName("RK");
        user.setEmail("RK@RK.com");
        user.setEmail("RK body");

        given()
                .spec(spec)
                .body(user)
                .when()
                .post("users")
                .then()
                .statusCode(201)
                .body("name", startsWith("RK"));
    }

    @Test
    public void createUserUsingExternalFileBody() {
        given()
                .spec(spec)
                .body(new File(Constants.CREATE_USER_JSON_PATH))
                .when()
                .post("users")
                .then()
                .statusCode(201)
                .body("name", startsWith("RK"));
    }
}
