package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    //*********BasePage Variables*********
    private String baseURL = "https://www.tinkoff.ru/";
    //-------------------By Locators-------------------------------------
    private By paymentTab = By.xpath(".//li[@data-menu-item='2']");//Платежи

    public HomePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor javascriptExecutor) {
        super(driver, wait, javascriptExecutor);
    }

    /**
     * Open Home Page("https://www.tinkoff.ru/")
     */
    public void openHomePage (){
        driver.get(baseURL);
    }

    /**
     * Переход в Платежи
     */
    public void paymentTabClick(){
        wait.until(ExpectedConditions.elementToBeClickable(paymentTab)).click();
    }
}
