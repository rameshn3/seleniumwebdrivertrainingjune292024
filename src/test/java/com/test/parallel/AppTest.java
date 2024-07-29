package com.test.parallel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class AppTest {
private WebDriver driver;
private WebDriverWait wait;
    @BeforeClass
    @Parameters("browser")
    public void setup(String browser){
        driver= WebDriverFactory.getInstance(browser).getDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(30));
    }

    @Test
    public void testSaucelabs(){

    //maximize the browser
    driver.manage().window().maximize();

    //add implicit wait
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

    //launch the url
    driver.get("https://www.saucedemo.com/");

    //fetch current page title -getTitle()
    String pageTitle= driver.getTitle();

    System.out.println(Thread.currentThread().getName()+"::"+pageTitle);
    //current page absolute url
    String url = driver.getCurrentUrl();
    System.out.println("current page url is:"+url);

    //current page source code
    String src = driver.getPageSource();

    //fetch current window id
    String pid = driver.getWindowHandle();
    System.out.println("current page window id is:"+pid);

    //how can you fetch all the window ids
    Set<String> handles = driver.getWindowHandles();
    System.out.println("all the window ids are:"+handles);
    //explicit wait comamnd
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    //identify the email editbox xpath with id
    WebElement emailEditbox = driver.findElement(By.xpath("//input[@id='user-name']"));
    //clear the content
    emailEditbox.clear();
    //type the email
    emailEditbox.sendKeys("standard_user");
    //click on Search icon
    WebElement passwordEditbox = driver.findElement(By.xpath("//input[@name='password']"));
    // searchIcn.click();
    passwordEditbox.clear();
    passwordEditbox.sendKeys("secret_sauce");

    WebElement loginBtn = driver.findElement(By.xpath("//input[@data-test='login-button']"));

    loginBtn.submit();
    //verify the page title
    wait.until(ExpectedConditions.titleIs("Swag Labs"));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Swag Labs')]")));


    //css with contains *
    WebElement hamburgerMenu = driver.findElement(By.xpath("//button[contains(@id,'react-burger-menu-btn')]"));

    wait.until(ExpectedConditions.visibilityOf(hamburgerMenu));

    //open the hamburger menu
    hamburgerMenu.click();

    //identify logo with css contains (*) and ends-with ($)
    WebElement logout=driver.findElement(By.xpath("//*[starts-with(text(),'Logout')]"));
    wait.until(ExpectedConditions.visibilityOf(logout));

    //click on logout
    logout.click();

    //wait for the homepage
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[contains(@id,'user-name')]"))));


}

@AfterClass
    public void tearDown(){
        WebDriverFactory.quitDriver();
}


}
