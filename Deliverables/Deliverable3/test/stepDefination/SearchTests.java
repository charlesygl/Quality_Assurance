/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stepDefination;

import static Testing.Deliverable_three.driver;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Gaole
 */
public class SearchTests {
    
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    
    @Given("^Open firefox and open webpage$")
    public void Open_firefox_and_open_webpage() throws Throwable {
        System.setProperty("webdriver.gecko.driver", "libs\\geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "http://store.demoqa.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl + "/");
    }

    @When("^I enter iPod in the search bar$")
    public void I_enter_iPod_in_the_search_bar() throws Throwable {
        driver.findElement(By.name("s")).clear();
        driver.findElement(By.name("s")).sendKeys("iPod");
        driver.findElement(By.name("s")).sendKeys(Keys.ENTER);
        Thread.sleep(10000);
    }

    @Then("^iPod should be listed in the search result$")
    public void iPod_should_be_listed_in_the_search_result() throws Throwable {
        try {
            assertEquals("Apple iPod touch Large", driver.findElement(By.linkText("Apple iPod touch Large")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        quitDriver();
    }

    @When("^I enter chairs in the search bar$")
    public void I_enter_chairs_in_the_search_bar() throws Throwable {
        driver.findElement(By.name("s")).clear();
        driver.findElement(By.name("s")).sendKeys("chairs");
        driver.findElement(By.name("s")).sendKeys(Keys.ENTER);
        Thread.sleep(10000);
    }

    @Then("^Search result should show nothing$")
    public void Search_result_should_show_nothing() throws Throwable {
        try {
            assertEquals("Sorry, but nothing matched your search criteria. Please try again with some different keywords.", driver.findElement(By.cssSelector("p")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        quitDriver();
    }
    
    private void quitDriver() {
        driver.close();
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
    
}
