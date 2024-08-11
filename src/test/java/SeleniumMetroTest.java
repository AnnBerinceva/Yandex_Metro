import driver.WebDriverCreator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.MetroHomePage;

public class SeleniumMetroTest {
    WebDriverCreator webDriverCreator = new WebDriverCreator();
    private WebDriver driver;
    private MetroHomePage metroPage;

    private static final String CITY_SAINTP = "Санкт-Петербург";
    private static final String STATION_SPORTIVNAYA = "Спортивная";
    private static final String STATION_LUBYANKA = "Лубянка";
    private static final String STATION_KRASNOGVARD = "Красногвардейская";


    @Before
    public void setUp() {
        driver = webDriverCreator.createChromeDriver();
        driver = new ChromeDriver();
        driver.get("https://qa-metro.stand-2.praktikum-services.ru/");
        metroPage = new MetroHomePage(driver);
        metroPage.waitForLoadHomePage();
    }

    @Test
    public void checkChooseCityDropdown() {
        metroPage.chooseCity(CITY_SAINTP);
        metroPage.waitForStationVisibility(STATION_SPORTIVNAYA);
    }

    @Test
    public void checkRouteApproxTimeIsDisplayed() {
         metroPage.buildRoute(STATION_LUBYANKA, STATION_KRASNOGVARD);
        Assert.assertEquals("≈ 36 мин.", metroPage.getApproximateRouteTime(0));
    }

    @Test
    public void checkRouteStationFromIsCorrect() {
        metroPage.buildRoute(STATION_LUBYANKA, STATION_KRASNOGVARD);
        Assert.assertEquals(STATION_LUBYANKA, metroPage.getRouteStationFrom());

    }

    @Test
    public void checkRouteStationToIsCorrect() {
        metroPage.buildRoute(STATION_LUBYANKA, STATION_KRASNOGVARD);
        Assert.assertEquals(STATION_KRASNOGVARD, metroPage.getRouteStationTo());

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}