package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Узнайте задолженность по ЖКУ в Москве
 */
public class ZHKUMoscowPage extends BasePage {
    private By payHAUMoscow = By.xpath(".//span[text()='Оплатить ЖКУ в Москве']");// Результат Поиска Выбор региона

    public ZHKUMoscowPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor javascriptExecutor) {
        super(driver, wait, javascriptExecutor);
    }

    /**
     * Перейти в ЖКУ
     */
    public void payHAUMoscowClick(){
        wait.until(ExpectedConditions.elementToBeClickable(payHAUMoscow)).click();
    }
}
