package pivotal.automation;

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
        String body = "{\"name\":\"" + projectName + "\"}";
        Response response = RequestManager.sendPostRequest(endpoint, body);
        projectID = response.jsonPath().getString("id");
    }

    @Test
    public void modifyProject() {
        String putEndpoint = endpoint + "/" + projectID;
        String body = "{\"name\":\"" + projectName + " modified" + "\"}";
        Response response = RequestManager.sendPutRequest(putEndpoint, body);

        // Status code assertion
        int actualStatusCode = response.statusCode();
        int expectedStatusCode = 200;
        Assert.assertEquals(actualStatusCode, expectedStatusCode);

        // JSON Schema validation
        JsonSchemaValidator.validateJsonSchema("PUTProjectResponseSchema.json", response);

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
            RequestManager.sendDeleteRequest(endpoint);
        }
    }
}
