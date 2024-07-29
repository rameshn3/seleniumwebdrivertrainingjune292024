package com.test.parallel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
/**
 * This class is using singlton design pattern
 */
public class WebDriverFactory {
    //create a static variable
    private static volatile WebDriverFactory instance;
    private static ThreadLocal<WebDriver>tlDriver = new ThreadLocal<>();
    //create a private constructor
    private WebDriverFactory(){}
    private void initDriver(String browser){
        switch (browser){
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                tlDriver.set(new ChromeDriver(chromeOptions));
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--private");
                tlDriver.set(new FirefoxDriver(firefoxOptions));
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--inprivate");
                tlDriver.set(new EdgeDriver(edgeOptions));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser:"+browser);
        }
        //maximize the window
        getDriver().manage().window().maximize();
    }
    //create a getInstance method
    public static WebDriverFactory getInstance(String browser){
        if(instance==null){
            synchronized (WebDriverFactory.class){
                if(instance==null){
                    instance = new WebDriverFactory();
                }
            }
        }
if(tlDriver.get()==null){
    instance.initDriver(browser);
}
return instance;
    }
    public WebDriver getDriver(){
       return tlDriver.get();
    }

    public static void quitDriver(){
        if(tlDriver.get()!=null){
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
