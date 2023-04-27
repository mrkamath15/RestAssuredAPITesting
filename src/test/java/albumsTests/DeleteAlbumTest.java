package albumsTests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteAlbumTest extends BaseTest {

    @Test
    public void deleteAlbumTest() {
        given()
                .spec(spec)
                .when()
                .delete("albums/1")
                .then().statusCode(200)
                .body("isEmpty()", is(true));
    }
}
