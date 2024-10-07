import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class sTest {
    static ChromeDriver driver = null;
    @Test
    void setUp(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://samsung.com/uk");
        driver.findElement(By.id("truste-consent-button")).click();
    }
}
