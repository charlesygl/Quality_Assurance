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
import static org.junit.Assert.assertTrue;
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
public class ShoppingCartTests {
    
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    
    @Given("^Open page for all product$")
    public void Open_page_for_all_product() throws Throwable {
        System.setProperty("webdriver.gecko.driver", "libs\\geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "http://store.demoqa.com";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl + "/");
        driver.findElement(By.linkText("All Product")).click();
        driver.findElement(By.linkText("iPhone 5")).click();
    }

    @When("^I click on Add To Cart and Go to Checkout for an item$")
    public void I_click_on_Add_To_Cart_and_Go_to_Checkout_for_an_item() throws Throwable {
        Thread.sleep(10000);
        driver.findElement(By.name("Buy")).click();
        driver.findElement(By.linkText("Go to Checkout")).click();
    }

    @Then("^This item should be listed in shopping cart$")
    public void This_item_should_be_listed_in_shopping_cart() throws Throwable {
        //assertEquals("1", driver.findElement(By.cssSelector("em.count")).getText());
        assertTrue(isElementPresent(By.linkText("iPhone 5")));
    }

    @Given("^Shopping cart is open and present$")
    public void Shopping_cart_is_open_and_present() throws Throwable {
        baseUrl = "http://store.demoqa.com";
        driver.get(baseUrl + "/");
        driver.get(baseUrl + "/products-page/checkout/");
    }

    @When("^I click on Remove for an item$")
    public void I_click_on_Remove_for_an_item() throws Throwable {
        driver.findElement(By.cssSelector("a.cart_icon")).click();
        driver.findElement(By.cssSelector("form.adjustform.remove > input[name=\"submit\"]")).click();
    }

    @Then("^This item shoule be removed from shopping cart$")
    public void This_item_shoule_be_removed_from_shopping_cart() throws Throwable {
        driver.findElement(By.cssSelector("a.cart_icon")).click();
        try {
            assertEquals("Oops, there is nothing in your cart.", driver.findElement(By.cssSelector("div.entry-content")).getText());
            } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }
    
    @Given("^Shopping cart is open with at least one item$")
    public void Shopping_cart_is_open_with_at_least_one_item() throws Throwable {
        baseUrl = "http://store.demoqa.com";
        driver.get(baseUrl + "/");
        driver.findElement(By.cssSelector("img[alt=\"home\"]")).click();
        driver.findElement(By.linkText("All Product")).click();
        driver.findElement(By.linkText("iPhone 5")).click();
        Thread.sleep(10000);
        driver.findElement(By.name("Buy")).click();
        Thread.sleep(10000);
        driver.findElement(By.linkText("Go to Checkout")).click();
    }

    @When("^I update the quantity of an item with valid numbers$")
    public void I_update_the_quantity_of_an_item_with_valid_numbers() throws Throwable {
        Thread.sleep(10000);
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("12");
        driver.findElement(By.name("submit")).click();
    }

    @Then("^The quantity of this item should be updated$")
    public void The_quantity_of_this_item_should_be_updated() throws Throwable {
        try {
            assertEquals("12", driver.findElement(By.name("quantity")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    @When("^I update the quantity of an item with negtive numbers$")
    public void I_update_the_quantity_of_an_item_with_negtive_numbers() throws Throwable {
        Thread.sleep(10000);
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("-12");
        driver.findElement(By.name("submit")).click();
    }

    @Then("^The quantity of this item should not be negtive$")
    public void The_quantity_of_this_item_should_not_be_negtive() throws Throwable {
        Thread.sleep(10000);
        try {
            assertEquals("Oops, there is nothing in your cart.", driver.findElement(By.cssSelector("div.entry-content")).getText());
            } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    @When("^I update the quantity of an item$")
    public void I_update_the_quantity_of_an_item() throws Throwable {
        Thread.sleep(10000);
        try {
            assertEquals("$12.00", driver.findElement(By.cssSelector("span.pricedisplay > span.pricedisplay")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("12");
        driver.findElement(By.name("submit")).click();
    }

    @Then("^The total price should change$")
    public void The_total_price_should_change() throws Throwable {
        Thread.sleep(10000);
        try {
            assertEquals("$144.00", driver.findElement(By.cssSelector("span.pricedisplay > span.pricedisplay")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        quitDriver();
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
  
    private void quitDriver() {
        driver.close();
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
