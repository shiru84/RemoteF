import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class sTest {
    static ChromeDriver driver = null;
    static String SKU = "NK36C7070WS/UR";
    static String ActualSKU;
    static String SearchLocator = "#component-id > div.nv00-gnb__inner-wrap > div.nv00-gnb__utility-wrap > div.nv00-gnb__utility.search > button > svg";
    static String SearchBarLocator = "gnb-search-keyword";
static String SearchCommitButton = "#wrap > section.gnb-search > div.gnb-search__contents > div.gnb-search__search-wrap > form > fieldset > div > button.gnb-search__input-btn--search > svg";
static String BuyNowCTALocator = "#product > div > div.result__panel > div.result-list > ul > li > div > div.result-item__content > div.hide-mobile > div.result-cta > a.cta.cta--contained.cta--black.js-buy-now";
static String LocatorSKU = "#content > div > div > div.pd-g-product-detail-kv.aem-GridColumn.aem-GridColumn--default--12 > div.product-detail-kv > div.product-detail-kv__buying-tool > section > div.pd-buying-tool__info > div > p > span";
    @BeforeSuite
    void setUp(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://samsung.com/uk");
        driver.findElement(By.id("truste-consent-button")).click();
    }
    public static void FindSKU (){
        driver.findElement(By.cssSelector(SearchLocator)).click();try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.id(SearchBarLocator)).click();
        driver.findElement(By.id(SearchBarLocator)).sendKeys(SKU);
        driver.findElement(By.cssSelector(SearchCommitButton)).click();
        driver.findElement(By.cssSelector(BuyNowCTALocator)).click();
        ActualSKU = driver.findElement(By.cssSelector(LocatorSKU)).getText();
    }
    @Test
    void test_001(){
        driver.get("http://samsung.com/uk");
    }
    @Test
    void test_002(){
        FindSKU();
        Assert.assertEquals(SKU,ActualSKU);

    }
    @AfterSuite
    void close(){
        driver.close();
    }
}
