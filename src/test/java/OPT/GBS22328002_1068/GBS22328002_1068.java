package OPT.GBS22328002_1068;

import org.apache.commons.io.FileUtils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class GBS22328002_1068 {
    public String dataXLS;
    static ChromeDriver driver;
    static WebDriverWait wait;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static WebElement expectedTATest;
    public int columnPreQA = 2;
    public int columnQA = 3;
    public int ROW = 1;
    public int wb = 0;
    static String XLS = System.getProperty("user.dir")+ "/src/test/java/OPT/GBS22328002_1068/testfile.xlsx";


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
        dataXLS = sheet.getRow(r).getCell(c).getStringCellValue();
        Thread.sleep(1000);
    }

    void findTACopy(Integer ROW, Integer cP) throws IOException, InterruptedException {
        getTestData(ROW, cP);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        expectedTATest = driver.findElement(By.xpath(dataXLS));
        Actions actions = new Actions (driver);
        actions.moveToElement(expectedTATest);
        actions.perform();
        System.out.println(expectedTATest.getText());

    }
    void clickBuyNow(Integer ROW, Integer cP) throws IOException, InterruptedException {
        getTestData(ROW, cP);
        System.out.println(dataXLS);
        driver.findElement(By.xpath(dataXLS)).click();
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
        writeFile();
    }
    void test(Integer ROW, Integer cP, Integer cQA) throws IOException, InterruptedException {
        Setup();
        getTestData(ROW, cP);
        driver.get(dataXLS);
//        clickBuyNow(0, cP);
        getTestData(ROW,cQA);
        driver.get(dataXLS);
        findTACopy(ROW,0);
        writeResult(ROW, cP+3);
        takeScreenshot(ROW);
        closeBrowser();
    }
    public static void takeScreenshot(Integer ROW) {
        // Cast WebDriver instance to TakesScreenshot to access screenshot functionality
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;

        // Capture the screenshot and store it as a file
        File srcFile = screenshotTaker.getScreenshotAs(OutputType.FILE);

        // Define the destination file path
        File destFile = new File( System.getProperty("user.dir")+ "/src/test/java/OPT/GBS22328002_1068/Screenshots/" + ROW + ".jpg");

        try {
            // Copy the screenshot to the specified location
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved as " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
//    @Test
//    void test00 () throws IOException, InterruptedException {
//        test(2, 5, 3);
//    }
    @Test
    void test01 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);
    }
    @Test
    void test02 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }
    @Test
    void test03 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }
    @Test
    void test04 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }
    @Test
    void test05 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }
    @Test
    void test06 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }
    @Test
    void test07 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }




    void closeBrowser() {

        driver.quit();
    }

}
