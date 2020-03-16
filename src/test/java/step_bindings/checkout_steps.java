package step_bindings;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class checkout_steps {
    WebDriver driver;

    @Given("that i am on the shopping website")
    public void that_i_am_on_the_shopping_website() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.edgewordstraining.co.uk/demo-site/");
        driver.manage().window().maximize();
    }

    @When("i add an item to the basket")
    public void i_add_an_item_to_the_basket() {
        driver.findElement(By.cssSelector("#masthead [type='search']")).click();
        driver.findElement(By.cssSelector("#masthead [type='search']")).sendKeys("cap");
        driver.findElement(By.cssSelector("#masthead [type='search']")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector(".entry-summary button")).click();
    }

    @Then("i can view the item in my basket")
    public void i_can_view_the_item_in_my_basket() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.linkText("Cart")).click();
        assertTrue(driver.findElement(By.linkText("Cap")).isDisplayed());
        //Thread.sleep(2000);
        WebElement deleteCap;
        deleteCap = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.remove")));
        deleteCap.click();
        WebElement returnToShopLink;
        returnToShopLink= wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Return to shop")));
        returnToShopLink.click();
        driver.quit();
    }

}

