import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktikum.samokat.MainPageSamokat;


@RunWith(Parameterized.class)
public class DropdownListTest {

    private WebDriver driver;

    private final int index;

    private final String expected;

    public DropdownListTest(int index, String expected) {
        this.index = index;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
            {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
            {1,
                "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, "
                    + "можете просто сделать несколько заказов — один за другим."},
            {2,
                "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. "
                    + "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. "
                    + "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
            {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
            {4,
                "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку "
                    + "по красивому номеру 1010."},
            {5,
                "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже "
                    + "если будете кататься без передышек и во сне. Зарядка не понадобится."},
            {6,
                "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не "
                    + "попросим. Все же свои."},
            {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}

        };
    }

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().window().maximize();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void checkDropdownList() {
        MainPageSamokat objMainPage = new MainPageSamokat(driver);
        objMainPage.clickAgreeCookieButton();
        objMainPage.clickOnQuestionByIndex(index);
        objMainPage.checkAnswerInFAQ(index, expected);
    }
}
