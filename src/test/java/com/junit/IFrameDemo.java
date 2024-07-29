package com.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IFrameDemo {
    static WebDriver driver;
    static WebDriverWait wait;
    @BeforeAll
public static void launchBrowser(){
        // Create EdgeOptions and add the incognito argument
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--inprivate");

        // Initialize EdgeDriver with the options
    driver = new EdgeDriver(options);
wait = new WebDriverWait(driver, Duration.ofSeconds(30));
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//maximize the window
driver.manage().window().maximize();

driver.get("https://jqueryui.com/");

wait.until(ExpectedConditions.titleIs("jQuery UI"));
        Assertions.assertEquals(driver.getTitle(),"jQuery UI");
wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Autocomplete")));
//click on Autocomplete link
driver.findElement(By.linkText("Autocomplete")).click();

    }

    @Test
    public void iFrameTest(){
        System.out.println("Started executing the iFrameTest()...");

        wait.until(ExpectedConditions.urlContains("/autocomplete/"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content h1")));
        Assertions.assertTrue(driver.findElement(By.cssSelector("#content h1")).isDisplayed());

      //switch to top frame
        //WebElement ifrme = driver.findElement(By.className("demo-frame"));
        switchToFrame(0);
        //fetch the text
        driver.findElement(By.id("tags")).sendKeys("Webdriver");
        String typedValue = driver.findElement(By.id("tags")).getAttribute("value");
       Assertions.assertEquals(typedValue,"Webdriver");

        switchtoDefaultFrame();
        //navigate to previous page
        driver.navigate().back();
        wait.until(ExpectedConditions.titleIs("jQuery UI"));
        Assertions.assertEquals(driver.getTitle(),"jQuery UI");
    }


    @AfterAll
    public static void quitDriver(){
        if(driver!=null){
            driver.quit();
        }
    }
    private void switchToFrame(WebElement frameElement) {
        try {
            if (frameElement.isDisplayed()) {
                driver.switchTo().frame(frameElement);
                System.out.println("Navigated to frame with element "+ frameElement);
            } else {
                System.out.println("Unable to navigate to frame with element "+ frameElement);
            }
        } catch (NoSuchFrameException e) {
            System.out.println("Unable to locate frame with element " + frameElement + e.getStackTrace());
        } catch (StaleElementReferenceException e) {
            System.out.println("Element with " + frameElement + "is not attached to the page document" + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Unable to navigate to frame with element " + frameElement + e.getStackTrace());
        }
    }
    public void switchtoDefaultFrame() {
        try {
            driver.switchTo().defaultContent();
            System.out.println("Navigated back to webpage from frame");
        } catch (Exception e) {
            System.out
                    .println("unable to navigate back to main webpage from frame"
                            + e.getStackTrace());
        }
    }

    public void switchToFrame(int frame) {
        try {
            driver.switchTo().frame(frame);
            System.out.println("Navigated to frame with id " + frame);
        } catch (NoSuchFrameException e) {
            System.out.println("Unable to locate frame with id " + frame
                    + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Unable to navigate to frame with id " + frame
                    + e.getStackTrace());
        }
    }
}
