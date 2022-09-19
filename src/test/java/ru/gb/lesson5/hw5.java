package ru.gb.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class hw5 {
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
        driver.get("https://etagi.com");
    }

    @Test
    void authTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Войти']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[.='Продолжить']")));
        driver.findElement(By.xpath("//*[@type='tel']")).sendKeys("9829115758");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("AM1tlublu");
        driver.findElement(By.xpath("//span[text()='Продолжить']")).click();
        Thread.sleep(2000);
        Assertions.assertTrue(driver.findElement(By.xpath("//span[.='Введенные логин или пароль неверны']")).isDisplayed());
    }

    @Test
    void cityTest() {
        driver.findElement(By.xpath("//div/button[text()=\"Да, в этом\"]")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//span[.='Тюмень']")).isDisplayed());
    }

    @Test
    void flatType() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[.='Принять']")));
        driver.findElement(By.xpath("//button[.='Принять']")).click();
        //не могла нормальный xpath подобрать для элемента button
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
        driver.quit();
    }
}
