package com.mnewtours.pages;

import com.google.common.base.CharMatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookPage {
    private Wait<WebDriver> wait;

    private WebDriver driver;

    @FindBy(xpath = "(//font[contains(.,'$')])[1]")
    WebElement taxes;

    @FindBy(xpath = "(//font[contains(.,'$')])[2]")
    WebElement totalPrice;

    @FindBy(xpath = "//tr[3]/td[3]/font")
    WebElement departPrice;

    @FindBy(xpath = "//tr[6]/td[3]/font")
    WebElement returnPrice;

    @FindBy(xpath = "(//b//font[contains(.,'/')])[1]")
    WebElement departDate;

    @FindBy(xpath = "(//b//font[contains(.,'/')])[2]")
    WebElement returnDate;

    @FindBy(xpath = "//tr/td/font[contains(.,'Passengers')]/following::td")
    WebElement passengerCount;

    @FindBy(xpath = "//select[@name='creditCard']")
    WebElement cardType;

    @FindBy(xpath = "//input[@name='creditnumber']")
    WebElement cardNumber;

    @FindBy(xpath = "//input[@name='cc_frst_name']")
    WebElement ccFirstName;

    @FindBy(xpath = "//input[@name='cc_mid_name']")
    WebElement ccMidName;

    @FindBy(xpath = "//input[@name='cc_last_name']")
    WebElement ccLastName;

    @FindBy(xpath = "//select[@name='cc_exp_dt_mn']")
    WebElement ccExpMonth;

    @FindBy(xpath = "//select[@name='cc_exp_dt_yr']")
    WebElement ccExpYear;

    @FindBy(xpath = "//input[@name='billAddress1']")
    WebElement billAddress;

    @FindBy(xpath = "//input[@name='billCity']")
    WebElement billCity;

    @FindBy(xpath = "//input[@name='billState']")
    WebElement billState;

    @FindBy(xpath = "//input[@name='billZip']")
    WebElement billZip;

    @FindBy(xpath = "//select[@name='billCountry']")
    WebElement billCountry;

    @FindBy(xpath = "//input[@name='delAddress1']")
    WebElement deliveryAddress;

    @FindBy(xpath = "//input[@name='delCity']")
    WebElement deliveryCity;

    @FindBy(xpath = "//input[@name='delState']")
    WebElement deliveryState;

    @FindBy(xpath = "//input[@name='delZip']")
    WebElement deliveryZip;

    @FindBy(xpath = "//select[@name='delCountry']")
    WebElement deliveryCountry;

    @FindBy(xpath = "//input[@name='buyFlights']")
    WebElement purchase;

    public BookPage(WebDriver driver) {
        wait = new WebDriverWait(driver, 10, 500);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitPage() {
        wait.until(ExpectedConditions.titleContains("Book a Flight: Mercury Tours"));
    }

    public int getReturnPrice() {
        return Integer.parseInt(returnPrice.getText());
    }

    public int getDepartPrice() {
        return Integer.parseInt(departPrice.getText());
    }

    public int getTotalPrice() {
        String sum = CharMatcher.DIGIT.retainFrom(totalPrice.getText());
        return Integer.parseInt(sum);
    }

    public int getTaxes() {
        String sum = CharMatcher.DIGIT.retainFrom(taxes.getText());
        return Integer.parseInt(sum);
    }

    public int getPassengersCount() {
        return Integer.parseInt(passengerCount.getText());
    }

    public String getDepartDate() {
        return departDate.getText();
    }

    public String getReturnDate() {
        return returnDate.getText();
    }

    public void setPassenger(int number, String firstName, String lastName, String meal) {
        String firstPath = String.format("//input[@name='passFirst%d']", number - 1);
        String lastPath = String.format("//input[@name='passLast%d']", number - 1);
        String mealPath = String.format("//select[@name='pass.%d.meal']", number - 1);
        driver.findElement(By.xpath(firstPath)).sendKeys(firstName);
        driver.findElement(By.xpath(lastPath)).sendKeys(lastName);
        new Select(driver.findElement(By.xpath(mealPath))).selectByVisibleText(meal);
    }

    public void setCard(String type, String number, int expMonth, int expYear, String firstName, String middleName, String lastName) {
        new Select(cardType).selectByVisibleText(type);
        cardNumber.sendKeys(number);
        ccFirstName.sendKeys(firstName);
        ccMidName.sendKeys(middleName);
        ccLastName.sendKeys(lastName);
        new Select(ccExpMonth).selectByIndex(expMonth);
        new Select(ccExpYear).selectByValue(String.valueOf(expYear));
    }

    public void setBillingAddress(String address, String city, String state, String zip, String country) {
        billAddress.clear();
        billAddress.sendKeys(address);
        billCity.clear();
        billCity.sendKeys(city);
        billState.clear();
        billState.sendKeys(state);
        billZip.clear();
        billZip.sendKeys(zip);
        new Select(billCountry).selectByVisibleText(country);
    }

    public void setDeliveryAddress(String address, String city, String state, String zip, String country) {
        deliveryAddress.clear();
        deliveryAddress.sendKeys(address);
        deliveryCity.clear();
        deliveryCity.sendKeys(city);
        deliveryState.clear();
        deliveryState.sendKeys(state);
        deliveryZip.clear();
        deliveryZip.sendKeys(zip);
        new Select(deliveryCountry).selectByVisibleText(country);
    }
    public void purchase() {
        purchase.click();
    }
}
