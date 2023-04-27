package photosTests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DeletePhotosTest extends BaseTest {

    @Test
    public void deletePhotoTest() {
        given()
                .spec(spec)
                .when()
                .delete("photos/1")
                .then().statusCode(200)
                .body("isEmpty()", is(true));
    }
}
