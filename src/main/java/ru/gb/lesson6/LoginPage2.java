package ru.gb.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage2 extends BaseView2 {
    public LoginPage2(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@type='tel']")
    private WebElement phoneField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//span[text()='Продолжить']")
    private WebElement submitButton;

    public MyAccountPage login(String login, String password) {
        phoneField.sendKeys(login);
        passwordField.sendKeys(password);
        submitButton.click();
        return new MyAccountPage(driver);
    }

}
