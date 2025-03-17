package OTHER.OTHERMaster;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class OTHERMasterDRAFT {
    public String dataXLS;
    static ChromeDriver driver;
    static WebDriverWait wait;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static WebElement expectedTATest;
    static WebElement CTA;
    static JavascriptExecutor js;
    public String expectedTATestLocator = "/html/body/div[1]/div[3]/div/div/div[3]/div/div[4]/section/div/div[2]/div[2]/div[1]/div[1]/div/div/div[2]/div[3]/div[7]/button[2]";
    public int columnPreQA = 2;
    public int columnQA = 3;
    public int ROW = 1;
    public int wb = 0;
    static String XLS = System.getProperty("user.dir")+ "/src/test/java/OTHER/OTHERMaster/testfile.xlsx";

    @BeforeSuite
    void Setup() {
        //C:\Program Files\Google\Chrome\Application
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
        dataXLS = sheet.getRow(r).getCell(c).getStringCellValue();
        Thread.sleep(1000);
    }

    void findCardCopy(Integer rP, Integer cP) throws IOException, InterruptedException {
        getTestData(rP, cP);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println(dataXLS);
        expectedTATest = driver.findElement(By.xpath(dataXLS));
        scroll(expectedTATest);
        System.out.println(expectedTATest.getText());

    }
    void clickCTA(Integer rP, Integer cP) throws IOException, InterruptedException {
        getTestData(rP, cP+2);
        System.out.println(dataXLS);
        CTA = driver.findElement(By.xpath(dataXLS));
        scroll(CTA);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        CTA.click();

    }

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
        row.createCell(c).setCellValue(expectedTATest.getText());
//        row.createCell(c+1).setCellValue("Pass");
        writeFile();
    }
    public static void scroll(WebElement element){
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
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
        File destFile = new File( System.getProperty("user.dir")+ "/src/test/java/OPT/OPTMaster/Screenshots/" + ROW + ".jpg");

        try {
            // Copy the screenshot to the specified location
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved as " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
    void test() throws IOException, InterruptedException {
        driver.get("https://www.samsung.com/uk");
        ROW = ROW+1;
        getTestData(ROW, columnPreQA);
        System.out.println(dataXLS);
        driver.get(dataXLS);
try {
    findCardCopy(ROW, columnPreQA + 2);
    writeResult(ROW, columnPreQA + 3);
}catch (IOException e) {
    throw new RuntimeException(e);
}
        try {
            findCardCopy(ROW, columnPreQA -2);
            writeResult(ROW, columnPreQA + 4);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        takeScreenshot(ROW);
    }
    @Test
    void test00 () throws IOException, InterruptedException {
        test();
       
    }
    @Test
    void test01 () throws IOException, InterruptedException {

        test();
    }
    @Test
    void test02 () throws IOException, InterruptedException {
        test();

    }
    @Test
    void test03 () throws IOException, InterruptedException {
        test();

    }
    @Test
    void test04 () throws IOException, InterruptedException {
        test();

    }
    @Test
    void test05 () throws IOException, InterruptedException {
        test();

    }
//    @Test
//    void test06 () throws IOException, InterruptedException {
//        ROW = ROW+1;
//        test(ROW, columnPreQA, columnQA);
//
//    }
//    @Test
//    void test07 () throws IOException, InterruptedException {
//        ROW = ROW+1;
//        test(ROW, columnPreQA, columnQA);
//
//    }
//    @Test
//    void test08 () throws IOException, InterruptedException {
//        ROW = ROW+1;
//        test(ROW, columnPreQA, columnQA);
//
//    }@Test
//    void test09 () throws IOException, InterruptedException {
//        ROW = ROW+1;
//        test(ROW, columnPreQA, columnQA);
//
//    }@Test
//    void test10 () throws IOException, InterruptedException {
//        ROW = ROW+1;
//        test(ROW, columnPreQA, columnQA);
//
//    }@Test
//    void test11 () throws IOException, InterruptedException {
//        ROW = ROW+1;
//        test(ROW, columnPreQA, columnQA);
//
//    }@Test
//    void test12 () throws IOException, InterruptedException {
//        ROW = ROW+1;
//        test(ROW, columnPreQA, columnQA);
//
//    }
//    @Test
//    void test13 () throws IOException, InterruptedException {
//        ROW = ROW+1;
//        test(ROW, columnPreQA, columnQA);
//
//    }
//    @Test
//    void test14 () throws IOException, InterruptedException {
//        ROW = ROW+1;
//        test(ROW, columnPreQA, columnQA);
//
//    }
//    @Test
//    void test15 () throws IOException, InterruptedException {
//        ROW = ROW+1;
//        test(ROW, columnPreQA, columnQA);
//
//    }
//    @Test
//    void test16 () throws IOException, InterruptedException {
//        ROW = ROW+1;
//        test(ROW, columnPreQA, columnQA);
//
//    }@Test
//    void test17 () throws IOException, InterruptedException {
//        ROW = ROW+1;
//        test(ROW, columnPreQA, columnQA);
//
//    }@Test
//    void test18 () throws IOException, InterruptedException {
//        ROW = ROW+1;
//        test(ROW, columnPreQA, columnQA);
//
//    }
//    @Test
//    void test19 () throws IOException, InterruptedException {
//        ROW = ROW+1;
//        test(ROW, columnPreQA, columnQA);
//
//    }
//    @Test
//    void test20 () throws IOException, InterruptedException {
//        ROW = ROW+1;
//        test(ROW, columnPreQA, columnQA);
//
//    }




    @AfterSuite
    void closeBrowser() {

        driver.quit();
    }

}
