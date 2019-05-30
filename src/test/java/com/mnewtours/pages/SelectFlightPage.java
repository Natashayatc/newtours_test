package com.mnewtours.pages;

import com.google.common.base.CharMatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectFlightPage {
    private Wait<WebDriver> wait;

    private WebDriver driver;

    @FindBy(xpath = "(//td[@class='title']//font[contains(.,'/')])[1]")
    WebElement departDate;

    @FindBy(xpath = "(//td[@class='title']//font[contains(.,'/')])[2]")
    WebElement returnDate;

    @FindBy(xpath = "//input[@name='reserveFlights']")
    WebElement reserveButton;

    public SelectFlightPage(WebDriver driver) {
        wait = new WebDriverWait(driver, 10, 500);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setDepartFlight(String val) {
        String xpath = String.format("//input[@name='outFlight'][contains(@value,'%s')]", val);
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    }

    public void setReturnFlight(String val) {
        String xpath = String.format("//input[@name='inFlight'][contains(@value,'%s')]", val);
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    }

    public String getDepartDate() {
        return departDate.getText();
    }

    public String getReturnDate() {
        return returnDate.getText();
    }

    public int getDepartSum(int departIndex) {
        String xpath = java.lang.String.format("(//font/b[contains(.,'Price')])[%d]", departIndex);
        WebElement element = driver.findElement(By.xpath(xpath));
        String inSum = CharMatcher.DIGIT.retainFrom(element.getText());
        return Integer.parseInt(inSum);
    }

    public int getReturnSum(int returnIndex) {
        String xpath = java.lang.String.format("(//font/b[contains(.,'Price')])[%d]", returnIndex + 4);
        WebElement element = driver.findElement(By.xpath(xpath));
        String sum = CharMatcher.DIGIT.retainFrom(element.getText());
        return Integer.parseInt(sum);
    }

    public void waitPage() {
        wait.until(ExpectedConditions.titleContains("Select a Flight"));
    }

    public void reserve() {
        reserveButton.click();
    }
}
