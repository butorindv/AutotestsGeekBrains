package ru.geekbrains.webui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/*
    Пишем скрипт (не тест) по созданию заявки на расход
    Процесс логина вынесен в отдельный метод - задел на переиспользуемость

    6. Продолжение рассмотрения способов поиска элементов
    7. Работа с классом Select для взаимодействия с выпадающими списками
    https://www.selenium.dev/documentation/en/support_packages/working_with_select_elements/

    В процессе создания скрипта весь контент должен помещаться в полностью развернутом окне браузера.
    Скроллы к элементу через класс Action будут разобраны на уроке 5!
 */
public class Start_4 {

    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String STUDENT_LOGIN = "Applanatest";
    private static final String STUDENT_PASSWORD = "Student2020!";
    private static final WebDriver driver;

    // Говнокод, но что поделать - статический блок инициализации выполняется самым первым.
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

        // Клик на кнопку "Расходы"
        driver.findElement(By.xpath(
            ".//ul[@class='nav nav-multilevel main-menu']/li[@class='dropdown']/a[@class='unclickable']/span[text()='Расходы']")).click();
        // Клик на выпадашку "Заявки на расходы"
        driver.findElement(By.xpath(".//span[@class='title' and text()='Заявки на расходы']")).click();

        // кликаем на кнопку создания заявки
        sleep(5000);
        driver.findElement(By.cssSelector("div[class='pull-left btn-group icons-holder']")).click();

        // ввод в поле "назначение"
        sleep(2000);
        driver.findElement(By.xpath(".//textarea")).sendKeys("test");

        // работа с Select. Выбор поля "подразделение"
        Select businessUnitDropDown = new Select(driver.findElement(By.name("crm_expense_request[businessUnit]")));
        businessUnitDropDown.selectByValue("1");

        // работа с Select. Выбор поля "статья расхода".
        Select expenditureDropDown = new Select(driver.findElement(By.name("crm_expense_request[expenditure]")));
        expenditureDropDown.selectByVisibleText("01101 - ОС: вычислительная техника инфраструктуры");

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
        driver.quit();
    }

    private static void login() {
        driver.get(LOGIN_PAGE_URL);

        // используем имя тега
        WebElement loginTextInput = driver.findElement(By.cssSelector("input[id='prependedInput']"));
        loginTextInput.sendKeys(STUDENT_LOGIN);

        // используем имя класса
        WebElement passwordTextInput = driver.findElement(By.cssSelector(".span2[name='_password']"));
        passwordTextInput.sendKeys(STUDENT_PASSWORD);

        // используя xpath
        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();
    }
}

/*
    Если студенты спросят о том, можно ли имитировать наведение курсора на элемент
    (выбор подменю на главной навигационной панели)
    Сообщить о том что можно, разбор на уроке №5.

    JFYI приведен код ниже
    ---------------------

    private static void tapOnExpensesRequestWithActionChain() {
        By btnLocator = By.xpath(
            ".//ul[@class='nav nav-multilevel main-menu']/li[@class='dropdown']/a[@class='unclickable']/span[text()='Расходы']");

        Actions actions = new Actions(driver);
        WebElement menuBtn = driver.findElement(btnLocator);
        actions.moveToElement(menuBtn);

        WebElement subMenu = driver.findElement(By.xpath(".//span[@class='title' and text()='Заявки на расходы']"));
        actions.moveToElement(subMenu);
        actions.click().build().perform();
    }

 */
