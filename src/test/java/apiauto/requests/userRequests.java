package apiauto.requests;

import io.restassured.RestAssured;
import apiauto.utils.ConfigReader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class userRequests {

    // get list users by page
    public static Response getUsers(int page) {
        return given()
                .header("x-api-key", ConfigReader.get("api.key"))
                .queryParam("page", page)
                .when()
                .get("/users");
    }
}
//    // Get single user
//    public static Response getUserById(int userId) {
//        return given()
//                .when()
//                .get("/users/" + userId);
//    }
//}
//
//    // Create user
//    public static Response createUser(String name, String job) {
//        String body = "{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }";
//        return given()
//                .header("Content-Type", "application/json")
//                .body(body)
//                .when()
//                .post("/users");
//    }
//
//    // Update user
//    public static Response updateUser(int userId, String name, String job) {
//        String body = "{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }";
//        return given()
//                .header("Content-Type", "application/json")
//                .body(body)
//                .when()
//                .put("/users/" + userId);
//    }
//
//    // Delete user
//    public static Response deleteUser(int userId) {
//        return given()
//                .when()
//                .delete("/users/" + userId);
//    }
//}
