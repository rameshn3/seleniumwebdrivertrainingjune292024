package com.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class AmazonTest {
    private WebDriver driver;
    private static final String URL = "https://www.amazon.com/";
    private By searchBoxBy = By.xpath("//input[@id='twotabsearchtextbox']");
    private By searchButtonId = By.id("nav-search-submit-button");
    private By resultBy = By.xpath("//a[@class='a-link-normal//span");
    private By productTitleBy = By.xpath("//span[@id='productTitle']");
    private By secondProductBy = By.xpath("(//div[@class='a-cardui'])[2]");
    private By addToCartBy = By.xpath("(//input[@name='submit.addToCart'])[1]");
    private By addToCartButtonBy =
            By.xpath("//input[@id='add-to-cart-button']");
    private By addedToCartBy = By.xpath("//h1[@class='a-size-medium-plus']");

    public AmazonTest(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToAmazon() {
        driver.get(URL);
    }

    public void searchProduct(String productName) {
        WebDriverWait wait = wait(10);

        wait.until(ExpectedConditions.elementToBeClickable(searchBoxBy));
        WebElement searchBox = driver.findElement(searchBoxBy);
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys(productName);

        wait.until(ExpectedConditions.elementToBeClickable(searchButtonId));
        WebElement searchBtn = driver.findElement(searchButtonId);
        searchBtn.click();
    }

    public String getPageId() {
        return driver.getWindowHandle();
    }

    public void selectProduct(String productName) {
        wait(30).until(
                ExpectedConditions.numberOfElementsToBeMoreThan(resultBy, 0));
        List<WebElement> results = driver.findElements(resultBy);

        WebElement result = results.stream()
                .filter(e ->
                        e.getText().contains(productName))
                .findFirst()
                .get();

        scrollTo(result);

        wait(10).until(ExpectedConditions.elementToBeClickable(result));
        clickJS(result);
    }

    private void switchToNewWindow(String parentWindowId) {
        Set<String> windowHandles = driver.getWindowHandles();

        String windowHandle = windowHandles.stream()
                .filter(e ->
                        !e.equals(parentWindowId))
                .findFirst()
                .get();

        driver.switchTo().window(windowHandle);
    }

    public void waitUntilTitleContains(String keyword) {
        wait(30).until(ExpectedConditions.titleContains(keyword));
    }

    public String getProductTitle() {
        wait(10).until(
                ExpectedConditions.visibilityOfElementLocated(secondProductBy));

        WebElement productTitle = driver.findElement(productTitleBy);
        return productTitle.getText().trim();
    }

    public void addProductToCart() {
        wait(10).until(
                ExpectedConditions.visibilityOfElementLocated(secondProductBy));

        WebElement addToCart = driver.findElement(addToCartBy);
        addToCart.click();
    }

    public void selectAddToCart() {
        wait(10).until(
                ExpectedConditions.elementToBeClickable(addToCartButtonBy));

        WebElement addtoCartButton = driver.findElement(addToCartButtonBy);
        addtoCartButton.click();
    }

    public String addToCartStatus() {
        wait(10).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(addedToCartBy));

        WebElement addedToCartLabel = driver.findElement(addedToCartBy);

        return addedToCartLabel.getText();
    }

    private void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).
                executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void clickJS(WebElement element) {
        ((JavascriptExecutor) driver).
                executeScript("arguments[0].click();", element);
    }

    private WebDriverWait wait(int seconds) {
        WebDriverWait product = new WebDriverWait(driver,
                Duration.ofSeconds(seconds));
        return product;
    }

   /* private void closeBrowser() {
        driver.close();
        driver.switchTo().window(originalWindow);
    }*/

}
