package pivotal.automation;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;

public class POSTProjectTests {

    String projectID;

    @Test
    public void createProjectTest() {
        String endpoint = "https://www.pivotaltracker.com/services/v5/projects";

        String body = "{\"name\":\"Executioner\"}";
        Response response = RequestManager.sendPostRequest(endpoint, body);

        // Save ID for deletion
        projectID = response.jsonPath().getString("id");

        // Status code assertion
        int actualStatusCode = response.statusCode();
        int expectedStatusCode = 200;
        Assert.assertEquals(actualStatusCode, expectedStatusCode);

        // JSON Schema validation
        JsonSchemaValidator.validateJsonSchema("POSTProjectResponseSchema.json", response);

        // Response body values assertions
        String expectedProjectName = "Executioner";
        String actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, expectedProjectName);

        String expectedProjectKind = "project";
        String actualProjectKind = response.jsonPath().getString("kind");
        Assert.assertEquals(actualProjectKind, expectedProjectKind);
    }

    @AfterMethod
    public void deleteProjectsPostCondition() {
        if (projectID != null) {
            String endpoint = "https://www.pivotaltracker.com/services/v5/projects/" + projectID;
            RequestManager.sendDeleteRequest(endpoint);
        }
    }
}
