package pivotal.automation;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class GETProjectTests {

    String endpoint = "https://www.pivotaltracker.com/services/v5/projects";
    String projectName = "Automation Project";
    String projectID;

    @BeforeMethod
    public void createProject() {
        String body = "{\"name\":\"" + projectName + "\"}";
        Response response = RequestManager.sendPostRequest(endpoint, body);
        projectID = response.jsonPath().getString("id");
    }

    @Test
    public void getSingleProject() {
        String getProjectEndpoint = endpoint + "/" + projectID;
        Response response = RequestManager.sendGetRequest(getProjectEndpoint);

        // Status code assertion
        int actualStatusCode = response.statusCode();
        int expectedStatusCode = 200;
        Assert.assertEquals(actualStatusCode, expectedStatusCode);

        // JSON Schema validation
        JsonSchemaValidator.validateJsonSchema("GETProjectResponseSchema.json", response);

        // Response body values assertions
        String expectedProjectName = projectName;
        String actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, expectedProjectName);

        String expectedProjectKind = "project";
        String actualProjectKind = response.jsonPath().getString("kind");
        Assert.assertEquals(actualProjectKind, expectedProjectKind);
    }

    @AfterMethod
    public void deleteProject() {
        if (projectID != null) {
            String endpoint = "https://www.pivotaltracker.com/services/v5/projects/" + projectID;
            RequestManager.sendDeleteRequest(endpoint);
        }
    }
}
