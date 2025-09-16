package apiauto;

import apiauto.requests.userRequests;
import apiauto.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class testReqres {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.get("base.url");
    }

    @Test
    //positive scenario
    public void testGetUsers() {
        File jsonSchema = new File("src/test/resources/jsonSchema/getListUsersSchema.json");

        Response response = userRequests.getUsers(2);

        response.then().log().all()
                .statusCode(200)
                .body("page", Matchers.equalTo(2))
                .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }

    @Test
    //negative scenario
    public void testGetUsersInvalidPage() {
        File jsonSchema = new File("src/test/resources/jsonSchema/getListUsersSchema.json");

        Response response = userRequests.getUsers(2222);

        response.then().log().all()
                .statusCode(200)
                .body("page", Matchers.equalTo(2222))
                .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }

    @Test
    //boundary scenario
    public void testGetUsersPageZero() {
        File jsonSchema = new File("src/test/resources/jsonSchema/getListUsersSchema.json");

        Response response = userRequests.getUsers(0);

        response.then().log().all()
                .statusCode(200)
                .body("page", Matchers.equalTo(1))
                .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }
}
