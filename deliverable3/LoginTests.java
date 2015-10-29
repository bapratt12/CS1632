package com.deliverable3.redditTests;


import java.util.regex.Pattern;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

//The user story under test in this class is as follows:
//As a reddit user, I want to log into reddit.com with a username and password, 
//so that I can use all of the functionalities given to reddit account holders.

public class LoginTests {
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

  @Test
  //Given an account on reddit exists, when a user inputs a valid username and password, then the user is logged in.
  public void testLoginScenario1() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys("cs1632test");
    driver.findElement(By.name("passwd")).clear();
    driver.findElement(By.name("passwd")).sendKeys("cs1632password");
    driver.findElement(By.cssSelector("button.btn")).click();
    assertEquals("cs1632test", driver.findElement(By.xpath("//a[contains(@href, 'https://www.reddit.com/user/cs1632test/')]")).getText());
    assertFalse(isElementPresent(By.name("user")));
    assertFalse(isElementPresent(By.name("passwd")));
    driver.findElement(By.linkText("logout")).click();
  }
  
  @Test
  //Given an account on reddit exists, when a user inputs a valid username and incorrect password, then the user is not logged into the account.
  public void testLoginScenario2() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys("cs1632test");
    driver.findElement(By.name("passwd")).clear();
    driver.findElement(By.name("passwd")).sendKeys("incorrectpassword");
    driver.findElement(By.cssSelector("button.btn")).click();
    assertTrue(isElementPresent(By.name("user")));
    assertTrue(isElementPresent(By.name("passwd")));
    assertTrue(isElementPresent(By.cssSelector("div.status.error")));
    driver.navigate().refresh();
  }
  
  @Test
  //Given an account on reddit exists, when a user inputs a nonexistent username, then the user is not logged into an account.
  public void testLoginScenario3() throws Exception {
    driver.get(baseUrl + "/");
    String username = generateRandomUsername(20);
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys(username);
    driver.findElement(By.name("passwd")).clear();
    driver.findElement(By.cssSelector("button.btn")).click();
    assertTrue(isElementPresent(By.name("user")));
    assertTrue(isElementPresent(By.name("passwd")));
    assertTrue(isElementPresent(By.cssSelector("div.status.error")));
    driver.navigate().refresh();
  }
  
  //generate a random username, just about guarantees it will not be valid
  private String generateRandomUsername(int n){
	  char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
	  StringBuilder sb = new StringBuilder();
	  Random random = new Random();
	  int max = random.nextInt(4)+16;
	  for (int i = 0; i < max; i++) {
	      char c = chars[random.nextInt(chars.length)];
	      sb.append(c);
	  }
	  return sb.toString();
  }
  
  @Test
  //Given an account on reddit exists, when a user inputs a valid username but doesn’t input a password, then the user is not logged into an account.
  public void testLoginScenario4() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys("cs1632test");
    driver.findElement(By.name("passwd")).clear();
    driver.findElement(By.name("passwd")).sendKeys("");
    driver.findElement(By.cssSelector("button.btn")).click();
    assertTrue(isElementPresent(By.name("user")));
    assertTrue(isElementPresent(By.name("passwd")));
    assertTrue(isElementPresent(By.cssSelector("div.status.error")));
    driver.navigate().refresh();
  }
  
  @Test
  //Given an account on reddit exists, when a user inputs nothing for the username or password, then the user is not logged into an account.
  public void testLoginScenario5() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys("");
    driver.findElement(By.name("passwd")).clear();
    driver.findElement(By.name("passwd")).sendKeys("");
    driver.findElement(By.cssSelector("button.btn")).click();
    assertTrue(isElementPresent(By.name("user")));
    assertTrue(isElementPresent(By.name("passwd")));
    assertTrue(isElementPresent(By.cssSelector("div.status.error")));
    driver.navigate().refresh();
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
