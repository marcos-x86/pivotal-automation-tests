package pivotal.automation.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RequestSteps {
    @When("the user creates the following request with the following headers")
    public void theUserCreatesTheFollowingRequestWithTheFollowingHeaders() {
        System.out.println("I'm step 1");
    }

    @And("the user sends a POST request to {string}")
    public void theUserSendsAPOSTRequestTo(String arg0) {
        System.out.println("I'm step 2");
    }

    @Then("the user verifies that the response status code is {int}")
    public void theUserVerifiesThatTheResponseStatusCodeIs(int arg0) {
        System.out.println("I'm step 3");
    }

    @And("the user verifies that the response matches the JSON Schema {string}")
    public void theUserVerifiesThatTheResponseMatchesTheJSONSchema(String arg0) {
        System.out.println("I'm step 4");
    }
}
