package lesson3;// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class JavaonlineTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void import1() {
    driver.get("http://java-online.ru/");
    driver.manage().window().setSize(new Dimension(1552, 840));
    assertThat(driver.getTitle(), is("Java онлайн для разработчиков         "));
    driver.findElement(By.linkText("Войти")).click();
    assertThat(driver.getTitle(), is("Авторизация         "));
    driver.findElement(By.id("authorization:alogin")).sendKeys("testworkp0chta@yandex.ru");
    {
      String value = driver.findElement(By.id("authorization:alogin")).getAttribute("value");
      assertThat(value, is("testworkp0chta@yandex.ru"));
    }
    driver.findElement(By.id("authorization:apassword")).sendKeys("Qqqq1111");
    {
      String value = driver.findElement(By.id("authorization:apassword")).getAttribute("value");
      assertThat(value, is("Qqqq1111"));
    }
    driver.findElement(By.name("authorization:j_idt35")).click();
    assertThat(driver.getTitle(), is("Личный кабинет         "));
  }
  @Test
  public void import2() {
    driver.get("http://java-online.ru/");
    driver.manage().window().setSize(new Dimension(1552, 840));
    assertThat(driver.getTitle(), is("Java онлайн для разработчиков         "));
    driver.findElement(By.xpath("//a[text()=\'Поиск на сайте\']")).click();
    {
      List<WebElement> elements = driver.findElements(By.xpath("//input[@id=\'formFind:findText\']"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.xpath("//input[@id=\'formFind:findText\']")).sendKeys("mysql");
    driver.findElement(By.name("formFind:j_idt30")).click();
    {
      List<WebElement> elements = driver.findElements(By.xpath("//span[@id=\'results\']/p[text()=\'По данному запросу ничего не найдено. Измените критерий поиска.\']"));
      assert(elements.size() == 0);
    }
  }
}
