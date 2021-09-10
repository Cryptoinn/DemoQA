import org.checkerframework.common.value.qual.EnsuresMinLenIf;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TradePage {

    String cur_code;
    WebDriver driver;

    /**
     * PageObject constructor with currency name validation
     *
     * @param code currency name
     * @param driver active instance of the WebDriver
     */
    public TradePage(String code, WebDriver driver){
        if (driver.getCurrentUrl().contains(code)){
            this.cur_code = code;
            this.driver = driver;
        } else {
            System.out.println("Warning! Wrong currency code, check URL before calling TradePage constructor");
        }
        pageLoadWait();
    }

    /**
     * PageObject constructor with currency name validation
     *
     * @param driver active instance of the WebDriver
     */
    public TradePage(WebDriver driver){
            this.driver = driver;
            pageLoadWait();
    }

    private void pageLoadWait(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * This method will return price value from the head part of the Trade page
     *
     * @return price
     */
    public String getCurValue(){
        String value;
        WebElement valueElement = driver.findElement(By.className("showPrice"));
        value = valueElement.getText();

        return value;
    }

    /**
     * This method will check bids "Total" column on a Trade page
     *
     * @return List of values for a Bid order book
     */
    public List<String> getCurBidsTotal(){

        var bids = new ArrayList<String>();

        List<WebElement> bidsElements = driver.findElements(By.className("orderbook-progress"));

        for (WebElement bid: bidsElements) {

            String bidValue = bid.findElement(By.xpath(".//div/div/div[3]")).getText();

            bids.add(bidValue);
        }

        return bids;
    }


    /**
     * This method will check time of the last Market Trade operation from the Time column
     *
     * @return the last Market Trade time
     */
    public String getLastTradeTime(){

        WebElement lastTradeElement = driver.findElement(By.className("list-item-container"));

        String lastTradeTime = lastTradeElement.findElement(By.xpath(".//div/div[3]")).getText();

        return lastTradeTime;
    }


}
