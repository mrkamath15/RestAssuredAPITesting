package todoTests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DeleteTodoTest extends BaseTest {

    @Test
    public void deleteTodoTest() {
        given()
                .spec(spec)
                .when()
                .delete("todos/1")
                .then().statusCode(200)
                .body("isEmpty()", is(true));
    }
}
