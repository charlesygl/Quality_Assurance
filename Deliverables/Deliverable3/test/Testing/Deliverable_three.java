package Testing;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@Cucumber.Options(features={"Stories/Login.feature", "Stories/ShoppingCart.feature", "Stories/Search.feature"}, glue={"stepDefination"})
public class Deliverable_three {
  public static WebDriver driver;
}
