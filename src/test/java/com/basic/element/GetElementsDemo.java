package com.basic.element;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class GetElementsDemo {

    public static void main(String[] args) throws InterruptedException {
        //interface revvariable = new implementedclass();
        WebDriver driver = new ChromeDriver();

        //maximize the browser
        driver.manage().window().maximize();

        //add implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        //launch the url
        driver.get("https://www.rediff.com/");

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
        WebElement topStoriesFirstText = driver.findElement(By.xpath("//div[@id='topdiv_0']/h2[1]/a"));
        System.out.println("first top story text:"+topStoriesFirstText.getText());
        //GetAttribute Demo
      WebElement signinLink = driver.findElement(By.className("signin"));
        System.out.println("url of the singin link:"+signinLink.getAttribute("href"));
        System.out.println("tooltip of the singin link:"+signinLink.getAttribute("title"));
        //get Css Properties
        System.out.println("color of the singin link:"+signinLink.getCssValue("color"));
        System.out.println("underline value of the singin link:"+signinLink.getCssValue("text-decoration"));
        System.out.println("font size of the singin link:"+signinLink.getCssValue("font-size"));
        System.out.println("font family of the singin link:"+signinLink.getCssValue("font-family"));
        //get signin link points(x,y) and size
        System.out.println("point of singin link: x: "+signinLink.getRect().getX()+" y: "+signinLink.getRect().getY());
        System.out.println("Size of singin link: height"+signinLink.getRect().getHeight()+" width: "+signinLink.getRect().getWidth());
        //tage name of element
        System.out.println("tag name of the singin link:"+signinLink.getTagName());

       //click on signin link
        signinLink.click();

        wait.until(ExpectedConditions.titleContains("Rediffmail"));
        WebElement usernameEditbox = driver.findElement(By.id("login1"));
        wait.until(ExpectedConditions.visibilityOf(usernameEditbox));
        if(usernameEditbox.isDisplayed()&&usernameEditbox.isEnabled()){
            System.out.println("we are in singin page");
            usernameEditbox.sendKeys("rameshn3@gmail.com");
        }
WebElement rememberCheckbox= driver.findElement(By.name("remember"));
if(rememberCheckbox.isSelected()){
    //uncheck the checkbox
    rememberCheckbox.click();
}
        Thread.sleep(5000);
 //check the checkbox
        if(!rememberCheckbox.isSelected()){
            //uncheck the checkbox
            rememberCheckbox.click();
        }
        Thread.sleep(5000);
        //quit entire webdriver
      driver.quit();

    }


}
