package com.mnewtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightFinderPage {
    private Wait<WebDriver> wait;

    private WebDriver driver;

    @FindBy(xpath = "//input[@type = 'radio'][@value= 'oneway']")
    WebElement oneWay;

    @FindBy(xpath = "//input[@type = 'radio'][@value= 'roundtrip']")
    WebElement roundTrip;

    @FindBy(xpath = "//select[@name='passCount']")
    WebElement passengers;

    @FindBy(xpath = "//select[@name='fromPort']")
    WebElement departingFrom;

    @FindBy(xpath = "//select[@name='fromMonth']")
    WebElement fromMonth;

    @FindBy(xpath = "//select[@name='fromDay']")
    WebElement fromDay;

    @FindBy(xpath = "//select[@name='toPort']")
    WebElement toPort;

    @FindBy(xpath = "//select[@name='toMonth']")
    WebElement toMonth;

    @FindBy(xpath = "//select[@name='toDay']")
    WebElement toDay;

    @FindBy(xpath = "//input[@type = 'radio'][@value='Business']")
    WebElement businessClass;

    @FindBy(xpath = "//input[@type = 'radio'][@value='Coach']")
    WebElement economyClass;

    @FindBy(xpath = "//input[@type = 'radio'][@value='First']")
    WebElement firstClass;

    @FindBy(xpath = "//select[@name='airline']")
    WebElement airline;

    @FindBy(xpath = "//input[@name='findFlights']")
    WebElement findButton;

    public FlightFinderPage(WebDriver driver) {
        wait = new WebDriverWait(driver, 10, 500);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitPage() {
        wait.until(ExpectedConditions.titleContains("Find a Flight: Mercury Tours:"));
    }

    public void setOneWay() {
        oneWay.click();
    }

    public void setRoundTrip() {
        roundTrip.click();
    }

    public void setFromPort(String val) {
        new Select(departingFrom).selectByValue(val);
    }

    public void setPassengersCount(int count) {
        new Select(passengers).selectByValue(String.valueOf(count));
    }

    public void setFromMonth(int month) {
        new Select(fromMonth).selectByValue(String.valueOf(month));
    }

    public void setFromDay(int day) {
        new Select(fromDay).selectByValue(String.valueOf(day));
    }

    public void setToPort(String val) {
        new Select(toPort).selectByValue(val);
    }

    public void setToMonth(int month) {
        new Select(toMonth).selectByValue(String.valueOf(month));
    }

    public void setToDay(int day) {
        new Select(toDay).selectByValue(String.valueOf(day));
    }

    public void setBusinessClass() {
        businessClass.click();
    }

    public void setEconomyClass() {
        economyClass.click();
    }

    public void setFirstClass() {
        firstClass.click();
    }

    public void setAirline(String val) {
        new Select(airline).selectByVisibleText(val);
    }

    public void findFlight() {
        findButton.click();
    }
}
