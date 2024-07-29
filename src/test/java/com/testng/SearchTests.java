package com.testng;

import com.test.parallel.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class SearchTests {
    private WebDriver driver;

    private WebDriverWait wait;

    private static final String URL = "https://www.autotrader.ca/";

    private static final By makeListId   = By.id("rfMakes");
    private static final By modelListId  = By.id("rfModel");
    private static final By postalCodeId = By.id("locationAddressV2");

    private static final String MAKE        = "BMW";
    private static final String MODEL       = "4 Series";
    private static final String POSTAL_CODE = "V6L 2Y3";

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser){
        driver= WebDriverFactory.getInstance(browser).getDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(30));
    }


    @AfterClass
    public void tearDown(){
        WebDriverFactory.quitDriver();
    }


    @Test(groups={"smoke"})
    public void searchReturnsResultsTest() throws InterruptedException {
        openHomePage();

        Assert.assertEquals(getHomePageUrl(), URL);

        selectMake(MAKE);
        selectModel(MODEL);
        typePostalCode(POSTAL_CODE);
        executeSearch();

        Thread.sleep(3000);

        String resultsPageTitle = getResultsPageTitle();
        Assert.assertTrue(resultsPageTitle.contains(MAKE));
        Assert.assertTrue(resultsPageTitle.contains(MODEL));
    }

    public String getResultsPageTitle() {
        return driver.getTitle();
    }

    public void executeSearch() {
        WebElement postalCodeBox = driver.findElement(postalCodeId);
        postalCodeBox.sendKeys(Keys.ENTER);
    }

    public void typePostalCode(String postalCode) {
        WebElement postalCodeBox = driver.findElement(postalCodeId);
        postalCodeBox.sendKeys(postalCode);
    }

    public void selectModel(String model) {
        Select modelList = getModelList();
        modelList.selectByValue(model);
    }

    public Select getModelList() {
        wait.until(ExpectedConditions.elementToBeClickable(modelListId));
        WebElement modelElement = driver.findElement(modelListId);
        return new Select(modelElement);
    }

    public void selectMake(String make) {
        Select makeList = getMakeList();
        makeList.selectByValue(make);
    }

    public Select getMakeList() {
        wait.until(ExpectedConditions.elementToBeClickable(makeListId));
        WebElement makeElement = driver.findElement(makeListId);
        return new Select(makeElement);
    }

    public String getHomePageUrl() {
        return driver.getCurrentUrl();
    }

    public void openHomePage() {
        driver.get(URL);
    }

}

