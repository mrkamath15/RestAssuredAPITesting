package usersTests;

import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.User;
import utils.Constants;
import java.io.File;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetAllUsersTests extends BaseTest {

    @Test
    public void getAllUsersTest() {
        given()
                .spec(spec)
                .when()
                .get("users")
                .then()
                .statusCode(200)
                .body("$", hasSize(greaterThan(1)))
                .body("$.size()", equalTo(10))
                .body("id", notNullValue())
                .body("name", notNullValue())
                .body("username", notNullValue())
                .body("email", notNullValue())
                .body("address.street", notNullValue())
                .body("address.suite", notNullValue())
                .body("address.city", notNullValue())
                .body("address.zipcode", notNullValue())
                .body("address.geo.lat", notNullValue())
                .body("address.geo.lng", notNullValue())
                .body("phone", notNullValue())
                .body("website", notNullValue())
                .body("company.name", notNullValue())
                .body("company.catchPhrase", notNullValue())
                .body("company.bs", notNullValue());
    }

    @Test
    public void getAllUsersUsingPojo() {
        User[] users = given()
                .spec(spec)
                .when()
                .get("users")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(User[].class);

        for (User eachUser : users) {
            Assert.assertTrue(eachUser.getId() >= 1);
            Assert.assertNotNull(eachUser.getName());
            Assert.assertNotNull(eachUser.getUsername());
            Assert.assertNotNull(eachUser.getEmail());
            Assert.assertNotNull(eachUser.getAddress().getStreet());
            Assert.assertNotNull(eachUser.getAddress().getSuite());
            Assert.assertNotNull(eachUser.getAddress().getCity());
            Assert.assertNotNull(eachUser.getAddress().getZipcode());
            Assert.assertNotNull(eachUser.getAddress().getGeo().getLat());
            Assert.assertNotNull(eachUser.getAddress().getGeo().getLng());
            Assert.assertNotNull(eachUser.getPhone());
            Assert.assertNotNull(eachUser.getWebsite());
            Assert.assertNotNull(eachUser.getCompany().getName());
            Assert.assertNotNull(eachUser.getCompany().getCatchPhrase());
            Assert.assertNotNull(eachUser.getCompany().getBs());
        }
    }

    @Test
    public void testSingleUserValues() {
        given()
                .spec(spec)
                .when()
                .get("users")
                .then()
                .statusCode(200)
                .body("id[0]", equalTo(1))
                .body("name[0]", startsWith("Leanne"))
                .body("username[0]", equalTo("Bret"))
                .body("email[0]", endsWith("@april.biz"))
                .body("address[0].street", startsWith("Kulas"))
                .body("address[0].suite", endsWith("556"))
                .body("address[0].city", equalTo("Gwenborough"))
                .body("address[0].zipcode", startsWith("92998"))
                .body("address[0].geo.lat", equalTo("-37.3159"))
                .body("address[0].geo.lng", equalTo("81.1496"))
                .body("phone[0]", endsWith("x56442"))
                .body("website[0]", endsWith("rd.org"))
                .body("company[0].name", startsWith("Romaguera"))
                .body("company[0].catchPhrase", startsWith("Multi-layered"))
                .body("company[0].bs", endsWith("e-markets"));
    }

    @Test
    public void getAllUsersResponseTimeTest() {
        given()
                .spec(spec)
                .when()
                .get("users")
                .then()
                .statusCode(200)
                .time(lessThan(5000L));
    }

    @Test
    public void getAllUsersSchemaValidationTest() {
        given()
                .spec(spec)
                .when()
                .get("users")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(Constants.GET_ALL_USERS_SCHEMA_PATH)));
    }

    @Test
    public void getUserByIdTest() {
        given()
                .spec(spec)
                .when()
                .get("users?id=5")
                .then()
                .statusCode(200)
                .body("$.size()", equalTo(1))
                .body("id[0]", equalTo(5))
                .body("name[0]", notNullValue())
                .body("username[0]", notNullValue())
                .body("email[0]", notNullValue())
                .body("address[0].street", notNullValue())
                .body("address[0].suite", notNullValue())
                .body("address[0].city", notNullValue())
                .body("address[0].zipcode", notNullValue())
                .body("address[0].geo.lat", notNullValue())
                .body("address[0].geo.lng", notNullValue())
                .body("phone[0]", notNullValue())
                .body("website[0]", notNullValue())
                .body("company[0].name", notNullValue())
                .body("company[0].catchPhrase", notNullValue())
                .body("company[0].bs", notNullValue());
    }

}
