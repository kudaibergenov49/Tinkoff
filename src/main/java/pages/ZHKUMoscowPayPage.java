package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Оплатите ЖКУ в Москве без комиссии
 */
public class ZHKUMoscowPayPage extends BasePage {
    private By paymentTab = By.xpath(".//li[@data-menu-item='2']");//Платежи

    private By payerCode = By.id("payerCode");//Код плательщика ЖКУ в Москве
    private By period = By.id("period");// За какой период вы оплачиваете коммунальные услуги
    private By housingInsuranceAmount = By.xpath(".//label[span[text()='Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве, \u20BD ']]/div[@class='Input__value_2Kx90 Input__value_changed_2SIME']/input");// Сумма страхования жилья
    private By paymentSum = By.xpath(".//label[span[contains(text(),'Сумма платежа,  от 10 до 15 000')]]");// Сумма платежа

    private By payButton = By.xpath(".//button/h2[text()='Оплатить ЖКУ в Москве']");// Кнопка Оплатить ЖКУ в Москве
    //---------------------------------------error Messages--------------------------------------
    private By payerCodeError = By.xpath(".//div[@class='ui-grid__column ui-grid__column_desktop_11']/form/div[1]/div/div[2]");//Код плательщика ЖКУ в Москве - Сообщение об ошибке заполения
    private By periodError = By.xpath(".//div[@class='ui-grid__column ui-grid__column_desktop_11']/form/div[2]/div/div[@class='ui-form-field-error-message ui-form-field-error-message_ui-form']");//Код плательщика ЖКУ в Москве
    private By housingInsuranceAmountError = By.xpath(".//div[@class='ui-grid__column ui-grid__column_desktop_11']/form/div[3]/div/div[@class='ui-form-field-error-message ui-form-field-error-message_ui-form']");// Сумма страхования жилья
    private By paymentSumError = By.xpath(".//form/div[4]/div/div/div/div/div/div/div/div[2]");// Сумма платежа

    public ZHKUMoscowPayPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor javascriptExecutor) {
        super(driver, wait, javascriptExecutor);
    }

    /**
     * Проверка сообщении ошибок при некорректном заполении полей
     * @param code Код плательщика ЖКУ в Москве
     * @param dates За какой период вы оплачиваете коммунальные услуги
     * @param insuranceAmount Сумма страхования жилья
     * @param paySum Сумма платежа
     */
    public void checkFillingFields(String code,String dates,String insuranceAmount,String paySum){
        wait.until(ExpectedConditions.visibilityOfElementLocated(period)).sendKeys(dates);
        wait.until(ExpectedConditions.visibilityOfElementLocated(payerCode)).sendKeys(code);
        wait.until(ExpectedConditions.visibilityOfElementLocated(housingInsuranceAmount)).sendKeys(insuranceAmount);
        wait.until(ExpectedConditions.visibilityOfElementLocated(paymentSum)).sendKeys(paySum);
        wait.until(ExpectedConditions.visibilityOfElementLocated(payButton)).click();
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(payerCodeError)).getText(),"Поле неправильно заполнено");
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(periodError)).getText(),"Поле заполнено некорректно");
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(housingInsuranceAmountError)).getText(),"Сумма добровольного страхования не может быть больше итоговой суммы.");
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(paymentSumError)).getText(),"Минимальная сумма перевода — 10 \u20BD");
    }

    /**
     * Проверка сообщении ошибок при пустых значениях полей
      */
    public void checkNullFields(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(payButton)).click();
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(payerCodeError)).getText(),"Поле обязательное");
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(periodError)).getText(),"Поле обязательное");
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(paymentSumError)).getText(),"Поле обязательное");
    }

    /**
     * Сумма платежа отрицательна
     */
    public void checkMinusPaymentSum(String paySum){
        WebElement paymntSum = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentSum));
        paymntSum.sendKeys(paySum);
        wait.until(ExpectedConditions.visibilityOfElementLocated(payButton)).click();
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(paymentSumError)).getText(),"Поле заполнено неверно");
        paymntSum.sendKeys(Keys.BACK_SPACE);
        paymntSum.sendKeys(Keys.BACK_SPACE);
    }
    /**
     * Переход в платежи
     */
    public void paymentTabClick(){
        wait.until(ExpectedConditions.elementToBeClickable(paymentTab)).click();
    }

    /**
     * Нажать на кнопку Оплатить ЖКУ в Москве
     */
    public void payButtonClick(){
        wait.until(ExpectedConditions.elementToBeClickable(payButton)).click();
    }

}
