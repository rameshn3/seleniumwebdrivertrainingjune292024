package com.junit;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FrameDemo {
    static WebDriver driver;
    static WebDriverWait wait;
    @BeforeAll
public static void launchBrowser(){
        // Create ChromeOptions and add arguments to disable the infobar
        ChromeOptions options = new ChromeOptions();
        //run in incognito mode
        options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        // Initialize ChromeDriver with the options
       driver = new ChromeDriver(options);
wait = new WebDriverWait(driver, Duration.ofSeconds(30));
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//maximize the window
driver.manage().window().maximize();

driver.get("https://the-internet.herokuapp.com/");

wait.until(ExpectedConditions.titleIs("The Internet"));
        Assertions.assertEquals(driver.getTitle(),"The Internet");
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/h1")));
//click on Frames link
        driver.findElement(By.linkText("Frames")).click();

    }



    @Test
    public void nestedFrameTest(){
        System.out.println("Started executing the nestedFrameTest()...");

        wait.until(ExpectedConditions.urlContains("/frames"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.example h3")));
        Assertions.assertTrue(driver.findElement(By.cssSelector("div.example h3")).isDisplayed());

//click on Nested Frames link
        driver.findElement(By.xpath("//*[text()='Nested Frames']")).click();
      wait.until(ExpectedConditions.urlContains("/nested_frames"));
      //switch to top frame
        driver.switchTo().frame("frame-top").switchTo().frame("frame-middle");
        //fetch the text
        String txt = driver.findElement(By.id("content")).getText();
        Assertions.assertEquals(txt,"MIDDLE");

    }


    @AfterAll
    public static void quitDriver(){
        if(driver!=null){
            driver.quit();
        }
    }
    private void verifyResult(String msg){
        Assertions.assertEquals(msg,driver.findElement(By.id("result")).getText());
    }
}
