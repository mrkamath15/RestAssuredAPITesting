package albumsTests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UpdateAlbumTest extends BaseTest {

    @Test
    public void updateAlbumTest() {
        given()
                .spec(spec)
                .body("{\n" +
                        "    \"userId\": 1,\n" +
                        "    \"title\": \"RK molestiae enim\"\n" +
                        "}")
                .when()
                .put("albums/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .body("title", startsWith("RK"));
    }
}
