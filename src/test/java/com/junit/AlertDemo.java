package com.junit;

import com.sun.nio.file.ExtendedOpenOption;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertDemo {
    static WebDriver driver;
    static WebDriverWait wait;
    @BeforeAll
public static void launchBrowser(){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");

        driver = new FirefoxDriver(options);
wait = new WebDriverWait(driver, Duration.ofSeconds(30));
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//maximize the window
driver.manage().window().maximize();

driver.get("https://the-internet.herokuapp.com/");

wait.until(ExpectedConditions.titleIs("The Internet"));
        Assertions.assertEquals(driver.getTitle(),"The Internet");
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content']/h1")));
//click on JavaScript alerts link
        driver.findElement(By.linkText("JavaScript Alerts")).click();

    }

    @BeforeEach
    public void setUp(){
        wait.until(ExpectedConditions.urlContains("/javascript_alerts"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.example h3")));
    Assertions.assertTrue(driver.findElement(By.cssSelector("div.example h3")).isDisplayed());
    }

    @Test
    public void simpleAlertTest(){
        System.out.println("Started executing the simpleAlertTest()...");
//click on simple alert button -Click for JS Alert
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        //switch to alert
        Alert alt = driver.switchTo().alert();
        System.out.println("simple alert text:"+alt.getText());
        //click on simple alert ok button
        alt.accept();
        verifyResult("You successfully clicked an alert");

    }

    @Test
    public void confirmationTest(){
        System.out.println("Started executing the confirmationTest()...");
        //click on Click for JS Confirm button
        driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
    Alert conf = driver.switchTo().alert();
    System.out.println("confirmation dialog text is:"+conf.getText());
//click on ok button in confirmation alert
        conf.accept();
        verifyResult("You clicked: Ok");

        //click on Click for JS Confirm button
        driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
        Alert conf1 = driver.switchTo().alert();

//click on cancel button in confirmation alert
        conf.dismiss();
        verifyResult("You clicked: Cancel");

    }

    @Test
    public void promptTest(){
        System.out.println("Started executing the promptTest()...");
        //click on Click for JS Prompt button
        driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]")).click();
        Alert prmpt = driver.switchTo().alert();
        System.out.println("prompt dialog text is:"+prmpt.getText());

        //type the value in prompt dialog
        prmpt.sendKeys("selenium");
//click on ok button in confirmation alert
        prmpt.accept();
        verifyResult("You entered: selenium");

        driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]")).click();
        Alert prmpt1 = driver.switchTo().alert();
        prmpt1.sendKeys("selenium");
//click on cancel button in confirmation alert
        prmpt1.dismiss();
        verifyResult("You entered: null");

    }

    @AfterEach
    public void tearDown(){
        driver.manage().deleteAllCookies();
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
