package todotests;

import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.ToDo;
import utils.Constants;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;

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

  @Test
  public void getToDoByIdTest() {
    given()
      .spec(spec)
      .queryParam("id", 5)
      .when()
      .get("todos")
      .then()
      .statusCode(200)
      .body("userId[0]", notNullValue())
      .body("id[0]", equalTo(5))
      .body("title[0]", notNullValue())
      .body("completed[0]", notNullValue());
  }

  @Test
  public void getToDoByUserIdTest() {
    given()
      .spec(spec)
      .queryParam("userId", 5)
      .when()
      .get("todos")
      .then()
      .statusCode(200)
      .body("userId", everyItem(equalTo(5)))
      .body("id", everyItem(notNullValue()))
      .body("title", everyItem(notNullValue()))
      .body("completed", everyItem(notNullValue()));
  }


}
