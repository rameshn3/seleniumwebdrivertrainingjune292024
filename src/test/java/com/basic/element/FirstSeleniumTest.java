package com.basic.element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Set;
public class FirstSeleniumTest {

    public static void main(String[] args) throws InterruptedException {
        //interface revvariable = new implementedclass();
        WebDriver driver = new ChromeDriver();

        //maximize the browser
        driver.manage().window().maximize();

        //launch the url
        driver.get("https://facebook.com");

        //fetch current page title -getTitle()
        String pageTitle= driver.getTitle();
        System.out.println("current page title is:"+pageTitle);

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

        //identify the email editbox
        WebElement emailEditbox = driver.findElement(By.id("email"));
    //clear the content
        emailEditbox.clear();
        //type the email
        emailEditbox.sendKeys("rameshn3@gmail.com");
      WebElement passwordEditbox = driver.findElement(By.name("pass"));
        passwordEditbox.clear();
        passwordEditbox.sendKeys("test13");

        WebElement loginButton = driver.findElement(By.id("loginbutton"));
       // loginButton.click();
        loginButton.submit();

        WebElement forgotpassword_link=driver.findElement(By.linkText("Forgot password?"));
        forgotpassword_link.click();

        //nvigate back to previous page
        driver.navigate().back();

        Thread.sleep(3000);
        //close the browser
        //driver.close();

        //quit entire webdriver
      driver.quit();

    }


}
