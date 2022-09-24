package ru.gb.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigationBlock2 extends BaseView2 {

    public NavigationBlock2(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//button[.='4+']")
    private WebElement rooms;

    @FindBy(xpath = "//div[@class='acol dropdowns-menu']")
    private WebElement area;

    public Flat hoverRoomsMenuAndClick() {
        actions.moveToElement(rooms)
              .perform();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(area));
           area.click();
        return new Flat(driver);
    }
}
