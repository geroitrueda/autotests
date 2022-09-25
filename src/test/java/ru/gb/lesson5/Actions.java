package ru.gb.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.function.Function;

public class Actions {
    WebDriver driver;
    WebDriverWait webDriverWait;
    org.openqa.selenium.interactions.Actions actions;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new org.openqa.selenium.interactions.Actions(driver);
        driver.get("https://afisha.ru");
    }

    @Test
    void hightlightTextTest() throws InterruptedException {
        driver.get("https://translate.google.ru/?sl=en&tl=ru&text=text&op=translate");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@aria-label='Исходный текст']/following-sibling::div/span")));
        actions.moveToElement(driver.findElement(By.xpath("//textarea[@aria-label='Исходный текст']/following-sibling::div/span")), -20, 0)
                .clickAndHold()
                .moveByOffset(30, 0)
                .perform();
        Thread.sleep (5000);
    }

    @Test
    void yetNewExamples() throws InterruptedException {
        driver.get("https://yandex.ru");
        ((JavascriptExecutor) driver).executeScript("alert('ddddd')");
        Thread.sleep(5000);
        driver.switchTo().alert().accept();
        Thread.sleep(5000);

        driver.switchTo().newWindow(WindowType.TAB);
        Thread.sleep(2000);

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        driver.get("https://ya.ru");
        Thread.sleep(2000);
        driver.switchTo().window(tabs.get(0));
        driver.close();
    }
    @Test
    void authTest() {
        driver.findElement(By.xpath("//button[.='Войти']")).click();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src, 'login')]")));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("login")));
        driver.findElement(By.id("login")).sendKeys("spartalex1993");
        driver.findElement(By.id("password")).sendKeys("Test");
        webDriverWait.until(d -> d.findElement(By.id("login")).getAttribute("value").contains("@rambler.ru"));
        driver.findElement(By.xpath("//button[.='Войти']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header//a//div[contains(., 'Избранное') and preceding-siblig::span]")));
        Assertions.assertTrue(driver.findElement(By.xpath("//header//a//div[contains(., 'Избранное') and preceding-siblig::span]")).isDisplayed());
    }
    //section//button[@type and @class] todo xpath for location
    @AfterEach
    void tearDown() {
        driver.quit();
    }

}