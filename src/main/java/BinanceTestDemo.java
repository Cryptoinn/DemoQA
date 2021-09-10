import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BinanceTestDemo {

    public static void main(String[] args) throws InterruptedException {

        WebDriver firefox = DriverInit.firefox("https://www.binance.com/en/trade/ORN_USDT").driver;

        TradePage tradePage = new TradePage("ORN",firefox);

        // Test case #1 : Currency price should be updated at least every 10 second
        // Test case #2 : Order Book should be updated at least every 10 second
        var baseValue = tradePage.getCurValue();
        var baseBidsTotal = tradePage.getCurBidsTotal();
        Thread.sleep(10000);

        if (baseValue == tradePage.getCurValue()){
            System.out.println("TEST FAILED! Currency price wasn't updated");
        } else {
            System.out.println("TEST COMPLETE! Currency price was updated");
        }

        if (baseBidsTotal == tradePage.getCurBidsTotal()){
            System.out.println("TEST FAILED! Currency bids Order Book wasn't updated");
        } else {
            System.out.println("TEST COMPLETE! Currency bids Order Book was updated");
        }


        // Test case #3 : Market Trade history should be updated at least every 60 second
        // Flexible Wait
        var baseTradeTime = tradePage.getLastTradeTime();

        try{
            new WebDriverWait(firefox, Duration.ofSeconds(60)).until(
                    new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver driver) {
                            var newTradeTime = tradePage.getLastTradeTime();
                            if(!baseTradeTime.equals(newTradeTime)) {
                                System.out.println("TEST COMPLETE! The last Trade Time was updated in time");
                                return true;
                            }
                            else
                                return false;
                        }
                    });
        } catch (Exception e){
            System.out.println("TEST FAILED! The last Trade Time wasn't updated in 60 seconds");
        }






    }
}
