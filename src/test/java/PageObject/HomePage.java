package pageobject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageYandexSamokat {
    private WebDriver driver;

    private final String HOME_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    //локатор стрелки в блоке "Вопросы о важном"
    private String arrowButton = ".//div[@id='accordion__heading-%d']";

    // локатор области с текстом в блоке "Вопросы о важном"
    private String textArea = ".//div[@id='accordion__panel-%d']//p";

    //локатор кнопки заказа самоката
    private String orderButtonLink = ".//div[contains(@class,'%s')]/button[text()='Заказать']";

    //кнопка заказа в шапке страницы
    private static String orderButtonHeader = "Header_Nav";

    //кнопка заказа в теле страницы
    private static String orderButtonBody = "Home_FinishButton__1_cWm";

    //локатор поля Имя
    private By nameField = By.xpath(".//*[contains(@placeholder,'Имя')]");

    //локатор поля Фамилия
    private By surnameField = By.xpath(".//*[contains(@placeholder,'Фамилия')]");

    //локатор поля Адрес
    private By addressField = By.xpath(".//*[contains(@placeholder,'куда привезти')]");

    //локатор поля Телефон
    private By phoneField = By.xpath(".//*[contains(@placeholder,'Телефон')]");

    //локатор поля Метро
    private By subwayStationField = By.xpath(".//*[contains(@placeholder,'метро')]");
    private String subwayStationButton = ".//div[text()='%s']";

    //локатор кнопки Далее
    private By nextButton = By.xpath(".//button[text()='Далее']");

    //локатор поля Дата доставки
    private By deliveryDateField = By.xpath(".//*[contains(@placeholder,'Когда привезти')]");

    //локатор кнопки выбора даты в календаре
    private String datePicker = ".//div[@role='button' and text()='%s']";

    //локатор поля Длительность аренды
    private By durationField = By.xpath(".//div[@class='Dropdown-root']");

    //локатор выбора длительности аренды
    private String durationPicker = ".//div[@class='Dropdown-option' and text()='%s']";

    //локатор поля Комментарий
    private By commentField = By.xpath(".//*[contains(@placeholder,'Комментарий')]");

    //локатор кнопки Заказать
    private By completeOrderButton = By.xpath(".//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");

    //локатор модального окна
    private By modalHeader = By.cssSelector(".Order_ModalHeader__3FDaJ");

    //локатор кнопки подтверждения
    private By confirmButton = By.xpath(".//button[text()='Да']");

    //локатор текста Заказ оформлен
    private By succesfulOrder = By.xpath(".//div[text()='Заказ оформлен']");

    //локатор кнопки Посмотреть статус
    private By viewOrderStatusButton = By.xpath(".//button[text()='Посмотреть статус']");

    private By scooterLogo = By.xpath(".//img[@alt='Scooter']");


    //локатор кнопки куки
    private By cookieButton = By.className("App_CookieButton__3cvqF");

    public String getHOME_PAGE_URL() {
        return HOME_PAGE_URL;
    }
    public static String getOrderButtonHeader() {return orderButtonHeader; }
    public static String getOrderButtonBody() {return orderButtonBody; }


    //конструктор класса
    public HomePageYandexSamokat(WebDriver driver){
        this.driver = driver;
    }

    //метод, скрывающий баннер про куки
    public void hideCookieBanner() {
        driver.findElement(cookieButton).click();
    }

    public void findAndClickArrowButton(int faqIndex) {
        WebElement element = driver.findElement(By.xpath(String.format((arrowButton),faqIndex)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public String getFaqText(int faqIndex) {
        return driver.findElement(By.xpath(String.format(textArea,faqIndex))).getText();
    }


    public void locateAndClickOrderButton(String orderButtonClass) {
        String orderButton = String.format(orderButtonLink,orderButtonClass);
        Assert.assertTrue(driver.findElement(By.xpath(orderButton)).isEnabled());
        driver.findElement(By.xpath(orderButton)).click();
    }


    public void fillUserData(String name, String surname, String address, String subwayStationName, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(subwayStationField).click();
        driver.findElement(By.xpath(String.format(subwayStationButton,subwayStationName))).click();
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void fillDeliveryDetails(String day, String duration, String colour, String comment) {
        driver.findElement(deliveryDateField).click();
        driver.findElement(By.xpath(String.format(datePicker, day))).click();
        driver.findElement(durationField).click();
        driver.findElement(By.xpath(String.format(durationPicker, duration))).click();
        driver.findElement(By.id(colour)).click();
        driver.findElement(commentField).sendKeys(comment);
        driver.findElement(completeOrderButton).click();
    }


    public void checkConfimationModalFormIsVisible() {
        Assert.assertTrue(driver.findElement(modalHeader).isDisplayed());
        driver.findElement(confirmButton).click();
    }


    public void checkSuccesfulOrderFormIsVisible() {
        Assert.assertTrue(driver.findElement(succesfulOrder).isDisplayed());
        driver.findElement(viewOrderStatusButton).click();
    }

    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

}
