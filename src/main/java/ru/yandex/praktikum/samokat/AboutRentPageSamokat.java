package ru.yandex.praktikum.samokat;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AboutRentPageSamokat {

    private final WebDriver driver;

    // Заголовок "Про аренду"
    private final By rentHeader = By.xpath(".//div[text()='Про аренду']");

    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    // Поле "Срок аренды"
    private final By rentalPeriodField = By.xpath(".//div[@class='Dropdown-placeholder'][text()='* Срок аренды']");

    // Поле "Комментарий для курьера"
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Кнопки "Заказать"
    private final By orderButtons = By.xpath(".//button[text()='Заказать']");

    // Заголовок в окне с подтверждением заказа
    private final By headerInModalWindow = By.xpath(".//div[text()='Хотите оформить заказ?']");

    // Кнопка "Да" в модальном окне
    private final By yesButton = By.xpath(".//button[text()='Да']");

    // Заголовок окна "Заказ оформлен"
    private final By headerOrderPlaced = By.xpath(".//div[text()='Заказ оформлен']");

    public AboutRentPageSamokat(WebDriver driver) {
        this.driver = driver;
    }

    // Метод проверки заголовка страницы
    public void checkHeaderAboutRentIsDisplayed(){
        WebElement element = driver.findElement(rentHeader);
        Assert.assertTrue(element.isDisplayed());
    }

    // Метод выбора даты начала аренды
    public void selectStartDateOfRent(String date){
        WebElement element = driver.findElement(dateField);
        element.sendKeys(date, Keys.ENTER);
    }

    public void selectRentPeriod(String value) {
        WebElement elementField = driver.findElement(rentalPeriodField);
        elementField.click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-option'][text()='" +value+ "']")).click();
    }

    // Метод выбора цвета
    public void selectColor(String color){
        driver.findElement(By.xpath(".//label[text()='" + color + "']")).click();
    }

    // Метод заполнения комментария
    public void fillInCommentField(String commentValue){
        WebElement element = driver.findElement(comment);
        element.sendKeys(commentValue);
    }

    // Метод нажатия на кнопку "Заказать"
    public void clickOrderButton(int index){
        WebElement element = driver.findElements(orderButtons).get(index);
        element.click();
    }

    // Метод проверки окна "Хотите оформить заказ?" и нажатия кнопки "Да"
    public void checkingModalWindow() {
        WebElement element = driver.findElement(headerInModalWindow);
        Assert.assertTrue(element.isDisplayed());
        WebElement elementButton = driver.findElement(yesButton);
        elementButton.click();
    }

    // Метод проверки окна "Заказ оформлен"
    public void checkingPlaceOrder(){
        WebElement element = driver.findElement(headerOrderPlaced);
        Assert.assertTrue(element.isDisplayed());
    }

    // Метод заполнения формы "Про аренду"
    public void fillInRentForm(String dateValue, String periodValue, String colorValue, String commentValue){
        checkHeaderAboutRentIsDisplayed();
        selectStartDateOfRent(dateValue);
        selectRentPeriod(periodValue);
        selectColor(colorValue);
        fillInCommentField(commentValue);
        clickOrderButton(1);
    }
}
