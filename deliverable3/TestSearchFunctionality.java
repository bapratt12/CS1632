import java.util.*;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestSearchFunctionality {
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

  //Given that you are on the reddit homepage, when a search for “cat videos” is put into the search bar and sorted by posts made today, then the search should only yield posts that were submitted on the day of the search.

  @Test
  public void testScenario1() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.name("q")).click();
    driver.findElement(By.name("q")).clear();
    driver.findElement(By.name("q")).sendKeys("cat videos");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.findElement(By.xpath("//header/div/div[2]/div/span")).click();
    driver.findElement(By.linkText("past 24 hours")).click();
    int time = 24;
    String s = new String(driver.findElement(By.xpath("//div[4]/div/div/div/div/div/span[3]/time")));
    Matcher matcher = Pattern.compile("\\d+").matcher(s);
    matcher.find();
    int i = Integer.valueOf(matcher.group());
    int val = time - i;
    assertTrue(val>=0);
  }

  //Given that you are on the reddit homepage, when a search for “cat videos” is put into the search bar and sorted by top posts, then the search should show posts with the highest number of upvotes first.

  @Test
  public void testScenario2() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.name("q")).click();
    driver.findElement(By.name("q")).clear();
    driver.findElement(By.name("q")).sendKeys("cat videos");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.findElement(By.cssSelector("div.dropdown.lightdrop > span.selected")).click();
    driver.findElement(By.linkText("top")).click();
    String s1 = new String(driver.findElement(By.name("css=span.search-score"));
    Matcher matcher = Pattern.compile("\\d+").matcher(s);
    matcher.find();
    int first = Integer.valueOf(matcher.group());
    String s2 = new String(driver.findElement(By.xpath("//div[2]/div/div/span[2]")));
    Matcher matcher = Pattern.compile("\\d+").matcher(s);
    matcher.find();
    int second = Integer.valueOf(matcher.group());
    assertTrue(first>=second);
  }

  //Given that you are on the reddit homepage, when a search for “cat videos” is put into the search bar and specified to only search one subreddit, then the search should show posts only from that subreddit.

  @Test
  public void testScenario3() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.name("q")).clear();
    driver.findElement(By.name("q")).sendKeys("cat videos");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.findElement(By.name("q")).clear();
    driver.findElement(By.name("q")).sendKeys("cat videos subreddit:funny");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    assertTrue(isElementPresent(By.linkText("/r/funny")));
    assertTrue(isElementPresent(By.xpath("(//a[contains(text(),'/r/funny')])[2]")));
    assertTrue(isElementPresent(By.xpath("(//a[contains(text(),'/r/funny')])[3]")));
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
