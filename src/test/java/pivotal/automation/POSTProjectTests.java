package pivotal.automation;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;

public class POSTProjectTests {

    @Test
    public void createProjectTest() {
        String endpoint = "https://www.pivotaltracker.com/services/v5/projects";

        Response response = RestAssured.given()
                .header("X-TrackerToken", "")
                .header("Content-Type", "application/json")
                .body("{\"name\":\"Executioner\"}")
                .when()
                .post(endpoint);

        // Status code assertion
        int actualStatusCode = response.statusCode();
        int expectedStatusCode = 200;
        Assert.assertEquals(actualStatusCode, expectedStatusCode);

        // JSON Schema validation
        File schemaContent = new File("src/test/resources/schemas/POSTProjectResponseSchema.json");
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schemaContent));

        // Response body values assertions
        String expectedProjectName = "Executioner";
        String actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, expectedProjectName);

        String expectedProjectKind = "project";
        String actualProjectKind = response.jsonPath().getString("kind");
        Assert.assertEquals(actualProjectKind, expectedProjectKind);
    }

    @AfterTest
    public void deleteProjectsPostCondition() {

    }
}
