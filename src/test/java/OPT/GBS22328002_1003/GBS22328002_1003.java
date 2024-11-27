package OPT.GBS22328002_1003;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class GBS22328002_1003 {
    public String URL;
    static ChromeDriver driver;
    static WebDriverWait wait;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static WebElement expectedTATest;

public int row = 0;
    public int wb = 0;
    static String XLS = System.getProperty("user.dir")+ "/src/test/java/OPT/GBS22328002_1003/testfile.xlsx";

    @BeforeSuite
    void Setup() {
        //chrome.exe --remote-debugging-port=9222 --user-data-dir="C:\selenium\ChromeProfile"
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.samsung.com/uk");
        driver.findElement(By.id("truste-consent-button")).click();


    }

    void getTestData (Integer r, Integer c) throws IOException, InterruptedException {
        workbook = new XSSFWorkbook(XLS);
        sheet = workbook.getSheetAt(wb);
        URL = sheet.getRow(r).getCell(c).getStringCellValue();
        Thread.sleep(1000);
    }

        void clickBuyNow() throws IOException, InterruptedException {

//        driver.findElement(By.cssSelector("#product > div > div.result__panel > div.result-list > ul > li > div > div.result-item__content > div.hide-mobile > div.result-cta > a.cta.cta--contained.cta--black.js-buy-now")).click();
            driver.findElement(By.linkText("Buy now")).click();
        URL = driver.getCurrentUrl();

    } //*[@id="product"]/div/div[2]/div[2]/ul/li/div/div[2]/div[2]/div[2]/a[1]

    void writeFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(XLS);
        workbook.write(fos);
        fos.close();
        workbook.close();
        System.out.println("File Writen");

    }
    void writeResult (Integer r, Integer c) throws IOException {
        FileInputStream fis = new FileInputStream(XLS);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheetAt(wb);
        Row row = sheet.getRow(r);
        row.createCell(c).setCellValue(URL);
        writeFile();
    }
    void test(Integer rQA, Integer cQA) throws IOException, InterruptedException {
        getTestData(rQA, cQA);
        driver.get(URL);
        clickBuyNow();
        writeResult(rQA, cQA+1);
    }

//    @Test
//    void test01 () throws IOException, InterruptedException {
//        row=row+1;
//        test( row,1);
//    }
    @Test void test01 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test02 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test03 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test04 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test05 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test06 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test07 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test08 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test09 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test10 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test11 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test12 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test13 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test14 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test15 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test16 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test17 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test18 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test19 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test20 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test21 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test22 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test23 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test24 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test25 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test26 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test27 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test28 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test29 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test30 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test31 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test32 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test33 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test34 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test35 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test36 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test37 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test38 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test39 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test40 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test41 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test42 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test43 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test44 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test45 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test46 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test47 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test48 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test49 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test50 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test51 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test52 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test53 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test54 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test55 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test56 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test57 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test58 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test59 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test60 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test61 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test62 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test63 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test64 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test65 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test66 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test67 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test68 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test69 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test70 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test71 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test72 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test73 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test74 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test75 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test76 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test77 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test78 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test79 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test80 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test81 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test82 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}
    @Test void test83 () throws IOException, InterruptedException {    row=row+1;    test( row, 1);}





    @AfterSuite
    void closeBrowser() {

        driver.quit();
    }

}
