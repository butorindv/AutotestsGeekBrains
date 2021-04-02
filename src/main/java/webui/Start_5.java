package ru.geekbrains.webui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/*
    Явные и неявные ожидания

    8. Заменяем все Thread.sleep на ожидания

 */
public class Start_5 {

    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String STUDENT_LOGIN = "Applanatest";
    private static final String STUDENT_PASSWORD = "Student2020!";
    private static final WebDriver driver;

    static {
        WebDriverManager.chromedriver().setup();

        // Класс настроек Chrome browser https://chromedriver.chromium.org/capabilities
        ChromeOptions options = new ChromeOptions();

        // Полный перечень https://peter.sh/experiments/chromium-command-line-switches/
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        login();

        driver.findElement(By.xpath(
            ".//ul[@class='nav nav-multilevel main-menu']/li[@class='dropdown']/a[@class='unclickable']/span[text()='Расходы']")).click();
        driver.findElement(By.xpath(".//span[@class='title' and text()='Заявки на расходы']")).click();

        // DIFF #2 ---------------------------------------------------------
        WebDriverWait waitFiveSeconds = new WebDriverWait(driver, 5);
        waitFiveSeconds.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(
            "div[class='pull-left btn-group icons-holder']"))));
        // -----------------------------------------------------------------
        driver.findElement(By.cssSelector("div[class='pull-left btn-group icons-holder']")).click();

        // DIFF #3 ---------------------------------------------------------
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains("/create"));
        // -----------------------------------------------------------------
        driver.findElement(By.xpath(".//textarea")).sendKeys("test");

        // работа с Select. Выбор поля "подразделение"
        Select businessUnitDropDown = new Select(driver.findElement(By.name("crm_expense_request[businessUnit]")));
        businessUnitDropDown.selectByValue("1");

        // работа с Select. Выбор поля "статья расхода".
        Select expenditureDropDown = new Select(driver.findElement(By.name("crm_expense_request[expenditure]")));
        expenditureDropDown.selectByValue("87");

        // Работа с текстовым вводом. Метод clear()
        driver.findElement(By.name("crm_expense_request[sumPlan]")).clear();
        driver.findElement(By.name("crm_expense_request[sumPlan]")).sendKeys("1488");

        // Работа с чекбоксом - тот же метод click(). isSelected возвращает boolean (логично)
        WebElement notifyDateHasChangedCheckbox = driver.findElement(By.name("crm_expense_request[dateChangeNotify]"));
        System.out.println("Before click: " + notifyDateHasChangedCheckbox.isSelected());
        notifyDateHasChangedCheckbox.click();
        System.out.println("After click: " + notifyDateHasChangedCheckbox.isSelected());

        // Выбираем 20 число в календаре обязательного поля 'Планируемая дата'
        // Так как на странице два поля, выбираем сложным xpath селектором такое поле, у которого в "братском"
        // текстовом лейбле класс 'required'
        driver.findElement(By.xpath(
            ".//div[preceding-sibling::div[child::label[@class='required']]]//input[@class='datepicker-input  hasDatepicker']")).click();
        driver.findElement(By.xpath(".//a[text()='20']")).click();

        // Нажимаем 'Сохранить и закрыть'
        driver.findElement(By.cssSelector("button[class='btn btn-success action-button']")).click();
    }

    private static void login() {
        driver.get(LOGIN_PAGE_URL);

        WebElement loginTextInput = driver.findElement(By.cssSelector("input[id='prependedInput']"));
        loginTextInput.sendKeys(STUDENT_LOGIN);

        WebElement passwordTextInput = driver.findElement(By.cssSelector(".span2[name='_password']"));
        passwordTextInput.sendKeys(STUDENT_PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();
    }
}
