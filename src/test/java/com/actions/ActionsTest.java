package com.actions;
import com.test.parallel.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.time.Duration;

public class ActionsTest {
    private WebDriver driver;
    private WebDriverWait wait;
    @BeforeClass
    @Parameters("browser")
    public void setup(String browser){
        driver= WebDriverFactory.getInstance(browser).getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void dragAndDropTest() {
        driver.get("https://jqueryui.com/droppable/");
      /*  String dragAndDropTitle="Droppable | jQuery UI";
        wait.until(ExpectedConditions.titleIs(dragAndDropTitle));
        Assert.assertEquals(driver.getTitle(),dragAndDropTitle);*/
        //switch to the iframe
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        WebElement src= driver.findElement(By.id("draggable"));
        WebElement trgt= driver.findElement(By.id("droppable"));
        Actions act = new Actions(driver);
       // act.dragAndDrop(src,trgt).perform();
        act.clickAndHold(src).perform();
        act.moveToElement(trgt).perform();
        act.release().perform();
    }

    @Test
    public void dragAndDropByTest() {
        driver.get("https://jqueryui.com/draggable/");
       /* String dragAndDropTitle="Draggable | jQuery UI";
        wait.until(ExpectedConditions.titleIs(dragAndDropTitle));
        Assert.assertEquals(driver.getTitle(),dragAndDropTitle);*/
        //switch to the iframe
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        WebElement src= driver.findElement(By.id("draggable"));
        Actions act = new Actions(driver);
        act.dragAndDropBy(src,120,150).perform();
    }

    @AfterClass
    public void tearDown(){
        WebDriverFactory.quitDriver();
    }


}

