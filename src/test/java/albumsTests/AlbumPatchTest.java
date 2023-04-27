package albumsTests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AlbumPatchTest extends BaseTest {

    @Test
    public void albumPatchTest() {
        given()
                .spec(spec)
                .body("{\n" +
                        "    \"title\": \"RK molestiae enim\"\n" +
                        "}")
                .when()
                .patch("albums/1")
                .then()
                .statusCode(200)
                .body("title", startsWith("RK"));
    }
}
