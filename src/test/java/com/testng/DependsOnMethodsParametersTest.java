package com.testng;

import com.test.parallel.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class DependsOnMethodsParametersTest {
WebDriver driver;
WebDriverWait wait;
String homePageTitle = "Your Store";
String loginTitle="";
String myAccountTitle="My Account";
By logo=By.xpath("//img[@title='Your Store']");
By myAccountMenu=By.xpath("//span[normalize-space()='My Account']");
By loginLink=By.xpath("//a[contains(.,'Login')]");
By returningCustomer = By.xpath("//h2[contains(.,'Returning Customer')]");
By logoutLink = By.xpath("(//a[contains(.,'Logout')])[1]");
By accountLogoutHeader = By.cssSelector("div[id='content'] h1");
By accounLogoutMessage = By.xpath("//p[contains(text(),'You have been logged off your account.')]");
By logoutContinueBtn=By.xpath("//a[contains(.,'Continue')]");
    private void clickMyAccountMenu(){
        WebElement myAccountMenuElement= driver.findElement(myAccountMenu);
        wait.until(ExpectedConditions.visibilityOf(myAccountMenuElement));
    //click on my account menu
        myAccountMenuElement.click();
    }

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser){
        driver= WebDriverFactory.getInstance(browser).getDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(30));
    }



@Test
public void homePageTest(){
    driver.get("http://localhost/index.php?route=common/home");
    wait.until(ExpectedConditions.titleContains(homePageTitle));
    Assert.assertEquals(driver.getTitle(),homePageTitle);
    //verify the opencart logo
    wait.until(ExpectedConditions.presenceOfElementLocated(logo));
    Assert.assertTrue(driver.findElement(logo).isDisplayed());
    clickMyAccountMenu();
    wait.until(ExpectedConditions.visibilityOfElementLocated(loginLink));
    //click on login link
    driver.findElement(loginLink).click();
    wait.until(ExpectedConditions.urlContains("route=account/login"));
}

@Parameters({"uname","pwd"})
@Test(dependsOnMethods = {"homePageTest"},alwaysRun = true)
public void doLoginTest(String uname,String pwd){
wait.until(ExpectedConditions.visibilityOfElementLocated(returningCustomer));
WebElement emailInputbox = driver.findElement(By.id("input-email"));
    emailInputbox.clear();
    emailInputbox.sendKeys(uname);
    WebElement passwordInputbox = driver.findElement(By.name("password"));
    passwordInputbox.clear();
    passwordInputbox.sendKeys(pwd);
    driver.findElement(By.xpath("//input[contains(@type,'submit')]")).click();


}

@Test(dependsOnMethods = {"doLoginTest"})
public void DoLogoutTest(){
    wait.until(ExpectedConditions.titleIs(myAccountTitle));
    wait.until(ExpectedConditions.urlContains("route=account/account"));
    clickMyAccountMenu();
    wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
    //click on login link
    driver.findElement(logoutLink).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(accountLogoutHeader));
    wait.until(ExpectedConditions.visibilityOfElementLocated(accounLogoutMessage));
    driver.findElement(logoutContinueBtn).click();

}


    @AfterClass
    public void tearDown(){
        WebDriverFactory.quitDriver();
    }


}


