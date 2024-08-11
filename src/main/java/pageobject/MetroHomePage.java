package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MetroHomePage {

    private final WebDriver driver;
    public By selectCityButton = By.className("select_metro__button");
    public By fieldFrom = By.xpath(".//input[@placeholder='Откуда']");
    public By fieldTo = By.xpath(".//input[@placeholder='Куда']");
    public By routeStationFromTo = By.className("route-details-block__terminal-station");

    public MetroHomePage(WebDriver driver){ this.driver = driver;  }

    public void waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Театральная']")));
    }

    public void chooseCity(String cityName){
        driver.findElement(selectCityButton).click();
        driver.findElement(By.xpath(String.format("//*[text()='%s']", cityName))).click();
    }
    public void setStationFrom(String stationFrom){
        driver.findElement(fieldFrom).sendKeys(stationFrom, Keys.DOWN, Keys.ENTER);
    }

    public void setStationTo(String stationTo){
        driver.findElement(fieldTo).sendKeys(stationTo, Keys.DOWN, Keys.ENTER);
    }

    public void waitForLoadRoute(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Получить ссылку на маршрут']")));
    }

    public void buildRoute(String stationFrom, String stationTo){
        setStationFrom(stationFrom);
        setStationTo(stationTo);
        waitForLoadRoute();
    }

    public String getRouteStationFrom() {
        return driver.findElements(routeStationFromTo).get(0).getText();
    }

    public String getRouteStationTo() {
            return driver.findElements(routeStationFromTo).get(1).getText();
    }

    public String getApproximateRouteTime(int routeNumber) {
        return driver.findElements(By.className("route-list-item__time")).get(routeNumber).getText();
    }

    public void waitForStationVisibility(String stationName) {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//*[text()='%s']", stationName))));
    }
}