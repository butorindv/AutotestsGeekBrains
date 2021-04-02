package ru.geekbrains.webui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/*
    Начало работы со стендом, экран логина.

    1. Настройка драйвера

    Примечание: запуская несколько раз, продемонстрировать проблему незакрывающихся браузеров в System Dock
 */
public class Start_1 {

    private static WebDriver driver;
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";

    public static void main(String[] args) throws InterruptedException {
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

        // Открываем первую вкладку
        driver.get(LOGIN_PAGE_URL);
        Thread.sleep(5000);

        // Создаем вторую вкладку. (Разбор на уроке 5, сейчас показать как магию)
        ((JavascriptExecutor) driver).executeScript("window.open()");

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        // Переключаемся на вторую вкладку
        driver.switchTo().window(tabs.get(1));
        driver.get(LOGIN_PAGE_URL);
        Thread.sleep(5000);

        // Возвращаемся на первую вкладку
        driver.switchTo().window(tabs.get(0));
        Thread.sleep(5000);

        // Демонстрация CLOSE - закрывает вкладку
        driver.close();
        Thread.sleep(5000);

        // Демонстрация QUIT - закрывает браузер
        driver.quit();
    }
}
