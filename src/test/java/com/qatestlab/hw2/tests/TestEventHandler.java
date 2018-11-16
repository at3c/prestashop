package com.qatestlab.hw2.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

/**
 * Class listen tests steps and output useful information
 * about tests states
 * Extend AbstractWebDriverEventListener for override needed method
 * Created by Sydorenko B. on 13.11.2018.
 */
public class TestEventHandler extends AbstractWebDriverEventListener {

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("Trying to find Element By : " + by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("Found Element By : " + by.toString());
    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("Trying to click WebElement : " + elementDescription(webElement, webDriver));
    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("Clicked on: " + webElement.toString());
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.println("WebDriver will change value for element - "
                + elementDescription(webElement, webDriver));
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.println("WebDriver changed value for element - "
                + elementDescription(webElement, webDriver));
    }

    /**
     * Method get and return web element description in type String
     * @param element web element
     * @param driver  web driver
     * @return String with information about web element
     */
    private String elementDescription(WebElement element, WebDriver driver) {
        String description = "tag:" + element.getTagName();
       // create web elements description
        if (element.getAttribute("id") != null) {
            description += " id: " + element.getAttribute("id");
        }
        else if (element.getAttribute("name") != null) {
            description += " name: " + element.getAttribute("name");
        }
        //find value inputwith JS Executer
        if(element.getTagName().equals("input")){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String textInput = (String) js.executeScript(
                    "return arguments[0].value;", element
            );
            description = (textInput == null) || textInput.isEmpty()
                    ? description : description + " ('" + textInput + "')";
            return description;
        }
        description += " ('" + element.getText() + "')";

        return description;
    }
}
