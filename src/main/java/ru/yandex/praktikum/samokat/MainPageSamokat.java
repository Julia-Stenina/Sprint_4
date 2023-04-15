package ru.yandex.praktikum.samokat;

import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageSamokat {

  private final WebDriver driver;

  // Кнопки "Заказать"
  private final By orderButtons = By.xpath(".//button[text()='Заказать']");

  // Кнопка согласия на сбор куки
  private final By agreeCookieButton = By.xpath(".//button[text()='да все привыкли']");

  // Список вопросов
  private final By questions = By.xpath(".//div[@data-accordion-component='AccordionItemHeading']");

  // Список ответов
  private final By answers = By.xpath(".//div[@data-accordion-component='AccordionItemPanel']");

  public MainPageSamokat(WebDriver driver) {
    this.driver = driver;
  }

  // Скролл до элемента
  private void scrollToElement(WebElement element) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
  }

  // Метод, выполняющий скролл до вопроса, проверяющий его доступность и совершающий клик по нему
  public void clickOnQuestionByIndex(int index) {
    WebElement element = driver.findElements(questions).get(index);
    scrollToElement(element);
    new WebDriverWait(driver, Duration.ofSeconds(5)).until(
            ExpectedConditions.elementToBeClickable(element));
    element.click();
  }

  // Метод проверки соответствия открывшегося ответа
  public void checkAnswerInFAQ(int index, String expected){
    WebElement element = driver.findElements(answers).get(index);
    new WebDriverWait(driver, Duration.ofSeconds(5)).until(
            ExpectedConditions.visibilityOf(element));
    Assert.assertEquals(expected, element.getText());
    new WebDriverWait(driver, Duration.ofSeconds(5)).until(
        ExpectedConditions.textToBePresentInElement(element, expected));
  }

  // Метод согласия на сбор куки
  public void clickAgreeCookieButton(){
    WebElement element = driver.findElement(agreeCookieButton);

    if (element.isDisplayed()) {
      element.click();
    }
  }

  // Метод клика на кнопку "Заказать"
  public void clickOrderButton(int index){
    WebElement element = driver.findElements(orderButtons).get(index);
    scrollToElement(element);
    new WebDriverWait(driver, Duration.ofSeconds(5)).until(
        ExpectedConditions.visibilityOf(element));
    element.click();
  }
}

