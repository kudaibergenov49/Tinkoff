package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * ЖКХ
 */
public class CommunalPaymentsPage extends BasePage {
    private By firstCategoryElement = By.xpath(".//ul[@class='ui-menu ui-menu_icons UIPayments__categoryProviders_3Fsrs']/li[1]");// Первый элемент в списке категории

    private By region = By.xpath(".//span[@class='PaymentsCatalogHeader__regionSelect_3MRVi']/span");//регион
    private By regionSearchInput = By.xpath(".//input[@class = 'ui-input__field']");// Строка поисква Выбор региона
    private By regionSearchButton = By.xpath(".//div[@class='ui-input__additional']");// Кнопка Поиск Выбор региона
    private By regionSearchResult = By.xpath(".//div[@data-qa-file='UIRegions']/div[@data-qa-file='Text']/span");// Результат Поиска Выбор региона

    public CommunalPaymentsPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor javascriptExecutor) {
        super(driver, wait, javascriptExecutor);
    }

    /**
     * Выбрать регион searchSity, если не установлен город assertCity
     * @param assertCity для проверки по дефолту
     * @param searchCity название города для поиска
     */
    public void selectRegion(String assertCity,String searchCity){
        try{
            Assert.assertEquals( wait.until(ExpectedConditions.elementToBeClickable(region)).getText(),assertCity);
        }
        catch (java.lang.AssertionError e){
            wait.until(ExpectedConditions.elementToBeClickable(region)).click();
            wait.until(ExpectedConditions.elementToBeClickable(regionSearchInput)).sendKeys(searchCity);
            wait.until(ExpectedConditions.elementToBeClickable(regionSearchButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(regionSearchResult)).click();

        }
    }

    /**
     * @return Текст первого элемента в списке
     */
    public String firstCategoryElementText(){
        return wait.until(ExpectedConditions.elementToBeClickable(firstCategoryElement)).getText();
    }

    /**
     * Перейти в первый элемент в списке
     */
    public void firstCategoryElementClick(){
        wait.until(ExpectedConditions.elementToBeClickable(firstCategoryElement)).click();
    }

}
