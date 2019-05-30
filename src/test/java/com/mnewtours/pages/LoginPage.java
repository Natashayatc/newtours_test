package com.mnewtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private Wait<WebDriver> wait;

    @FindBy(xpath = "//input[@type = 'text'][@name = 'userName']")
    WebElement loginField;

    @FindBy(xpath = "//input[@type = 'password'][@name = 'password']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@type = 'image'][@name = 'login']")
    WebElement loginButton;

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        wait = new WebDriverWait(driver, 10, 500);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String login, String password) {
        wait.until(ExpectedConditions.titleContains("Welcome: Mercury Tours"));
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        loginButton.click();
    }

}
