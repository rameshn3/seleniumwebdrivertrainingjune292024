package com.basic.dropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SelectClassDemo {

    public static void main(String[] args) throws InterruptedException {
        //interface revvariable = new implementedclass();
        WebDriver driver = new ChromeDriver();

        //maximize the browser
        driver.manage().window().maximize();

        //add implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        //launch the url
        driver.get("https://openwritings.net/sites/default/files/selenium-test-pages/select.html");

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


        //identify the email editbox xpath with id
        WebElement multiDropdown = driver.findElement(By.xpath("//select[@id='multi-selections']"));

        //Create Object for Select class
        Select sel = new Select(multiDropdown);

       //select an option by visibletext
        sel.selectByVisibleText("March");
        //select an option by value attribute
        sel.selectByValue("May");
        //select an option by index
        sel.selectByIndex(0);
        //select an option by visibletext
        sel.selectByVisibleText("July");
        //select an option by value attribute
        sel.selectByValue("Sep");
        //select an option by index
        sel.selectByIndex(10);
//get all the selectedoptions
        List<WebElement> selectedOptions = sel.getAllSelectedOptions();
        System.out.println("selected options size is:"+selectedOptions.size());
      //selectedOptions.forEach(e->e.getText());
for(WebElement e:selectedOptions){
    System.out.println(e.getText());
}

//deselect an option by label text
        sel.deselectByVisibleText("March");
//deselect an option by value
        sel.deselectByValue("Sep");

        //deselect an option by index
        sel.deselectByIndex(0);
   //get all selected options
   List<WebElement>remainingSelectedOptionsList = sel.getAllSelectedOptions();
        System.out.println("remaining selected options size:"+remainingSelectedOptionsList.size());
        Iterator<WebElement>it = remainingSelectedOptionsList.iterator();
        while(it.hasNext()){
            System.out.println(it.next().getText());
        }
        //deselect all the options
        sel.deselectAll();
       //get all selected options after deselectAll
        List<WebElement>remainSelectedOptsList = sel.getAllSelectedOptions();
        System.out.println("remaining selected options size after deselectAll:"+remainSelectedOptsList.size());

        //fetch all the dropdown options
        List<WebElement>optionsList=sel.getOptions();
//select last option from the dropdown
        sel.selectByIndex(optionsList.size()-1);
        System.out.println("last selected option:"+sel.getFirstSelectedOption().getText());

        for(int i=0;i<optionsList.size();i++){
            System.out.println(optionsList.get(i).getText());
        }

        Thread.sleep(5000);

        //quit entire webdriver
      driver.quit();

    }


}
