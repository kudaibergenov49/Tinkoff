package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Страница Платежи
 */
public class PaymentsPage extends BasePage {
    private By HAU = By.xpath(".//span[a[span[text()='ЖКХ']]]");//ЖКХ
    private By searchReceiver = By.xpath(".//label[span[text()]]/input");// Название или ИНН получателя
    private By searchResult = By.xpath(".//div[@class='SearchSuggest__results_1Tp9e']/div[1]/div[@class='Grid__root_1nlgc Grid__root_display_block_lwIvG']/div[1]/div/div[1]/div[1]");//

    public PaymentsPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor javascriptExecutor) {
        super(driver, wait, javascriptExecutor);
    }

    /**
     * Переход в ЖКХ
     */
    public void HAUClick(){
        wait.until(ExpectedConditions.elementToBeClickable(HAU)).click();
    }

    /**
     * Написать текст в строке поиска
     * @param text
     */
    public void searchReceiverInputText(String text){
        wait.until(ExpectedConditions.elementToBeClickable(searchReceiver)).sendKeys(text);
    }

    /**
     * @return текст первого элемента в списке результатов поиска
     */
    public String searchResultText(){
        return wait.until(ExpectedConditions.elementToBeClickable(searchResult)).getText();
    }

    /**
     * Перейти в первый элемент в списке результатов поиска
     */
    public void searchResultClick(){
        wait.until(ExpectedConditions.elementToBeClickable(searchResult)).click();
    }
}
