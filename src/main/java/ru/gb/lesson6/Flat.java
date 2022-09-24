package ru.gb.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Flat extends BaseView2 {

    public Flat(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[text()='2 микрорайон ']")
    private WebElement microraion;

    public Flat chooseMicroraion(String microraionName) {
        actions.click(microraion)
                .perform();
        return this;
    }

    @FindBy(xpath = "//button[.='Применить фильтр']")
    private WebElement Filter;

    public Flat useFilter() {
        actions.click(microraion)
                .perform();
        return this;
    }
}
