import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktikum.samokat.AboutRentPageSamokat;
import ru.yandex.praktikum.samokat.MainPageSamokat;
import ru.yandex.praktikum.samokat.OrderPageSamokat;

@RunWith(Parameterized.class)
public class OrderSamokatTest {

    private WebDriver driver;

    private final int index;

    private final String nameValue;

    private final String surnameValue;

    private final String addressValue;

    private final String metroValue;

    private final String numberValue;

    private final String dateValue;

    private final String periodValue;

    private final String colorValue;

    private final String commentValue;

    public OrderSamokatTest(int index, String nameValue, String surnameValue, String addressValue,
        String metroValue, String numberValue, String dateValue, String periodValue,
        String colorValue, String commentValue) {
        this.index = index;
        this.nameValue = nameValue;
        this.surnameValue = surnameValue;
        this.addressValue = addressValue;
        this.metroValue = metroValue;
        this.numberValue = numberValue;
        this.dateValue = dateValue;
        this.periodValue = periodValue;
        this.colorValue = colorValue;
        this.commentValue = commentValue;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
            {0, "Ксения", "Иванова", "г. Москва, улица Алексея Дикого, 9", "Новогиреево",
                "89751452839", "23.04.2022", "двое суток", "серая безысходность",
                "Позвонить перед доставкой"},
            {0, "Артем", "Степанов", "г. Москва, Большая Новодмитровская улица, 14", "Савёловская",
                "89657894365", "19.04.2022", "четверо суток", "чёрный жемчуг", ""},
            {1, "Сергей", "Дмитриев", "г. Москва", "Тверская", "89657894365", "13.04.2022",
                "семеро суток", "серая безысходность", "Буду у станции метро в серой куртке"},
            {1, "Марина", "Леонова", "г. Москва, Льва Толстого, 16", "Парк культуры", "89874623488",
                "28.04.2022", "сутки", "чёрный жемчуг", "БЦ Красная роза, 2 подьъезд"},
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
    public void orderSamokat() {
        MainPageSamokat objMainPage = new MainPageSamokat(driver);
        objMainPage.clickAgreeCookieButton();
        objMainPage.clickOrderButton(index);

        OrderPageSamokat objOrderPage = new OrderPageSamokat(driver);
        objOrderPage.fillInOrderForm(nameValue, surnameValue, addressValue, metroValue,
            numberValue);

        AboutRentPageSamokat objRentPage = new AboutRentPageSamokat(driver);
        objRentPage.fillInRentForm(dateValue, periodValue, colorValue, commentValue);
        objRentPage.checkingModalWindow();
        objRentPage.checkingPlaceOrder();
    }
}
