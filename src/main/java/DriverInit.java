import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class DriverInit {

    WebDriver driver;

    private DriverInit(String browserName){
        switch (browserName) {
            case "Firefox":
                System.setProperty("webdriver.gecko.driver", "C:\\Java\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\Java\\chromedriver.exe");
                driver = new ChromeDriver();
                break;

        }

    }

    public static DriverInit firefox(String url){

        var browser = new DriverInit("Firefox");

        browser.driver.get(url);

        return browser;
    }

    public static DriverInit chrome(String url){

        var browser = new DriverInit("Chrome");

        browser.driver.get(url);

        return browser;
    }
}
