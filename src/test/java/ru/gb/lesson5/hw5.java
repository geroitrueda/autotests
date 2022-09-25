package ru.gb.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.gb.lesson7.AdditionalLogger;
import ru.gb.lesson7.JunitExtension;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class hw5 {
    WebDriver driver;
    WebDriverWait webDriverWait;
    org.openqa.selenium.interactions.Actions actions;

    @RegisterExtension
    JunitExtension testWatcher = new JunitExtension();

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setBrowser() {
        driver = new EventFiringDecorator(new AdditionalLogger()).decorate(new ChromeDriver());
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new org.openqa.selenium.interactions.Actions(driver);
        driver.get("https://etagi.com");
    }

    @Test
    @Step("Логин")
    void authTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Войти']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[.='Продолжить']")));
        driver.findElement(By.xpath("//*[@type='tel']")).sendKeys("9829115758");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("AM1tlublu");
        driver.findElement(By.xpath("//span[text()='Продолжить']")).click();
        Thread.sleep(5000);
        Assertions.assertTrue(driver.findElement(By.xpath("//span[.='Введенные логин или пароль неверны']")).isDisplayed());
    }

    @Test
    @Step("Подтверждение города")
    void cityTest() {
        driver.findElement(By.xpath("//div/button[text()=\"Да, в этом\"]")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//span[.='Тюмень']")).isDisplayed());
    }

    @Test
    @Step("Согласие с куками")
    void cookieAgree() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[.='Принять']")));
        driver.findElement(By.xpath("//button[.='Принять']")).click();
        //Как сделать assertion на отсутствие блока?
        //Assertions.assertTrue(driver.findElement(By.xpath("//div[.='2 микрорайон ']")).isDisplayed());
    }
    @Test
    @Feature("Фильтр квартир")
    //@Issue("EC-2021") прилинковать к трекингу
    //@TmsLink("456") прилинковать к системе тесткейсов
    void flatType() {
        //не могла нормальный xpath подобрать для элемента button крестик
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div[2]/div/div/div/button")).click();
        //driver.manage().window().setSize(new Dimension(1600, 700));
        driver.findElement(By.xpath("//button[.='4+']")).click();
        driver.findElement(By.xpath("//div[@class='acol dropdowns-menu']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='2 микрорайон ']")));
        driver.findElement(By.xpath("//button[text()='2 микрорайон ']")).click();
        driver.findElement(By.xpath("//button[.='Применить фильтр']")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//div[.='2 микрорайон ']")).isDisplayed());
    }

    @AfterEach
    void tearDown() {

        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry log: logs) {
            Allure.addAttachment("Элемент лога браузера", log.getMessage());
        }
        testWatcher.setScreenshot(new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        driver.quit();
    }
}
