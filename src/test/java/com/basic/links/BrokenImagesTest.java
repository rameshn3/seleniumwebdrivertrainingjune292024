package com.basic.links;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class BrokenImagesTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriver driver = new EdgeDriver();
        //maximize the window
        driver.manage().window().maximize();

        //add implicitwait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //launch the app url
        driver.get("https://the-internet.herokuapp.com/broken_images");

        //add explicit wait
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        //wait for the page title
        wait.until(ExpectedConditions.urlContains("/broken_images"));

        //how can you fetch all the images from webpage
        List<WebElement> allimagesList = driver.findElements(By.tagName("img"));

        for(WebElement element:allimagesList){
            String imgsrc = element.getAttribute("src");
            if(imgsrc.startsWith("http")) {
                validateLink(imgsrc);
            }
            continue;
        }

    driver.quit();

    }

    private static void validateLink(String url) throws IOException {
        URL ul = new URL(url);
       HttpURLConnection hc = (HttpURLConnection) ul.openConnection();
       //open the connection
        hc.connect();
        //fetch the status code
        int statusCode = hc.getResponseCode();
        String rspMsg = hc.getResponseMessage();
        if(statusCode==200){
            System.out.println(url +" is working fine:"+statusCode+" - "+rspMsg);
        } else if (statusCode==404) {

            System.out.println(url +" is not working :"+statusCode+" - "+rspMsg);

        }
        hc.disconnect();
    }


}
