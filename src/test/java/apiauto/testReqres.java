package apiauto;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;
import apiauto.utils.ConfigReader;

import java.io.File;

public class testReqres {

    @Test
    //positive scenario
    public void testGetUsers() {
        RestAssured.baseURI = ConfigReader.get("base.url");

        File jsonSchema = new File("src/test/resources/jsonSchema/getListUsersSchema.json");

        RestAssured
                .given()
                .when()
                .get("/users?page=2")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("page", Matchers.equalTo(2))
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }

    @Test
    //negative scenario
    public void testGetUsersInvalidPage() {
        RestAssured.baseURI = ConfigReader.get("base.url");

        File jsonSchema = new File("src/test/resources/jsonSchema/getListUsersSchema.json");

        RestAssured
                .given()
                .when()
                .get("/users?page=2222")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("page", Matchers.equalTo(2))
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }

    @Test
    //boundaries test scenario
    public void testGetUsersPageZero() {
        RestAssured.baseURI = ConfigReader.get("base.url");

        File jsonSchema = new File("src/test/resources/jsonSchema/getListUsersSchema.json");

        RestAssured
                .given()
                .when()
                .get("/users?page=0")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("page", Matchers.equalTo(2))
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }
//
//    @Test
//    public void testPostCreateUsers() {
//        RestAssured.baseURI = ConfigReader.get("base.url");
//
//        String valueName = "Ardella";
//        String valueJob = "QA";
//
//        JSONObject objJson = new JSONObject();
//        objJson.put("name", valueName);
//        objJson.put("job", valueJob);
//
//        RestAssured.given()
//                .header("Content-Type", "application/json")
//                .header("Accept", "application/json")
//                .body(objJson.toString())
//                .when()
//                .post("/users")
//                .then().log().all()
//                .assertThat().statusCode(201)
//                .assertThat().body("name", Matchers.equalTo(valueName))
//                .assertThat().body("job", Matchers.equalTo(valueJob));
//    }
}