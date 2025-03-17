package CUG.D2SIBR_550;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

//48 tests

public class D2SIBR_550PDP {
static WebDriver driver;
WebDriverWait wait;
static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static String URL;
    static JavascriptExecutor js;
    static String COPY;
    static String SKU;
    static String BUY;
    static int wb =1;
    static int r =0;
    static int c =1;
    static String XLSXpath = System.getProperty("user.dir")+"/src/test/java/CUG/D2SIBR_550/CUGtestfilePDP.xlsx";
    public WebElement copyHolder;
    public static String positionOne = "//*[@id=\"product\"]/div/div[2]/div[2]/ul/li/div/div[2]/div[2]/div[1]";
//            "//*[@id=\"product\"]/div/div[2]/div[2]/ul/li/div/div[2]/div[2]/div[1]/div[1]";
//            "/html/body/div[1]/section[3]/div/div[2]/div[4]/div[1]/div/div[2]/div[2]/ul/li/div/div[2]/div[2]/div[1]";
    public static String positionTwo = "//*[@id=\"product\"]/div/div[2]/div[2]/ul/li/div/div[2]/div[2]/div[1]/div[2]";

//        "/html/body/div[1]/section[3]/div/div[2]/div[4]/div[1]/div/div[2]/div[2]/ul/li/div/div[2]/div[2]/div[1]/div[2]/p/span";

    @BeforeTest
    void Setup() {
        //C:\Program Files\Google\Chrome\Application
        //chrome.exe --remote-debugging-port=9222 --user-data-dir="C:\Users\mykola.r\IdeaProjects\RmoteF\src\test\resources\ChromeProfile"
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.samsung.com/uk/business");
        driver.findElement(By.id("truste-consent-button")).click();

    }
void getFromExcell(String position) throws IOException {
        workbook = new XSSFWorkbook(XLSXpath);
        sheet = workbook.getSheetAt(wb);
        URL = sheet.getRow(r).getCell(c).getStringCellValue();
        driver.get(URL);
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"product\"]/div/div[2]/div[2]/ul/li/div/div[2]/h3/a")));
    COPY = sheet.getRow(r).getCell(c+1).getStringCellValue();
    SKU = sheet.getRow(r).getCell(c-1).getStringCellValue();
    BUY = sheet.getRow(r).getCell(c+6).getStringCellValue();
