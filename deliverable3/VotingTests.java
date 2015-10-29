package com.deliverable3.redditTests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


//The user story this class tests is as follows:
//As a logged in reddit user, I want to be able to vote on others' posts,
//so I can indicate whether I liked it or not.

public class VotingTests {
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
  //Given that you are logged into a reddit account, when I upvote a post, 
  //then its total link karma increases by 1.
  public void testVotingScenario1() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys("cs1632test");
    driver.findElement(By.name("passwd")).clear();
    driver.findElement(By.name("passwd")).sendKeys("cs1632password");
    driver.findElement(By.cssSelector("button.btn")).click();
    int likes = driver.findElements(By.cssSelector("div.midcol.likes")).size();
    int upArrows = driver.findElements(By.cssSelector("div.arrow.login-required.access-required.upmod")).size();
    WebElement e = driver.findElement(By.xpath("//div[4]/div[3]/div/div/div/div"));
    e.click();
    assertEquals(likes+1, driver.findElements(By.cssSelector("div.midcol.likes")).size());
    assertEquals(upArrows+1,driver.findElements(By.cssSelector("div.arrow.login-required.access-required.upmod")).size());
    e.click();
    driver.findElement(By.linkText("logout")).click();
  }

  @Test
  //Given that you are logged into a reddit account, when I downvote a post, 
  //then its total link karma decreases by 1.
  public void testVotingScenario2() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys("cs1632test");
    driver.findElement(By.name("passwd")).clear();
    driver.findElement(By.name("passwd")).sendKeys("cs1632password");
    driver.findElement(By.cssSelector("button.btn")).click();
    int dislikes = driver.findElements(By.cssSelector("div.midcol.dislikes")).size();
    int downArrows = driver.findElements(By.cssSelector("div.arrow.login-required.access-required.downmod")).size();
    WebElement e = driver.findElement(By.xpath("//div[4]/div[3]/div/div/div/div[5]"));
    e.click();
    assertEquals(dislikes+1, driver.findElements(By.cssSelector("div.midcol.dislikes")).size());
    assertEquals(downArrows+1,driver.findElements(By.cssSelector("div.arrow.login-required.access-required.downmod")).size());
    e.click();
    driver.findElement(By.linkText("logout")).click();
  }
  
  @Test
  //Given that you are not logged into a reddit account, when I vote on a post, 
  //then I will be prompted to log in or create an account.
  public void testVotingScenario3() throws Exception {
    driver.get(baseUrl + "/");
    WebElement e = driver.findElement(By.xpath("//div[3]/div[3]/div/div/div/div"));
    e.click();
    Thread.sleep(1000);
    assertTrue(isElementPresent(By.cssSelector("div#login")));
    driver.findElement(By.linkText("close this window")).click();
  }
  
  @Test
  //Given that you are not logged into a reddit account, when I vote on a post, 
  //then its total link karma remains unchanged.
  public void testVotingScenario4() throws Exception {
    driver.get(baseUrl + "/");
    int noVotes = driver.findElements(By.cssSelector("div.midcol.unvoted")).size();
    WebElement e = driver.findElement(By.xpath("//div[3]/div[3]/div/div/div/div"));
    e.click();
    assertEquals(noVotes, driver.findElements(By.cssSelector("div.midcol.unvoted")).size());
  }
  
  @Test
  //Given that you are logged into a reddit account, when I downvote a post I had 
  //previously upvoted, then its total link karma decreases by 2.
  public void testVotingScenario5() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys("cs1632test");
    driver.findElement(By.name("passwd")).clear();
    driver.findElement(By.name("passwd")).sendKeys("cs1632password");
    driver.findElement(By.cssSelector("button.btn")).click();
    WebElement e = driver.findElement(By.xpath("//div[4]/div[3]/div/div/div/div"));
    e.click();
    int likes = driver.findElements(By.cssSelector("div.midcol.likes")).size();
    int upArrows = driver.findElements(By.cssSelector("div.arrow.login-required.access-required.upmod")).size();
    int dislikes = driver.findElements(By.cssSelector("div.midcol.dislikes")).size();
    int downArrows = driver.findElements(By.cssSelector("div.arrow.login-required.access-required.downmod")).size();
    WebElement ed = driver.findElement(By.xpath("//div[4]/div[3]/div/div/div/div[5]"));
    ed.click();
    assertEquals(likes-1, driver.findElements(By.cssSelector("div.midcol.likes")).size());
    assertEquals(upArrows-1,driver.findElements(By.cssSelector("div.arrow.login-required.access-required.upmod")).size());
    assertEquals(dislikes+1, driver.findElements(By.cssSelector("div.midcol.dislikes")).size());
    assertEquals(downArrows+1,driver.findElements(By.cssSelector("div.arrow.login-required.access-required.downmod")).size());
    ed.click();
    driver.findElement(By.linkText("logout")).click();
  }
  
  @Test
  //Given that you are logged into a reddit account, when I upvote a post I had 
  //previously upvoted, then the upvote should be cancelled and the post’s total 
  //link karma decreases by 1.
  public void testVotingScenario6() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys("cs1632test");
    driver.findElement(By.name("passwd")).clear();
    driver.findElement(By.name("passwd")).sendKeys("cs1632password");
    driver.findElement(By.cssSelector("button.btn")).click();
    WebElement e = driver.findElement(By.xpath("//div[4]/div[3]/div/div/div/div"));
    e.click();
    int likes = driver.findElements(By.cssSelector("div.midcol.likes")).size();
    int upArrows = driver.findElements(By.cssSelector("div.arrow.login-required.access-required.upmod")).size();
    e.click();
    assertEquals(likes-1, driver.findElements(By.cssSelector("div.midcol.likes")).size());
    assertEquals(upArrows-1,driver.findElements(By.cssSelector("div.arrow.login-required.access-required.upmod")).size());
    driver.findElement(By.linkText("logout")).click();
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
