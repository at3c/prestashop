package com.qatestlab.hw2.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to test web site with admin panel
 * @autor Admin on 04.11.2018.
 * version 1.0
 */
public class AdminTest {
    public static void main(String[] args) throws InterruptedException {
        // create instace "webdriver" for testing website
        WebDriver driver = getWebDriver();
        //Boolean resultTestAdminPanel = loginAccount(driver); //test login/logout web site
        Boolean resultTestAdminMenu = testAdminPanelMenu(driver);
        //System.out.println("Test admin panel: " + resultTestAdminPanel);
        //System.out.println("Test admin panel Menu: " + resultTestAdminMenu);
    }

//    public static Boolean loginAccount(WebDriver driver) throws InterruptedException {
//        gotoAdminPanel(driver);
//        WebElement userMenu = driver.findElement(By.className("employee_name"));
//        userMenu.click();
//        Thread.sleep(2000);
//
//        WebElement logout = driver.findElement(By.id("header_logout"));
//        logout.click();
//        Thread.sleep(1000);
//
//        // close browser
//        driver.quit();
//
//        return true;
//    }

    public static Boolean testAdminPanelMenu(WebDriver driver) throws InterruptedException {
        gotoAdminPanel(driver);
        //List<WebElement> menuItems = (ArrayList<WebElement>) driver.findElements(By.partialLinkText("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/index.php?controller=Admin"));
        WebElement elemItem = driver.findElement(By.partialLinkText("controller=Admin"));
//        for (WebElement item : menuItems) {
//            item.click();
//            Thread.sleep(2000);
//            WebElement titleBeforeRefresh = driver.findElement(By.className("page-title"));
//            System.out.println(titleBeforeRefresh.getText());
//            driver.navigate().refresh();
//            Thread.sleep(2000);
//            WebElement titleAfterRefresh = driver.findElement(By.className("page-title"));
//            if (titleBeforeRefresh.getText().equals(titleAfterRefresh.getText())) {
//                System.out.println("After refresh we stay at the web page: " + titleBeforeRefresh.getText());
//            } else {
//                System.out.println(("After refresh web site opened another page with title: " + titleAfterRefresh.getText()));
//            }
//        }
        return true;
    }

    // getter to instance WebDriver
    public static WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//resources//chromedriver.exe");
        return new ChromeDriver();
    }

    private static void gotoAdminPanel(WebDriver driver) throws InterruptedException {
        //get web site
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        Thread.sleep(2000);

        //find DOM elements and do actions
        WebElement login = driver.findElement(By.id("email"));
        login.sendKeys("webinar.test@gmail.com");

        WebElement passwd = driver.findElement(By.id("passwd"));
        passwd.sendKeys("Xcg7299bnSmMuRLp9ITw");

        WebElement buttonSubmit = driver.findElement(By.name("submitLogin"));
        buttonSubmit.click();
        Thread.sleep(2000);
    }


}
