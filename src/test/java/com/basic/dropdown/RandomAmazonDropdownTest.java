package com.basic.dropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomAmazonDropdownTest {

    public static void main(String[] args) throws InterruptedException {
        //interface revvariable = new implementedclass();
        WebDriver driver = new ChromeDriver();

        //maximize the browser
        driver.manage().window().maximize();

        //add implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        //launch the url
        driver.get("https://www.amazon.in/");

        //fetch current page title -getTitle()
        String pageTitle= driver.getTitle();
        System.out.println("current page title is:"+pageTitle);


        //css with class => tagname.classvalue
        WebElement allCategoryDropdown=driver.findElement(By.cssSelector("select.nav-search-dropdown.searchSelect.nav-progressive-attrubute.nav-progressive-search-dropdown"));

        //Create Object for Select
        Select sel = new Select(allCategoryDropdown);

        //fetch all the dropdown options
        List<WebElement>optionsList = sel.getOptions();

        //Create object for Random class java.util package which provides a method(nextInt()) to generate random integer in given size
        Random robj = new Random();
        for(int i=1;i<5;i++){
            int randomNumber= robj.nextInt(optionsList.size());
            //select random option from dropdown
            sel.selectByIndex(randomNumber);
            System.out.println("Option selected at "+randomNumber+" is :"+sel.getFirstSelectedOption().getText());
        }

        //quit entire webdriver
      driver.quit();

    }


}
