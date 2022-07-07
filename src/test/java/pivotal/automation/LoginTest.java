package pivotal.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    @Test
    public void loginTest() {
        WebDriverManager.chromedriver().setup();

        String urlLogin = "https://www.pivotaltracker.com/signin";
        WebDriver driver = new ChromeDriver();

        driver.get(urlLogin);

        WebElement usernameField = driver.findElement(By.id("credentials_username"));
        usernameField.sendKeys("marcos.x86@outlook.com");

        WebElement nextButton = driver.findElement(By.className("app_signin_action_button"));
        nextButton.click();

        WebElement passwordField = driver.findElement(By.id("credentials_password"));
        passwordField.sendKeys("");

        WebElement signInButton = driver.findElement(By.className("app_signin_action_button"));
        signInButton.click();

        WebElement createProjectButton = driver.findElement(By.id("create-project-button"));
        Assert.assertTrue(createProjectButton.isDisplayed());

        WebElement textLogo = driver.findElement(By.className("tc_header_text_logo"));
        Assert.assertTrue(textLogo.isDisplayed());

//        driver.quit();
    }

    @Test
    public void invalidLoginTest() {
        WebDriverManager.chromedriver().setup();

        String urlLogin = "https://www.pivotaltracker.com/signin";
        WebDriver driver = new ChromeDriver();

        driver.get(urlLogin);

        WebElement usernameField = driver.findElement(By.cssSelector("#credentials_username"));
        usernameField.sendKeys("marcos.x86@outlook.com");

        WebElement nextButton = driver.findElement(By.cssSelector("#login_type_check_form > input.app_signin_action_button"));
        nextButton.click();

        WebElement passwordField = driver.findElement(By.id("credentials_password"));
        passwordField.sendKeys("asdfasdfasdfasdf");

        WebElement signInButton = driver.findElement(By.className("app_signin_action_button"));
        signInButton.click();

        WebElement errorMessage = driver.findElement(By.className("app_signin_error"));
        Assert.assertTrue(errorMessage.isDisplayed());
        String expectedErrorText = "Invalid username/password";
        String actualErrorText = errorMessage.getText();
        Assert.assertEquals(actualErrorText, expectedErrorText);
    }
}
