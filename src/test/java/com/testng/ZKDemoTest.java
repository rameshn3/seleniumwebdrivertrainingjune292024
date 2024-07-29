package com.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class ZKDemoTest {
    WebDriver driver;
    WebDriverWait wait;

    String ZkDemoInputTitle="ZK Live Demo - Input";
@BeforeClass(alwaysRun = true)
    public void setup(){
     driver = new ChromeDriver();
    //maximize the window
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
    wait = new WebDriverWait(driver,Duration.ofMillis(30000));

}

@BeforeMethod(alwaysRun = true)
public void launchUrl(){
    driver.get("https://www.zkoss.org/zkdemo/input");
    wait.until(ExpectedConditions.titleContains(ZkDemoInputTitle));
    Assert.assertEquals(driver.getTitle(),ZkDemoInputTitle);
}

//@Test(description="handling popups",timeOut = 3000)
//@Test(description="handling popups",priority = 1)
@Test(description="handling popups",priority = 1,groups = {"smoke"})
    public void xPostTest(){
    //click on Date And TimePicker
    driver.findElement(By.linkText("Date and Time Picker")).click();
    String dataTimePickerTitle="ZK Live Demo - Date and Time Picker";
    wait.until(ExpectedConditions.titleContains(dataTimePickerTitle));
    Assert.assertEquals(driver.getTitle(),dataTimePickerTitle);
    //click on xpost
    //handle the iframes
    WebElement ifrm = driver.findElement(By.xpath("//iframe[@title='X Post Button']"));
    //switch to iframe
    driver.switchTo().frame(ifrm);
    //click on xpost
    driver.findElement(By.xpath("//a[contains(@href,'twitter')]")).click();
    //fetch all the window ids
    Set<String> handles = driver.getWindowHandles();
    //iterate the collection
    Iterator<String>it = handles.iterator();
    String pid = it.next();
    String chwinid= it.next();
    //switch to child tab
    driver.switchTo().window(chwinid);
    //click on login button
    driver.findElement(By.xpath("//*[@id=\"layers\"]/div[3]/div/div/div/div/div/div[2]/div[2]/div/div[2]/button[2]")).click();
    //type the value in username edibox
    driver.findElement(By.xpath("//input[@autocomplete='username']")).sendKeys("rameshn3@gmail.com");
    //close the child window
    driver.close();
    //switch back to parent window
    driver.switchTo().window(pid);
    driver.switchTo().defaultContent();
    //date editbox
    WebElement dateEditbox = driver.findElement(By.xpath("//input[contains(@class,'z-datebox-input')]"));
    dateEditbox.clear();
    dateEditbox.sendKeys("07/21/2024");

}

@Test(description="fetch all buttons",priority = 2,groups = {"smoke"})
public void buttonTest() {
//click on Button
    driver.findElement(By.linkText("Button")).click();
    String buttonTitle = "ZK Live Demo - Button";
    wait.until(ExpectedConditions.titleContains(buttonTitle));
    Assert.assertEquals(driver.getTitle(), buttonTitle);
    //fetch all the buttons
    List<WebElement> buttonList = driver.findElements(By.className("z-button"));
    System.out.println("button list size is:" + buttonList.size());
    for (WebElement btn : buttonList) {
        System.out.println(btn.getText());
    }
}

    //@Test(description="check all checkbox",enabled=true,priority = 4,invocationCount = 3)
    @Test(groups = {"regression"},dependsOnMethods = {"buttonTest"})
    public void checkboxTest(){
//click on Checkbox
        driver.findElement(By.linkText("Checkbox")).click();
        String checkboxTitle="ZK Live Demo - Checkbox";
        wait.until(ExpectedConditions.titleContains(checkboxTitle));
        Assert.assertEquals(driver.getTitle(),checkboxTitle);
        //fetch all the buttons
        List<WebElement>checkboxList= driver.findElements(By.xpath("//input[@type='checkbox']"));
        System.out.println("checkbox list size is:"+checkboxList.size());
        for(int i=1;i<checkboxList.size();i++){
            if(!checkboxList.get(i).isSelected()){
                checkboxList.get(i).click();
            }
        }
String text=driver.findElement(By.xpath("//span[contains(text(),'You have selected')]/following::span[1]")).getText();
        System.out.println("selected checkboxes is:"+text);
}

    @Test(description="select radiobuttons",priority = 3,groups = {"regression"})
    public void radioButtonTest(){
//click on Radio Button
        driver.findElement(By.linkText("Radio Button")).click();
        String radioButtonTitle="ZK Live Demo - Radio Button";
        wait.until(ExpectedConditions.titleContains(radioButtonTitle));
        Assert.assertEquals(driver.getTitle(),radioButtonTitle);
        //select the Performance radio button
        driver.findElement(By.xpath("//label[text()='Performance']/preceding::input[1]")).click();
        String featureText=driver.findElement(By.xpath("//span[contains(text(),'Feature')]/following::span[1]")).getText();
        System.out.println("selected radio button feature text is:"+featureText);
        //select Forum radio button
        driver.findElement(By.xpath("//label[text()='Forum']/preceding::input[1]")).click();
        String websiteText=driver.findElement(By.xpath("//span[contains(text(),'Web Site')]/following::span[1]")).getText();
        System.out.println("selected radio button website text is:"+websiteText);

        //select Developer Guide radio button
        driver.findElement(By.xpath("//label[text()='Developer Guide']/preceding::input[1]")).click();
        String documentationText=driver.findElement(By.xpath("//span[contains(text(),'Documentation')]/following::span[1]")).getText();
        System.out.println("selected radio button documentation text is:"+documentationText);

    }


@AfterClass(alwaysRun = true)
    public void tearDown(){
    if(driver!=null){
        driver.quit();
    }
}
}
