package com.basic.dropdown;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class AmazonSearchDropdownTest {
static WebDriver driver;
    public static void main(String[] args) throws InterruptedException, IOException {
        //interface revvariable = new implementedclass();
        driver = new ChromeDriver();

        //maximize the browser
        driver.manage().window().maximize();

        //add implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        //launch the url
        driver.get("https://www.amazon.in/");

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

        //css with class => tagname.classvalue

        selectoption(By.cssSelector("select.nav-search-dropdown.searchSelect.nav-progressive-attrubute.nav-progressive-search-dropdown"),"Electronics");

        //identify the email editbox css with Id tagname#idvalue
        WebElement searchEditbox = driver.findElement(By.cssSelector("input#twotabsearchtextbox"));
    //clear the content
        searchEditbox.clear();
        //type the email
        searchEditbox.sendKeys("Iphone");
        //click on Search icon
      WebElement searchIcn = driver.findElement(By.cssSelector("input[type='submit'][value='Go']"));
       // searchIcn.click();
        searchIcn.submit();
        //explicit wait comamnd
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.a-section.a-spacing-small.a-spacing-top-small > span:nth-child(1)")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.a-size-base.s-desktop-toolbar.a-text-normal")));
        //verify the page title
        wait.until(ExpectedConditions.titleContains("Amazon.in : Iphone"));
        captureScreenshot("searchresults-1");
//scroll down
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("window.scrollBy(0,2000)","");
        captureScreenshot("scrollDown-2");

        //scroll up
        jsx.executeScript("window.scrollBy(0,-1500)","");
        captureScreenshot("scrollUP-3");

        //scroll for back to top element
        WebElement backToTop = driver.findElement(By.xpath("//span[@class='navFooterBackToTopText'][contains(.,'Back to top')]"));

        jsx.executeScript("arguments[0].scrollIntoView(true);",backToTop);
        captureScreenshot("scrollDownForBackToTopBtn-4");

        //click on backToTop
        backToTop.click();
        captureScreenshot("scrollupForBackToTopBtn-5");
        //css with contains *
        WebElement addtocart = driver.findElement(By.cssSelector("button[id*='a-autoid']"));

        wait.until(ExpectedConditions.visibilityOf(addtocart));

        //identify logo with css contains (*) and ends-with ($)
        WebElement logo=driver.findElement(By.cssSelector("div[id*='logo'] a[href$='logo']"));
        wait.until(ExpectedConditions.visibilityOf(logo));

        //css with starts-with(^)
        WebElement deliveredToTxt=driver.findElement(By.cssSelector("div[id^='glow-ingress-block'] > span"));
        //fetch the text
        String txt = deliveredToTxt.getText();

        System.out.println("text is:"+txt);
        captureScreenshot(deliveredToTxt,"deliveredscreen-7");

        //scroll based on Keys enum
        for(int i=1;i<10;i++){
            driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
        }
        captureScreenshot("scrollByKeysDOWN-8");
        Thread.sleep(5000);
        for(int i=1;i<10;i++){
            driver.findElement(By.tagName("body")).sendKeys(Keys.UP);
        }
        captureScreenshot("scrollByKeysUP-8");

        Thread.sleep(5000);
        //close the browser
        //driver.close();

        //quit entire webdriver
      driver.quit();

    }
    /**
     * Taking the entire browser screenshot
     * @param screenName
     * @throws IOException
     */
    private static void captureScreenshot(String screenName) throws IOException {

        //take screenshot
        File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        //create Object for Date class
        Date d = new Date();
        screenName=screenName+"-"+d.toString().replace(":", "-").replace(" ", "-")+".jpg";

        //copy the file name under project directory
        FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\basic\\screenshots\\"+screenName));


    }
    /**
     * taking the element screenshot
     * @param element
     * @param screenName
     * @throws IOException
     */
    private static void captureScreenshot(WebElement element,String screenName) throws IOException {

        //take screenshot
        File src=((TakesScreenshot)element).getScreenshotAs(OutputType.FILE);

        //create Object for Date class
        Date d = new Date();
        screenName=screenName+"-"+d.toString().replace(":", "-").replace(" ", "-")+".jpg";

        //copy the file name under project directory
        FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\basic\\screenshots\\"+screenName));


    }

    /**
     * this method is used to select an option from dropdown without using select
     * @param locator
     * @param option
     */
    private static void selectoption(By locator,String option){
    WebElement allCategoryDropdown=driver.findElement(locator);
        /*2)fetch all the dropdown options using findElements API and
        tagName -option and store in List type collection*/
    List<WebElement> optsList=allCategoryDropdown.findElements(By.tagName("option"));

    //for(datatype varname:collectionname){}
    for(WebElement o:optsList){
        //print each dropdown option text
        System.out.println(o.getText());

        if(o.getText().equals(option)){
            //select it
            o.click();
            break;
        }
    }
}
}