//    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"product\"]/div/div[2]/div[2]/ul/li/div/div[2]/h3/a")));
    copyHolder = driver.findElement(By.xpath(position));


}
    void writeFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(XLSXpath);
        workbook.write(fos);
        fos.close();
        workbook.close();

    }
    void writeResult (String result, Integer cValue) throws IOException {
        FileInputStream fis = new FileInputStream(XLSXpath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheetAt(wb);
        Row row = sheet.getRow(r);
        row.createCell(cValue).setCellValue(result);
        writeFile();

    }
    public static void takeScreenshot(Integer ROW) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Cast WebDriver instance to TakesScreenshot to access screenshot functionality
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;

        // Capture the screenshot and store it as a file
        File srcFile = screenshotTaker.getScreenshotAs(OutputType.FILE);

        // Define the destination file path
        File destFile = new File( System.getProperty("user.dir")+"/src/test/java/CUG/D2SIBR_550/ScreenshotsPDPCE3/" + ROW + ".jpg");

        try {
            // Copy the screenshot to the specified location
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved as " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
    public static void scroll(){
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, window.innerHeight / 2);");}
    public void TEST() throws IOException {
        r = r + 1;
        getFromExcell(positionOne);
        WebElement actualSKU = driver.findElement(By.xpath("//*[@id=\"product\"]/div/div[2]/div[2]/ul/li/div/div[2]/div[1]"));

        if (actualSKU.getText().contains(SKU)) {
            Assert.assertTrue(true, "SAME SKU");
            System.out.println("SAME SKU FOUND!");
            System.out.println(copyHolder.getText());
            System.out.println(COPY);
        try {driver.findElement(By.xpath("//*[@id=\"product\"]/div/div[2]/div[2]/ul/li/div/div[2]/div[2]/div[2]/a[2]")).isDisplayed();
            Assert.assertTrue(true, "AVAILABLE");
            System.out.println("IS FOR SALE");
            driver.findElement(By.xpath("//*[@id=\"product\"]/div/div[2]/div[2]/ul/li/div/div[2]/div[2]/div[2]/a[2]")).click();
            String URL= driver.getCurrentUrl();
            driver.get(URL+BUY);

        }catch (org.openqa.selenium.NoSuchElementException e){
            System.out.println("NOT FOR SALE");
            writeResult("NOT FOR SALE", 3);
            Assert.assertTrue(false, "SKU NOT FOR SALE.");}

        if(driver.getPageSource().contains(COPY)){
            Assert.assertTrue(true, "Copy is visible");
            scroll();
            takeScreenshot(r);
                    writeResult("PASS", 3);
        } else {

            scroll();
            takeScreenshot(r);
            writeResult("FAIL", 3);
            Assert.assertTrue(false, "Page source does not contain the expected string.");


            }
        } else {
            takeScreenshot(r);
            writeResult("SKU NOT FOUD", 3);
            Assert.assertTrue(false, "SKU NOT FOUD.");
        }



//
//        }


//        try {
//            getFromExcell();
//            driver.findElement(By.xpath("//*[contains(text(),'Buy now')]")).click();
//            driver.getPageSource().contains(COPY);
////            WebElement MERCH = driver.findElement(By.xpath("//*[contains(text(),COPY)]"));
//            scroll();
//                    writeResult("PASS", 3);
//        } catch (Error error) {
//           writeResult("FAIL", 3);
//        } finally {
//            takeScreenshot(r);
//        }
    }
    @Test
    void test001() throws IOException { TEST(); }
    @Test    void test002() throws IOException { TEST();  }
    @Test    void test003() throws IOException { TEST();  }
    @Test    void test004() throws IOException { TEST();  }
    @Test    void test005() throws IOException { TEST();  }
    @Test    void test006() throws IOException { TEST();  }
    @Test    void test007() throws IOException { TEST();  }
    @Test    void test008() throws IOException { TEST();  }
    @Test    void test009() throws IOException { TEST();  }
    @Test    void test010() throws IOException { TEST();  }
    @Test    void test011() throws IOException { TEST();  }
    @Test    void test012() throws IOException { TEST();  }
    @Test    void test013() throws IOException { TEST();  }
    @Test    void test014() throws IOException { TEST();  }
    @Test    void test015() throws IOException { TEST();  }
    @Test    void test016() throws IOException { TEST();  }
    @Test    void test017() throws IOException { TEST();  }
    @Test    void test018() throws IOException { TEST();  }
    @Test    void test019() throws IOException { TEST();  }
    @Test    void test020() throws IOException { TEST();  }
    @Test    void test021() throws IOException { TEST();  }
    @Test    void test022() throws IOException { TEST();  }
    @Test    void test023() throws IOException { TEST();  }
    @Test    void test024() throws IOException { TEST();  }
    @Test    void test025() throws IOException { TEST();  }
    @Test    void test026() throws IOException { TEST();  }
    @Test    void test027() throws IOException { TEST();  }
    @Test    void test028() throws IOException { TEST();  }
    @Test    void test029() throws IOException { TEST();  }
    @Test    void test030() throws IOException { TEST();  }
    @Test    void test031() throws IOException { TEST();  }
    @Test    void test032() throws IOException { TEST();  }
    @Test    void test033() throws IOException { TEST();  }
    @Test    void test034() throws IOException { TEST();  }
    @Test    void test035() throws IOException { TEST();  }
    @Test    void test036() throws IOException { TEST();  }
    @Test    void test037() throws IOException { TEST();  }
    @Test    void test038() throws IOException { TEST();  }
    @Test    void test039() throws IOException { TEST();  }
    @Test    void test040() throws IOException { TEST();  }
    @Test    void test041() throws IOException { TEST();  }
    @Test    void test042() throws IOException { TEST();  }
    @Test    void test043() throws IOException { TEST();  }
    @Test    void test044() throws IOException { TEST();  }
    @Test    void test045() throws IOException { TEST();  }
    @Test    void test046() throws IOException { TEST();  }
    @Test    void test047() throws IOException { TEST();  }
    @Test    void test048() throws IOException { TEST();  }
    @Test    void test049() throws IOException { TEST();  }
    @Test    void test050() throws IOException { TEST();  }
    @Test    void test051() throws IOException { TEST();  }
    @Test    void test052() throws IOException { TEST();  }
    @Test    void test053() throws IOException { TEST();  }
    @Test    void test054() throws IOException { TEST();  }
    @Test    void test055() throws IOException { TEST();  }
    @Test    void test056() throws IOException { TEST();  }
    @Test    void test057() throws IOException { TEST();  }
    @Test    void test058() throws IOException { TEST();  }
    @Test    void test059() throws IOException { TEST();  }
    @Test    void test060() throws IOException { TEST();  }
    @Test    void test061() throws IOException { TEST();  }
    @Test    void test062() throws IOException { TEST();  }
    @Test    void test063() throws IOException { TEST();  }
    @Test    void test064() throws IOException { TEST();  }
    @Test    void test065() throws IOException { TEST();  }
    @Test    void test066() throws IOException { TEST();  }
    @Test    void test067() throws IOException { TEST();  }
    @Test    void test068() throws IOException { TEST();  }
    @Test    void test069() throws IOException { TEST();  }
    @Test    void test070() throws IOException { TEST();  }
    @Test    void test071() throws IOException { TEST();  }
    @Test    void test072() throws IOException { TEST();  }
    @Test    void test073() throws IOException { TEST();  }
    @Test    void test074() throws IOException { TEST();  }
    @Test    void test075() throws IOException { TEST();  }
    @Test    void test076() throws IOException { TEST();  }
    @Test    void test077() throws IOException { TEST();  }
    @Test    void test078() throws IOException { TEST();  }
    @Test    void test079() throws IOException { TEST();  }
    @Test    void test080() throws IOException { TEST();  }
    @Test    void test081() throws IOException { TEST();  }
    @Test    void test082() throws IOException { TEST();  }
    @Test    void test083() throws IOException { TEST();  }
    @Test    void test084() throws IOException { TEST();  }
    @Test    void test085() throws IOException { TEST();  }
    @Test    void test086() throws IOException { TEST();  }
    @Test    void test087() throws IOException { TEST();  }
    @Test    void test088() throws IOException { TEST();  }
    @Test    void test089() throws IOException { TEST();  }
    @Test    void test090() throws IOException { TEST();  }
    @Test    void test091() throws IOException { TEST();  }
    @Test    void test092() throws IOException { TEST();  }
    @Test    void test093() throws IOException { TEST();  }
    @Test    void test094() throws IOException { TEST();  }
    @Test    void test095() throws IOException { TEST();  }
    @Test    void test096() throws IOException { TEST();  }
    @Test    void test097() throws IOException { TEST();  }
    @Test    void test098() throws IOException { TEST();  }
    @Test    void test099() throws IOException { TEST();  }
    @Test    void test100() throws IOException { TEST();  }
    @Test
    void test101() throws IOException {
        TEST();

    }
    @Test    void test102() throws IOException { TEST();  }
    @Test    void test103() throws IOException { TEST();  }
    @Test    void test104() throws IOException { TEST();  }
    @Test    void test105() throws IOException { TEST();  }
    @Test    void test106() throws IOException { TEST();  }
    @Test    void test107() throws IOException { TEST();  }
    @Test    void test108() throws IOException { TEST();  }
    @Test    void test109() throws IOException { TEST();  }
    @Test    void test110() throws IOException { TEST();  }
    @Test    void test111() throws IOException { TEST();  }
    @Test    void test112() throws IOException { TEST();  }
    @Test    void test113() throws IOException { TEST();  }
    @Test    void test114() throws IOException { TEST();  }
    @Test    void test115() throws IOException { TEST();  }
    @Test    void test116() throws IOException { TEST();  }
    @Test    void test117() throws IOException { TEST();  }
    @Test    void test118() throws IOException { TEST();  }
    @Test    void test119() throws IOException { TEST();  }
//    @Test    void test120() throws IOException { TEST();  }
//    @Test    void test121() throws IOException { TEST();  }
//    @Test    void test122() throws IOException { TEST();  }
//    @Test    void test123() throws IOException { TEST();  }
//    @Test    void test124() throws IOException { TEST();  }
//    @Test    void test125() throws IOException { TEST();  }
//    @Test    void test126() throws IOException { TEST();  }
//    @Test    void test127() throws IOException { TEST();  }
//    @Test    void test128() throws IOException { TEST();  }
//    @Test    void test129() throws IOException { TEST();  }
//    @Test    void test130() throws IOException { TEST();  }
//    @Test    void test131() throws IOException { TEST();  }
//    @Test    void test132() throws IOException { TEST();  }
//    @Test    void test133() throws IOException { TEST();  }
//    @Test    void test134() throws IOException { TEST();  }
//    @Test    void test135() throws IOException { TEST();  }
//    @Test    void test136() throws IOException { TEST();  }
//    @Test    void test137() throws IOException { TEST();  }
//    @Test    void test138() throws IOException { TEST();  }
//    @Test    void test139() throws IOException { TEST();  }
//    @Test    void test140() throws IOException { TEST();  }
//    @Test    void test141() throws IOException { TEST();  }
//    @Test    void test142() throws IOException { TEST();  }
//    @Test    void test143() throws IOException { TEST();  }
//    @Test    void test144() throws IOException { TEST();  }
//    @Test    void test145() throws IOException { TEST();  }
//    @Test    void test146() throws IOException { TEST();  }
//    @Test    void test147() throws IOException { TEST();  }
//    @Test    void test148() throws IOException { TEST();  }
//    @Test    void test149() throws IOException { TEST();  }
//    @Test    void test150() throws IOException { TEST();  }
//    @Test    void test151() throws IOException { TEST();  }
//    @Test    void test152() throws IOException { TEST();  }
//    @Test    void test153() throws IOException { TEST();  }
//    @Test    void test154() throws IOException { TEST();  }
//    @Test    void test155() throws IOException { TEST();  }
//    @Test    void test156() throws IOException { TEST();  }

    @AfterSuite
    void closeBrowser() {

        driver.quit();
    }


}

