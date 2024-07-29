package com.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class GoogleSearchDataDrivenTest {
    WebDriver driver;
    WebDriverWait wait;
@BeforeClass
    public void setup(){
     driver = new ChromeDriver();
    //maximize the window
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
    wait = new WebDriverWait(driver,Duration.ofMillis(30000));

}

@Test(dataProvider="testdata")
    public void googleSearchTest(String keyword){
    //open the url
    driver.get("https://google.com");
    wait.until(ExpectedConditions.titleIs("Google"));
    Assert.assertEquals(driver.getTitle(),"Google");
    WebElement searchEditbox = driver.findElement(By.name("q"));
    searchEditbox.clear();
    searchEditbox.sendKeys(keyword);
    searchEditbox.sendKeys(Keys.ENTER);
    wait.until(ExpectedConditions.titleContains(keyword+" - Google Search"));

}

@DataProvider
    public Object[][] testdata(){
Object[][] data = new Object[3][1];
data[0][0]="selenium";
data[1][0]="playwright";
data[2][0]="cypress";
return data;
}

@AfterClass
    public void tearDown(){
    if(driver!=null){
        driver.quit();
    }
}
}
