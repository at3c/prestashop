package com.qatestlab.hw2.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class to test web site with admin panel
 * @autor Admin on 08.11.2018.
 * version 1.1
 */
public class AdminTest {
    private static WebDriver driver;

    public static void main(String[] args) {
        // create instace "webdriver" for testing website
        driver = getWebDriver();
        adminPanelTest(); //test login/logout web site
        System.out.println("Test admin panel was finished");
        driver = getWebDriver();
        menuPanelTest();
        System.out.println("Test admin panel menu was finished");
    }

    /**
     * test login as Admin and logout with help admin panel
     */
    public static void adminPanelTest() {
        logIn();

        WebElement userMenu = driver.findElement(By.className("employee_name"));
        userMenu.click();
        sleepMyThread(2000);

        WebElement logout = driver.findElement(By.id("header_logout"));
        logout.click();
        sleepMyThread(1000);

        // close browser
        driver.quit();
    }

    /**
     *  login as Admin and test menu items
     */
    public static void menuPanelTest() {
        logIn();
        sleepMyThread(2000);

        // initiation ArrayList object with values of menu items
        List<String>  menuItems = new ArrayList<String>(Arrays.asList("Dashboard", "Заказы", "Каталог", "Клиенты", "Служба поддержки",
                "Статистика", "Modules","Design","Доставка","Способ оплаты","International","Shop Parameters","Конфигурация"));

        // iterate object items
        for (String item: menuItems) {
            WebElement elemItems = driver.findElement(By.linkText(item));
            elemItems.click();
            sleepMyThread(1000);
            WebElement titlePage;
            try {
                titlePage = driver.findElement(By.className("page-title"));
            } catch (NoSuchElementException e) {
                System.err.println("Title wasn't on the page: " + item);
                continue;
            } catch (Exception e) {
                System.err.println(e.toString());
                continue;
            }
                String titlePageBeforeRefresText = titlePage.getText();
                System.out.println(titlePageBeforeRefresText);
                driver.navigate().refresh();
                sleepMyThread(1000);
                WebElement titlePageAfterRefresh = driver.findElement(By.className("page-title"));

                //display results to the console
                if (!(titlePageBeforeRefresText.equals(titlePageAfterRefresh.getText()))) {
                    System.out.println("The page was refreshed. You didn't stay in the same section");
                }
                System.out.println("The page was refreshed. You stayed in the same section: " +
                        titlePageAfterRefresh.getText());
        }
        // close browser
        driver.quit();
    }

    /**
     * method set url to web browser's driver,
     * create connection to browser
     * @return      Chrome driver object to use browser as user
     */
    public static WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//resources//chromedriver.exe");
        return new ChromeDriver();
    }

    /**
     * Login to a Website as an Admin
     */
    private static void logIn() {
        //get test web site
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        sleepMyThread(2000);

        //find DOM elements and do actions
        WebElement login = driver.findElement(By.id("email"));
        login.sendKeys("webinar.test@gmail.com");

        WebElement passwd = driver.findElement(By.id("passwd"));
        passwd.sendKeys("Xcg7299bnSmMuRLp9ITw");

        WebElement buttonSubmit = driver.findElement(By.name("submitLogin"));
        buttonSubmit.submit();
        sleepMyThread(2000);
    }

    /**
     * pausing execution application
     * @param time the sleep time to the millisecond
     */
    static void sleepMyThread(long time) {
        try {
            Thread.sleep(time);

        } catch (InterruptedException e){
            System.err.println(e.toString());
        }
    }
}
