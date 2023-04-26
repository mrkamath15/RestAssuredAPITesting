package todoTests;

import base.BaseTest;
import org.testng.annotations.Test;
import pojos.Post;
import pojos.ToDo;
import utils.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateToDoTest extends BaseTest {

    @Test
    public void createToDoUsingStringBody() {
        given()
                .spec(spec)
                .body("{\n" +
                        "    \"userId\": 5,\n" +
                        "    \"title\": \"RK Title\",\n" +
                        "    \"completed\": true\n" +
                        "}")
                .when()
                .post("todos")
                .then()
                .statusCode(201)
                .body("userId", greaterThanOrEqualTo(1))
                .body("title", notNullValue())
                .body("completed", equalTo(true))
                .body("id", greaterThanOrEqualTo(1));
    }

    @Test
    public void createToDoUsingObjectBody() {
        ToDo createToDo = new ToDo();
        createToDo.setUserId(5);
        createToDo.setTitle("RK1");
        createToDo.setCompleted(true);

        given()
                .spec(spec)
                .body(createToDo)
                .when()
                .post("todos")
                .then()
                .statusCode(201)
                .body("userId", greaterThanOrEqualTo(1))
                .body("title", notNullValue())
                .body("completed", equalTo(true))
                .body("id", greaterThanOrEqualTo(1));
    }

    @Test
    public void createToDoUsingExternalFileBody() throws FileNotFoundException {
        given()
                .spec(spec)
                .body(new FileInputStream(new File(Constants.CREATE_TODO_JSON_PATH)))
                .when()
                .post("todos")
                .then()
                .statusCode(201)
                .body("userId", greaterThanOrEqualTo(1))
                .body("title", notNullValue())
                .body("completed", equalTo(true))
                .body("id", greaterThanOrEqualTo(1));
    }
}