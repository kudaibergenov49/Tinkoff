package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor javascriptExecutor;

    //Constructor
    BasePage(WebDriver driver, WebDriverWait wait,JavascriptExecutor javascriptExecutor){
        this.driver = driver;
        this.wait = wait;
        this.javascriptExecutor = javascriptExecutor;
    }
}
