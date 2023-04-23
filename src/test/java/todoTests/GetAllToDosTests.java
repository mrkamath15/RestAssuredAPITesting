package todoTests;

import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.ToDo;
import utils.Constants;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetAllToDosTests extends BaseTest {

    @Test
    public void getAllTodosTest() {
        given()
                .spec(spec)
                .when()
                .get("todos")
                .then()
                .statusCode(200)
                .body("$", hasSize(greaterThan(1)))
                .body("$.size()", equalTo(200))
                .body("userId", hasItems(notNullValue()))
                .body("id", hasItems(notNullValue()))
                .body("title", hasItems(notNullValue()))
                .body("completed", hasItems(notNullValue()));
    }

    @Test
    public void getAllToDosUsingPojo() {
        ToDo[] todos = given()
                .spec(spec)
                .when()
                .get("todos")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(ToDo[].class);

        for (ToDo eachTodo : todos) {
            Assert.assertNotNull(eachTodo.getUserId());
            Assert.assertNotNull(eachTodo.getId());
            Assert.assertNotNull(eachTodo.getTitle());
            Assert.assertNotNull(eachTodo.isCompleted());
        }
    }

    @Test
    public void testSingleToDoValues() {
        given()
                .spec(spec)
                .when()
                .get("todos")
                .then()
                .statusCode(200)
                .body("userId[0]", equalTo(1))
                .body("id[0]", equalTo(1))
                .body("title[0]", startsWith("delectus"))
                .body("completed[0]", equalTo(false));
    }

    @Test
    public void getAllTodosResponseTimeTest() {
        given()
                .spec(spec)
                .when()
                .get("todos")
                .then()
                .statusCode(200)
                .time(lessThan(5000L));
    }

    @Test
    public void getAllTodosSchemaValidationTest() {
        given()
                .spec(spec)
                .when()
                .get("todos")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(Constants.GET_ALL_TODOS_SCHEMA_PATH)));
    }
}
