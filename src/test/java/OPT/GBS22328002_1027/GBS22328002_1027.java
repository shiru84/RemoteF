package OPT.GBS22328002_1027;

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
import org.testng.Assert;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class GBS22328002_1027 {
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
    static String XLS = System.getProperty("user.dir")+ "/src/test/java/OPT/GBS22328002_1027/testfile.xlsx";


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
    void getFromClipboard(Integer r, Integer c) throws IOException{
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // Retrieve the clipboard content as a String
        String CODE = null;
        try {
            CODE = (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            throw new RuntimeException(e);
        }
        FileInputStream fis = new FileInputStream(XLS);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheetAt(wb);
        Row row = sheet.getRow(r);
        row.createCell(c).setCellValue(CODE);
        writeFile();
    }
    void test(Integer ROW, Integer cP, Integer cQA) throws IOException, InterruptedException {
        Setup();
        getTestData(ROW, cP);
        driver.get(dataXLS);
        findTACopy(ROW,0);
        writeResult(ROW, cP+3);
//        clickBuyNow(ROW, cQA+1);
//        getFromClipboard(ROW, cP+4);;
//        getTestData(ROW,cQA);
//        driver.get(dataXLS);

        takeScreenshot(ROW);
        Assert.assertTrue(driver.getPageSource().contains("Purchase from Samsung.com by 03.12.24. Apply code at checkout to redeem. £50 will be automatically deducted in checkout on orders over £500. £100 will be automatically deducted in checkout on orders over £1,000. Code cannot be used in conjunction with other voucher codes."));
        closeBrowser();
    }
    public static void takeScreenshot(Integer ROW) {
        // Cast WebDriver instance to TakesScreenshot to access screenshot functionality
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;

        // Capture the screenshot and store it as a file
        File srcFile = screenshotTaker.getScreenshotAs(OutputType.FILE);

        // Define the destination file path
        File destFile = new File( System.getProperty("user.dir")+ "/src/test/java/OPT/GBS22328002_1027/Screenshots/" + ROW + ".jpg");

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
    void test001 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);
    }
    @Test
    void test002 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }
    @Test
    void test003 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }
    @Test
    void test004 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }
    @Test
    void test005 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }
    @Test
    void test006 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }
    @Test
    void test007 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }
    @Test
    void test008 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }
    @Test
    void test009 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }
    @Test
    void test010() throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }
    @Test
    void test011 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }
    @Test
    void test012 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test( ROW, columnPreQA, columnQA);

    }




    void closeBrowser() {

        driver.quit();
    }

}
