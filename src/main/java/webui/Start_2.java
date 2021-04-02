package ru.geekbrains.webui;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;

/*
    Начало работы со стендом, экран логина.

    2. Переключение между вкладками браузера
    3. Завершение работы драйвера (QUIT/CLOSE)

 */
public class Start_2 {

    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

        // Класс настроек Chrome browser https://chromedriver.chromium.org/capabilities
        ChromeOptions options = new ChromeOptions();

        // Полный перечень https://peter.sh/experiments/chromium-command-line-switches/
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        WebDriver driver = new ChromeDriver(options);

        // Открываем первую вкладку
        driver.get(LOGIN_PAGE_URL);
        Thread.sleep(1000);

        // Создаем вторую вкладку. (Разбор на уроке 5, сейчас показать как магию)
        ((JavascriptExecutor) driver).executeScript("window.open()");

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        // Переключаемся на вторую вкладку
        driver.switchTo().window(tabs.get(1));
        driver.get(LOGIN_PAGE_URL);
        Thread.sleep(1000);

        // Возвращаемся на первую вкладку
        driver.switchTo().window(tabs.get(0));
        Thread.sleep(1000);

        // Демонстрация CLOSE - закрывает вкладку
        driver.close();
        Thread.sleep(1000);

        // Демонстрация QUIT - закрывает браузер
        driver.quit();
    }

}
