package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class TinkoffTest extends BaseTest{
    private Pages pages;
    private Data data;
    private String testCommunalService;// Поставщие услуг : ЖКУ-Москва

    private class Pages{
        HomePage homePage = new HomePage(driver,wait,javascriptExecutor);
        PaymentsPage paymentsPage = new PaymentsPage(driver,wait,javascriptExecutor);
        CommunalPaymentsPage communalPaymentsPage = new CommunalPaymentsPage(driver,wait,javascriptExecutor);
        ZHKUMoscowPage zhkuMoscowPage = new ZHKUMoscowPage(driver,wait,javascriptExecutor);
        ZHKUMoscowPayPage zhkuMoscowPayPage = new ZHKUMoscowPayPage(driver,wait,javascriptExecutor);
    }

    private class Data{
        String moscowAssert = "Москве";
        String moscowSet = "Москва";
        String houseAndItilite = "ЖКУ-Москва";
        String failPayerCode = "123";
        String failPeriod = "123";
        String failHousingInsuranceAmount = "123";
        String failpaymentSum = "1";
        String nullSearchResult = "Ничего не найдено";
        String spbAssert = "Санкт-Петербурге";
        String spbSet = "Санкт-Петербург";
    }

    //Инициация данных и страниц
    @Test
    private void init(){
        pages = new Pages();
        data = new Data();
    }

    /**
     * Зайти на страницу Тинькофф банка - step 1
     */
    @Test(dependsOnMethods = "init")
    public void openHomePage(){
        pages.homePage.openHomePage();
    }

    /**
     * Перейти в ЖКХ = - step 2,3
     */
    @Test(dependsOnMethods = "openHomePage")
    public void transitionHousingAndUtilities(){
        pages.homePage.paymentTabClick();//Переход в Платежи
        pages.paymentsPage.HAUClick();//ЖКХ
    }

    /**
     * Перейти в ЖКУ-Москва - step 4,5
     */
    @Test(dependsOnMethods = "transitionHousingAndUtilities")
    public void goToZHKUMoscow(){
        pages.communalPaymentsPage.selectRegion(data.moscowAssert, data.moscowSet);// Регион - Москва
        //Переход в ЖКУ-Москва
        testCommunalService = pages.communalPaymentsPage.firstCategoryElementText(); // Сохранить название тестового поставщика услуг
        Assert.assertEquals(testCommunalService,data.houseAndItilite);//Убедиться,что поставщик услуг = ЖКУ Москва
        pages.communalPaymentsPage.firstCategoryElementClick(); // Перейти к Поставщику услкг
        pages.zhkuMoscowPage.payHAUMoscowClick();
    }

    /**
     * Проверить на странице "Узнайте задолженность по ЖКУ в Москве" элементы - step 6,7
     */
    @Test(dependsOnMethods = "goToZHKUMoscow")
    public void payMoscowHousingAndUtilities(){
        pages.zhkuMoscowPayPage.checkNullFields();// При пустых полях проверка сообщении о ошибках
        pages.zhkuMoscowPayPage.checkMinusPaymentSum("-2");//Проверка сообщении ошибок при отрицательной сумме платежа
        pages.zhkuMoscowPayPage.checkFillingFields(data.failPayerCode,data.failPeriod,data.failHousingInsuranceAmount,data.failpaymentSum); //Проверка появление записей об ошибках
    }

    // Переход в ЖКУ-Москва другим способом - step 8,9,10
    @Test(dependsOnMethods = "payMoscowHousingAndUtilities")
    public void checkCategoryList() {
        pages.zhkuMoscowPayPage.paymentTabClick();//Переход в Платежи
        pages.paymentsPage.searchReceiverInputText(testCommunalService);// Поиск поставщика услуг, выбранного заранее
        Assert.assertEquals(pages.paymentsPage.searchResultText(), testCommunalService);// проверка того, что искомый поставщик услуг в списке результатов поиска первый
        pages.paymentsPage.searchResultClick();//перейти на искомый поставщик услуг
    }

    // step 11,12 - Убедиться, что списке провайдеров искомый первый - Пкркйти в ЖКХ
    @Test(dependsOnMethods = "checkCategoryList")
    public void checkZHKUMoscowPage(){
        pages.zhkuMoscowPage.payHAUMoscowClick();//перейти на страницу "Оплатить ЖКУ в Москве"
        pages.zhkuMoscowPayPage.payButtonClick();//убедиться, что страница та же, что в шаге 5
        pages.zhkuMoscowPayPage.paymentTabClick();//Переход в Платежи
        pages.paymentsPage.HAUClick();//ЖКХ
    }

    /**
     *   Step 13,14 - Выбрать регион г.Санкт-Петербург, и проверить, что там нет поставщиков услуг другого города
     */
    @Test(dependsOnMethods = "checkZHKUMoscowPage")
    public void setCitySPB(){
        pages.communalPaymentsPage.selectRegion(data.spbAssert,data.spbSet);//Выбираем регион Санкт-Петербург
        pages.paymentsPage.searchReceiverInputText(testCommunalService);// Поиск ЖКУ-Москва в Питере
        Assert.assertEquals(pages.paymentsPage.searchResultText(),data.nullSearchResult);// Проверка, что в Питере не показывт комм.службу Москвы
    }
}
