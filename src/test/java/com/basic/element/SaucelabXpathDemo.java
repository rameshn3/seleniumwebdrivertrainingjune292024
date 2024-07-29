package com.basic.element;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class SaucelabXpathDemo {

    public static void main(String[] args) throws InterruptedException {
        //interface revvariable = new implementedclass();
        WebDriver driver = new ChromeDriver();

        //maximize the browser
        driver.manage().window().maximize();

        //add implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        //launch the url
        driver.get("https://www.saucedemo.com/");

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
        //explicit wait comamnd
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //add a new cookie to the browser
        Cookie ck = new Cookie("test","sdas8768dfsdnbs9s9d764ass4asdasd6d545");

        driver.manage().addCookie(ck);

        //fetch all the cookies
        Set<Cookie> cookiesSetCollection = driver.manage().getCookies();
        System.out.println("no of cookies before delete :"+cookiesSetCollection.size());
        for(Cookie ckel:cookiesSetCollection){
            System.out.println("cookie name:"+ckel.getName()+" value : "+ckel.getValue());
            System.out.println("cookie domain:"+ckel.getDomain()+" path : "+ckel.getPath());
            System.out.println("cookie expiry date:"+ckel.getExpiry());
        }

        //delete cookie by name
        driver.manage().deleteCookieNamed("test");
        //fetch all the cookies
        Set<Cookie> cookiesSetCollection1 = driver.manage().getCookies();
        System.out.println("no of cookies aftere delete :"+cookiesSetCollection1.size());

        //delete all the cookies
        driver.manage().deleteAllCookies();
        //fetch all the cookies
        Set<Cookie> cookiesSetCollection2 = driver.manage().getCookies();
        System.out.println("no of cookies after delete all :"+cookiesSetCollection2.size());


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


        Thread.sleep(5000);
        //close the browser
        //driver.close();

        //quit entire webdriver
      driver.quit();

    }


}
