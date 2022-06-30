package pivotal.automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RequestManager {

    public static Response sendGetRequest(String endpoint) {
        Response response = RestAssured.given()
                .header("X-TrackerToken", "")
                .when()
                .get(endpoint);
        return response;
    }
}
