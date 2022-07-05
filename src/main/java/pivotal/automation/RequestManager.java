package pivotal.automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RequestManager {

    public static String TOKEN = "";

    public static Response sendGetRequest(String endpoint) {
        Response response = RestAssured.given()
                .header("X-TrackerToken", TOKEN)
                .when()
                .get(endpoint);
        return response;
    }

    public static Response sendDeleteRequest(String endpoint) {
        Response response = RestAssured.given()
                .header("X-TrackerToken", TOKEN)
                .header("Content-Type", "application/json")
                .when()
                .delete(endpoint);
        return response;
    }

    public static Response sendPostRequest(String endpoint, String body) {
        Response response = RestAssured.given()
                .header("X-TrackerToken", TOKEN)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(endpoint);
        return response;
    }

    public static Response sendPutRequest(String endpoint, String body) {
        Response response = RestAssured.given()
                .header("X-TrackerToken", TOKEN)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put(endpoint);
        return response;
    }
}
