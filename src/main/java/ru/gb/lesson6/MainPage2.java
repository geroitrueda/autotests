package ru.gb.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage2 extends BaseView2 {

    @FindBy(xpath = "//*[text()='Войти']")
            public static WebElement signInButton;

      public MainPage2(WebDriver driver) { super(driver);
    }
   // @FindBy(xpath = "//button[.='Принять']")
   // public static WebElement agreeButton;

    public static LoginPage clickSingInButton() {
        signInButton.click();
        return new LoginPage(driver);
      //  agreeButton.click();
      //  return new LoginPage(driver);
    }

}

