package ru.gb.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class hm6 {
    WebDriver driver;
    MainPage mainPage;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();}

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        driver.get("https://www.etagi.com/");}

    @Test
    void flatType() {
        MainPage2.clickSingInButton()
                .login("9829115758", "221337073")
                .navigationBlock2.hoverRoomsMenuAndClick()
                .chooseMicroraion("2 микрорайон ")
                .useFilter()
                .rightMicroraion("2 микрорайон ");

}
