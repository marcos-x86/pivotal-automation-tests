package pivotal.automation.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;
import pivotal.automation.JsonSchemaValidator;
import pivotal.automation.RequestManager;
import pivotal.automation.hooks.SharedData;

import java.util.Map;

public class RequestSteps {

    Response response;
    SharedData sharedData;

    public RequestSteps(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @And("the user sends a POST request to {string} with the body")
    public void sendPOSTRequest(String endpoint, String body) {
        response = RequestManager.sendPostRequest(endpoint, body);
        sharedData.saveId(response.jsonPath().getString("id"));
    }

    @Then("the user verifies that the response status code is {int}")
    public void checkStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.statusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }

    @And("the user verifies that the response matches the JSON Schema {string}")
    public void checkJSONSchema(String schemaName) {
        JsonSchemaValidator.validateJsonSchema(schemaName, response);
    }

    @And("the user verifies that the response contains the following data")
    public void checkData(Map<String, String> data) {
        for(Map.Entry<String, String> entry : data.entrySet()) {
            String actualData = response.jsonPath().getString(entry.getKey());
            String expectedData = entry.getValue();
            Assert.assertEquals(actualData, expectedData);
        }
    }
}
