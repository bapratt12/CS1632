import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestPostCreation {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.reddit.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  //Given that you are logged into a reddit account, when you create a text post with a title, text, and subreddit, then the post should be successfully uploaded to that subreddit.

  @Test
  public void testScenario1() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Submit a new text post")).click();
    driver.findElement(By.name("title")).click();
    driver.findElement(By.name("title")).clear();
    driver.findElement(By.name("title")).sendKeys("Hello World");
    driver.findElement(By.name("text")).click();
    driver.findElement(By.name("text")).clear();
    driver.findElement(By.name("text")).sendKeys("Testing CS1632");
    driver.findElement(By.id("sr-autocomplete")).click();
    driver.findElement(By.id("sr-autocomplete")).clear();
    driver.findElement(By.id("sr-autocomplete")).sendKeys("Pitt");
    driver.findElement(By.name("submit")).click();
    driver.findElement(By.linkText("cs1632Pitt")).click();
    assertEquals("Hello World (self.Pitt)", driver.findElement(By.cssSelector("p.title")).getText());
  }

  //Given that you are logged into a reddit account, when you create a text post with a title but no text or subreddit, then the post should not be uploaded.

  @Test
  public void testScenario2() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Submit a new text post")).click();
    driver.findElement(By.name("title")).click();
    driver.findElement(By.name("title")).clear();
    driver.findElement(By.name("title")).sendKeys("Hello World");
    driver.findElement(By.name("submit")).click();
    driver.findElement(By.linkText("cs1632Pitt")).click();
    assertThat("Hello World (self.Pitt)", is(not(driver.findElement(By.cssSelector("p.parent")).getText())));
  }

  //Given that you are logged into a reddit account, when you create a text post with a title, text, but no subreddit, then the post should not be uploaded.

  @Test
  public void testScenario3() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Submit a new text post")).click();
    driver.findElement(By.name("title")).click();
    driver.findElement(By.name("title")).clear();
    driver.findElement(By.name("title")).sendKeys("Hello World");
    driver.findElement(By.name("text")).click();
    driver.findElement(By.name("text")).clear();
    driver.findElement(By.name("text")).sendKeys("My name is Tim");
    driver.findElement(By.name("submit")).click();
    driver.findElement(By.linkText("cs1632Pitt")).click();
    assertThat("Hello World (self.Pitt)", is(not(driver.findElement(By.cssSelector("p.parent")).getText())));
  }

  //Given that you are logged into a reddit account, when you create a text post with a title, subreddit, but no text, then the post should be successfully uploaded to that subreddit.

  @Test
  public void testScenario4() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Submit a new text post")).click();
    driver.findElement(By.name("title")).click();
    driver.findElement(By.name("title")).clear();
    driver.findElement(By.name("title")).sendKeys("Hello World");
    driver.findElement(By.id("sr-autocomplete")).click();
    driver.findElement(By.id("sr-autocomplete")).clear();
    driver.findElement(By.id("sr-autocomplete")).sendKeys("Pitt");
    driver.findElement(By.name("submit")).click();
    driver.findElement(By.linkText("cs1632Pitt")).click();
    assertEquals("Hello World (self.Pitt)", driver.findElement(By.cssSelector("p.title")).getText());
  }

  //Given that you are logged into a reddit account, when you create a text post with text but no subreddit or title, then the post should not be uploaded.

  @Test
  public void testScenario5() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Submit a new text post")).click();
    driver.findElement(By.name("text")).click();
    driver.findElement(By.name("text")).clear();
    driver.findElement(By.name("text")).sendKeys("My name is Tim");
    driver.findElement(By.name("submit")).click();
    driver.findElement(By.linkText("cs1632Pitt")).click();
    assertThat("Hello World (self.Pitt)", is(not(driver.findElement(By.cssSelector("p.parent")).getText())));
  }

  //Given that you are logged into a reddit account, when you create a text post with text, subreddit, but no title, then the post should not be uploaded.

  @Test
  public void testScenario6() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Submit a new text post")).click();
    driver.findElement(By.name("text")).click();
    driver.findElement(By.name("text")).clear();
    driver.findElement(By.name("text")).sendKeys("My name is Tim");
    driver.findElement(By.id("sr-autocomplete")).click();
    driver.findElement(By.id("sr-autocomplete")).clear();
    driver.findElement(By.id("sr-autocomplete")).sendKeys("Pitt");
    driver.findElement(By.name("submit")).click();
    driver.findElement(By.linkText("cs1632Pitt")).click();
    assertThat("Hello World (self.Pitt)", is(not(driver.findElement(By.cssSelector("p.parent")).getText())));
  }

  //Given that you are logged into a reddit account, when you create a text post with subreddit but no text or title, then the post should not be uploaded.

  @Test
  public void testScenario7() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Submit a new text post")).click();
    driver.findElement(By.id("sr-autocomplete")).click();
    driver.findElement(By.id("sr-autocomplete")).clear();
    driver.findElement(By.id("sr-autocomplete")).sendKeys("Pitt");
    driver.findElement(By.name("submit")).click();
    driver.findElement(By.linkText("cs1632Pitt")).click();
    assertThat("Hello World (self.Pitt)", is(not(driver.findElement(By.cssSelector("p.parent")).getText())));
  }

  //Given that you are not logged into a reddit account, when you try to create a text post with any combination of title/text/subreddit, then you should be prompted to log into or sign up for a reddit account.

  @Test
  public void testScenario8() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Submit a new text post")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("div.modal-body"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    assertTrue(isElementPresent(By.cssSelector("div.modal-body")));
  }

  @After
  public void tearDown() throws Exception {
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
}
