package ru.yandex.praktikum.samokat;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPageSamokat {

    private final WebDriver driver;

    // Заголовок "Для кого самокат"
    private final By orderHeader = By.xpath(".//div[text()='Для кого самокат']");

    // Поле "Имя"
    private final By nameField = By.xpath(".//input[@placeholder=\"* Имя\"]");

    // Поле "Фамилия"
    private final By surnameField = By.xpath(".//input[@placeholder=\"* Фамилия\"]");

    // Поле "Адрес"
    private final By addressField = By.xpath(".//input[@placeholder=\"* Адрес: куда привезти заказ\"]");

    // Поле "Станция метро"
    private final By metroStationField = By.xpath(".//input[@placeholder=\"* Станция метро\"]");

    // Поле "Телефон"
    private final By phoneNumberField = By.xpath(
        ".//input[@placeholder=\"* Телефон: на него позвонит курьер\"]");

    // Кнопка "Далее"
    private final By forwardButton = By.xpath(".//button[text()='Далее']");

    public OrderPageSamokat(WebDriver driver) {
        this.driver = driver;
    }

    // Метод проверки заголовка страницы
    public void checkHeaderOrderPage(){
        WebElement element = driver.findElement(orderHeader);
        Assert.assertTrue(element.isDisplayed());
    }

    // Метод для заполнения поля "Имя"
    public void fillInTheNameField (String name) {
        WebElement element = driver.findElement(nameField);
        element.sendKeys(name);
    }

    // Метод для заполнения поля "Фамилия"
    public void fillInTheSurnameField (String surname) {
        WebElement element = driver.findElement(surnameField);
        element.sendKeys(surname);
    }

    // Метод для заполнения поля "Адрес"
    public void fillInTheAddressField (String address) {
        WebElement element = driver.findElement(addressField);
        element.sendKeys(address);
    }

    // Метод для заполнения поля "Станция метро"
    public void fillInTheMetroStationField (String value) {
        WebElement element = driver.findElement(metroStationField);
        element.click();
        driver.findElement(By.xpath(".//div[text()='"+value+"']/parent::button")).click();

    }

    // Метод для заполнения поля "Телефон"
    public void fillInThePhoneNumberField (String phoneNumber) {
        WebElement element = driver.findElement(phoneNumberField);
        element.sendKeys(phoneNumber);
    }

    // Метод нажатия на кнопку "Далее"
    public void clickForwardButton(){
        WebElement element = driver.findElement(forwardButton);
        element.click();
    }

    // Метод заполнения формы
    public void fillInOrderForm(String nameValue, String surnameValue, String addressValue,
                                String metroValue, String numberValue){
        checkHeaderOrderPage();
        fillInTheNameField(nameValue);
        fillInTheSurnameField(surnameValue);
        fillInTheAddressField(addressValue);
        fillInTheMetroStationField(metroValue);
        fillInThePhoneNumberField(numberValue);
        clickForwardButton();
    }
}
