package pivotal.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PageTitleTest {

    @Test
    public void pageTitleTest() {
        WebDriverManager.chromedriver().setup();

        String urlLogin = "https://www.pivotaltracker.com/signin";
        WebDriver driver = new ChromeDriver();

        driver.get(urlLogin);

        String expectedTitle = "Pivotal Tracker - Sign in";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

        driver.quit();
    }
}
