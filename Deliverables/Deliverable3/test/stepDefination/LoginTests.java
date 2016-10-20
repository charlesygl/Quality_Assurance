/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stepDefination;

import static Testing.Deliverable_three.driver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Gaole
 */
public class LoginTests {
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    
    @Given("^Open firefox and start application$")
    public void Open_firefox_and_start_application() throws Throwable {
        System.setProperty("webdriver.gecko.driver", "libs\\geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "http://store.demoqa.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl + "/");
    }

    @When("^I enter valid username and valid password$")
    public void I_enter_valid_username_and_valid_password() throws Throwable {
        driver.findElement(By.linkText("AccountMy Account")).click();
        driver.findElement(By.id("log")).clear();
        driver.findElement(By.id("log")).sendKeys("charlesygl");
        driver.findElement(By.id("pwd")).clear();
        driver.findElement(By.id("pwd")).sendKeys("VPm34DxaEAfuzlux");
    }

    @Then("^User should be able to login successfully$")
    public void User_should_be_able_to_login_successfully() throws Throwable {
        driver.findElement(By.id("login")).click();
        driver.findElement(By.linkText("Log out")).click();
        Thread.sleep(10000);
    }
    
    @When("^I enter invalid username or password$")
    public void I_enter_invalid_username_or_password() throws Throwable {
        driver.findElement(By.linkText("AccountMy Account")).click();
        driver.findElement(By.id("log")).clear();
        driver.findElement(By.id("log")).sendKeys("charlesyg");
        driver.findElement(By.id("pwd")).clear();
        driver.findElement(By.id("pwd")).sendKeys("VPm34DxaEAfuzlux");
    }

    @Then("^User should not be able to login$")
    public void User_should_not_be_able_to_login() throws Throwable {
        driver.findElement(By.id("login")).click();
        assertEquals("ERROR", driver.findElement(By.cssSelector("strong")).getText());
        try {
            assertEquals("", driver.findElement(By.cssSelector("strong")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }
    
    private void quitDriver(){
        driver.close();
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
    
    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
