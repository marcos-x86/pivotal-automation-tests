package pivotal.automation.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginSteps {

    WebDriver driver;

    @Given("the user goes to login page")
    public void theUserGoesToLoginPage() {
        WebDriverManager.chromedriver().setup();
        String urlLogin = "https://www.pivotaltracker.com/signin";
        driver = new ChromeDriver();
        driver.get(urlLogin);
    }

    @When("the user enters {string} in the username field")
    public void theUserEntersInTheUsernameField(String username) {
        WebElement usernameField = driver.findElement(By.cssSelector("#credentials_username"));
        usernameField.sendKeys(username);

        WebElement nextButton = driver.findElement(By.cssSelector("#login_type_check_form > input.app_signin_action_button"));
        nextButton.click();
    }

    @And("the user enters {string} in the password field")
    public void theUserEntersInThePasswordField(String password) {
        WebElement passwordField = driver.findElement(By.id("credentials_password"));
        passwordField.sendKeys(password);

        WebElement signInButton = driver.findElement(By.className("app_signin_action_button"));
        signInButton.click();
    }

    @Then("verfies that the following error message is displayed")
    public void verfiesThatTheFollowingErrorMessageIsDislayed(String expectedText) {
        WebElement errorMessage = driver.findElement(By.className("app_signin_error"));
        Assert.assertTrue(errorMessage.isDisplayed());
        String actualErrorText = errorMessage.getText();
        Assert.assertEquals(actualErrorText, expectedText);
    }
}
