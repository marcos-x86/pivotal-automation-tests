package pivotal.automation;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class PUTProjectTests {

    String endpoint = "https://www.pivotaltracker.com/services/v5/projects";
    String projectName = "Automation Project";
    String projectID;

    @BeforeMethod
    public void createProject() {
        Response response = RestAssured.given()
                .header("X-TrackerToken", "")
                .header("Content-Type", "application/json")
                .body("{\"name\":\"" + projectName + "\"}")
                .when()
                .post(endpoint);

        projectID = response.jsonPath().getString("id");
    }

    @Test
    public void modifyProject() {
        String putEndpoint = endpoint + "/" + projectID;

        Response response = RestAssured.given()
                .header("X-TrackerToken", "")
                .header("Content-Type", "application/json")
                .body("{\"name\":\"" + projectName + " modified" + "\"}")
                .when()
                .put(putEndpoint);

        // Status code assertion
        int actualStatusCode = response.statusCode();
        int expectedStatusCode = 200;
        Assert.assertEquals(actualStatusCode, expectedStatusCode);

        // JSON Schema validation
        File schemaContent = new File("src/test/resources/schemas/PUTProjectResponseSchema.json");
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schemaContent));

        // Response body values assertions
        String expectedProjectName = projectName + " modified";
        String actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, expectedProjectName);

        String expectedInvalidProjectName = projectName;
        Assert.assertNotEquals(actualProjectName, expectedInvalidProjectName);
    }

    @AfterMethod
    public void deleteProject() {
        if (projectID != null) {
            String endpoint = "https://www.pivotaltracker.com/services/v5/projects/" + projectID;

            RestAssured.given()
                    .header("X-TrackerToken", "")
                    .header("Content-Type", "application/json")
                    .when()
                    .delete(endpoint);
        }
    }
}
