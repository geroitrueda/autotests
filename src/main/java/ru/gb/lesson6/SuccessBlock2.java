package ru.gb.lesson6;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SuccessBlock2 extends BaseView2 {
    public SuccessBlock2(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[.='2 микрорайон ']")
    private WebElement rightMicroraion;

    public void rightMicroraion(String microraionName) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//i[@class='icon-ok']")));
        Assertions.assertEquals(microraionName, rightMicroraion.getText());
    }
}
