package com.basic.links;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LinkedinSectionLinksTest {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        //maximize the window
        driver.manage().window().maximize();

        //add implicitwait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //launch the app url
        driver.get("https://www.linkedin.com/");

        //add explicit wait
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        //wait for the page title
        wait.until(ExpectedConditions.titleContains("LinkedIn: Log In or Sign Up"));

        List<WebElement> genSecLinkList = driver.findElements(By.xpath("//div[contains(@class,'w-full flex justify-end pl')]/div[1]/ul/li/a"));

        String xpath1 = "//div[contains(@class,'w-full flex justify-end pl')]/div[1]/ul/li[";
        String xpath2 = "]/a";

    /*    xpath1+i+xpath2

//div[contains(@class,'w-full flex justify-end pl')]/div[1]/ul/li["+i+"]/a*/
for(int i=1;i<=genSecLinkList.size();i++) {
    System.out.println("***************************");
    //fetch the link name
    String linkName = driver.findElement(By.xpath(xpath1 + i + xpath2)).getText();
    System.out.println("link text is:" + linkName);
    //fetch the url of the link
    String linkUrl = driver.findElement(By.xpath(xpath1 + i + xpath2)).getAttribute("href");
    System.out.println(linkName + " url is:" + linkUrl);
    //click on each link
    driver.findElement(By.xpath(xpath1 + i + xpath2)).click();
    Thread.sleep(2000);
    System.out.println(linkName + "title is:"+driver.getTitle());
    //navigate back to previous page
    driver.navigate().back();

}

driver.quit();

    }

}
