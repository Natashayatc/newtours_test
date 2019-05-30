package com.mnewtours;

import com.mnewtours.pages.BookPage;
import com.mnewtours.pages.FlightFinderPage;
import com.mnewtours.pages.LoginPage;
import com.mnewtours.pages.SelectFlightPage;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.regex.Matcher;

public class NewtoursTest {
    public WebDriver WebDriver;
    @Before
    public void initWebDriver() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver = new ChromeDriver();
        try{
            WebDriver.get("http://newtours.demoaut.com");
            System.out.println("Страница "+"http://newtours.demoaut.com"+" загружена успешно");
        }catch(Exception ex){
            System.out.println("Некоректный URl "+ex.getMessage());
        }
    }

    @Test
    public void test1() {
        LoginPage loginPage = new LoginPage(WebDriver);
        loginPage.login("test1", "test1");
        MatcherAssert.assertThat("Ошибка перехода на FLIGHT FINDER", WebDriver.getTitle().contains("Find a Flight"));
        System.out.println("Страница LoginPage успешно загружена и проверена");
        FlightFinderPage finderPage = new FlightFinderPage(WebDriver);
        int passCount = 2;
        finderPage.waitPage();
        finderPage.setOneWay();
        finderPage.setFromPort("Paris");
        finderPage.setFromDay(20);
        finderPage.setPassengersCount(passCount);
        finderPage.setFromMonth(11);
        finderPage.setToPort("Seattle");
        finderPage.setToMonth(12);
        finderPage.setToDay(19);
        finderPage.setBusinessClass();
        finderPage.setAirline("Pangea Airlines");
        finderPage.findFlight();
        System.out.println("Страница FlightFinderPage успешно загружена и проверена");
        SelectFlightPage selectFlightPage = new SelectFlightPage(WebDriver);
        selectFlightPage.waitPage();
        String depDate = "11/20/2018";
        String retDate = "12/19/2018";
        selectFlightPage.setDepartFlight("Unified Airlines");
        selectFlightPage.setReturnFlight("Blue Skies Airlines$631");
        MatcherAssert.assertThat("Неверная дата", depDate.equals(selectFlightPage.getDepartDate()));
        MatcherAssert.assertThat("Неверная дата", retDate.equals(selectFlightPage.getReturnDate()));
        int depSum = selectFlightPage.getDepartSum(4);
        int retSum = selectFlightPage.getReturnSum(2);
        selectFlightPage.reserve();
        System.out.println("Страница SelectFlightPage успешно загружена и проверена");

        BookPage bookPage = new BookPage(WebDriver);
        bookPage.waitPage();
        MatcherAssert.assertThat("Ошибка в дате", depDate.equals(bookPage.getDepartDate()));
        MatcherAssert.assertThat("Ошибка в дате", retDate.equals(bookPage.getReturnDate()));
        MatcherAssert.assertThat("Ошибка в сумме", depSum == bookPage.getDepartPrice());
        MatcherAssert.assertThat("Ошибка в сумме", retSum == bookPage.getReturnPrice());
        MatcherAssert.assertThat("Ошибка в количестве пассажиров", passCount == bookPage.getPassengersCount());
        int total = (depSum + retSum) * passCount + bookPage.getTaxes();
        MatcherAssert.assertThat("Ошибка в рассчете",total == bookPage.getTotalPrice());
        bookPage.setPassenger(1, "Ivan", "Ivanov", "Hindu");
        bookPage.setPassenger(2, "Irina", "Ivanova", "Bland");
        bookPage.setCard("Visa", "5479540454132487", 5, 2009,
                "Ivan", "Ivanovich", "Ivanov");
        bookPage.setBillingAddress("1085 Borregas Ave.", "Albuquerque",
                "New Mexico", "94089", "UNITED STATES");
        bookPage.setDeliveryAddress("1225 Borregas Ave.", "Boston",
                "Massachusetts", "91089", "UNITED STATES");
        bookPage.purchase();
        System.out.println("Страница BookPage успешно загружена и проверена");
    }

    @After
    public void closeWebDriver() {
        //WebDriver.close();
    }

}
