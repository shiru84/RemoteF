package OTHER.D2SIBR_538;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class D2SIBR_538 {
    public String linkQA;
    public String linkPreQA;
    public String dataXLS;
    public String dataXLS2;
    static ChromeDriver driver;
    static WebDriverWait wait;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static WebElement expectedTATest;
    public int column = 2;

    public int row = 0;
    public int wb = 0;
    static String XLS = System.getProperty("user.dir")+ "/src/test/java/OTHER/D2SIBR_538/testfile.xlsx";


    void Setup() {
        //chrome.exe --remote-debugging-port=9222 --user-data-dir="C:\selenium\ChromeProfile"
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.samsung.com/uk/business");
        driver.findElement(By.id("truste-consent-button")).click();


    }

    void getTestData () throws IOException, InterruptedException {
        workbook = new XSSFWorkbook(XLS);
        sheet = workbook.getSheetAt(wb);
        linkPreQA = sheet.getRow(row).getCell(column).getStringCellValue();
        linkQA = sheet.getRow(row).getCell(column+1).getStringCellValue();
        dataXLS = sheet.getRow(row).getCell(column-2).getStringCellValue();
        dataXLS2 = sheet.getRow(row).getCell(column+2).getStringCellValue();
        Thread.sleep(1000);
    }

    void findTACopy()  {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        expectedTATest = driver.findElement(By.xpath(dataXLS));
        System.out.println(expectedTATest.getText());


    }
    void clickBuyNow() throws IOException, InterruptedException {
//        getTestData();
//        System.out.println(dataXLS2);
        driver.findElement(By.xpath(dataXLS2)).click();
    }

    void writeFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(XLS);
        workbook.write(fos);
        fos.close();
        workbook.close();
        System.out.println("File Writen");

    }
    void writeResult (String v, Integer r, Integer c) throws IOException {
        FileInputStream fis = new FileInputStream(XLS);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheetAt(wb);
        Row row = sheet.getRow(r);
        row.createCell(c).setCellValue(v);
        writeFile();
    }
    void test() throws IOException, InterruptedException {
        row=row+1;
        Setup();
        getTestData();
        driver.get(linkPreQA);
        Thread.sleep(2000);
        driver.get(linkQA);
        Thread.sleep(2000);
        try {findTACopy();
            Assert.assertTrue(true, "AVAILABLE");
            clickBuyNow();
            writeResult(expectedTATest.getText(), row, column+3);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            String clipboardContent = null;
            try {
                clipboardContent = (String) clipboard.getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException e) {
                throw new RuntimeException(e);
            }
            System.out.println(clipboardContent);
            writeResult(clipboardContent, row, column+4);
            takeScreenshot(row);
            closeBrowser();

        }catch (org.openqa.selenium.NoSuchElementException e){
            takeScreenshot(row);
            System.out.println("NOT FOUND");
            writeResult("FAIL", row, column+3);
            closeBrowser();
            Assert.assertTrue(false, "SKU NOT FOUND");
        }

    }
    public static void takeScreenshot(Integer ROW) {
        // Cast WebDriver instance to TakesScreenshot to access screenshot functionality
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;

        // Capture the screenshot and store it as a file
        File srcFile = screenshotTaker.getScreenshotAs(OutputType.FILE);

        // Define the destination file path
        File destFile = new File( System.getProperty("user.dir")+ "/src/test/java/OTHER/D2SIBR_538/Screenshots/" + ROW + ".jpg");

        try {
            // Copy the screenshot to the specified location
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved as " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }

    @Test void test01 () throws IOException, InterruptedException {    test();}
    @Test void test02 () throws IOException, InterruptedException {    test();}
    @Test void test03 () throws IOException, InterruptedException {    test();}
    @Test void test04 () throws IOException, InterruptedException {    test();}
    @Test void test05 () throws IOException, InterruptedException {    test();}
    @Test void test06 () throws IOException, InterruptedException {    test();}
    @Test void test07 () throws IOException, InterruptedException {    test();}
    @Test void test08 () throws IOException, InterruptedException {    test();}
    @Test void test09 () throws IOException, InterruptedException {    test();}
    @Test void test10 () throws IOException, InterruptedException {    test();}
    @Test void test11 () throws IOException, InterruptedException {    test();}
    @Test void test12 () throws IOException, InterruptedException {    test();}
    @Test void test13 () throws IOException, InterruptedException {    test();}
    @Test void test14 () throws IOException, InterruptedException {    test();}
    @Test void test15 () throws IOException, InterruptedException {    test();}
    @Test void test16 () throws IOException, InterruptedException {    test();}
    @Test void test17 () throws IOException, InterruptedException {    test();}
    @Test void test18 () throws IOException, InterruptedException {    test();}
    @Test void test19 () throws IOException, InterruptedException {    test();}
    @Test void test20 () throws IOException, InterruptedException {    test();}
    @Test void test21 () throws IOException, InterruptedException {    test();}
    @Test void test22 () throws IOException, InterruptedException {    test();}
    @Test void test23 () throws IOException, InterruptedException {    test();}
    @Test void test24 () throws IOException, InterruptedException {    test();}
    @Test void test25 () throws IOException, InterruptedException {    test();}
    @Test void test26 () throws IOException, InterruptedException {    test();}
    @Test void test27 () throws IOException, InterruptedException {    test();}
    @Test void test28 () throws IOException, InterruptedException {    test();}
    @Test void test29 () throws IOException, InterruptedException {    test();}
    @Test void test30 () throws IOException, InterruptedException {    test();}
    @Test void test31 () throws IOException, InterruptedException {    test();}
    @Test void test32 () throws IOException, InterruptedException {    test();}
    @Test void test33 () throws IOException, InterruptedException {    test();}
    @Test void test34 () throws IOException, InterruptedException {    test();}
    @Test void test35 () throws IOException, InterruptedException {    test();}
    @Test void test36 () throws IOException, InterruptedException {    test();}
    @Test void test37 () throws IOException, InterruptedException {    test();}
    @Test void test38 () throws IOException, InterruptedException {    test();}
    @Test void test39 () throws IOException, InterruptedException {    test();}
    @Test void test40 () throws IOException, InterruptedException {    test();}
    @Test void test41 () throws IOException, InterruptedException {    test();}
    @Test void test42 () throws IOException, InterruptedException {    test();}
    @Test void test43 () throws IOException, InterruptedException {    test();}
    @Test void test44 () throws IOException, InterruptedException {    test();}
    @Test void test45 () throws IOException, InterruptedException {    test();}
    @Test void test46 () throws IOException, InterruptedException {    test();}
    @Test void test47 () throws IOException, InterruptedException {    test();}
    @Test void test48 () throws IOException, InterruptedException {    test();}
    @Test void test49 () throws IOException, InterruptedException {    test();}
    @Test void test50 () throws IOException, InterruptedException {    test();}
    @Test void test51 () throws IOException, InterruptedException {    test();}
    @Test void test52 () throws IOException, InterruptedException {    test();}
    @Test void test53 () throws IOException, InterruptedException {    test();}
    @Test void test54 () throws IOException, InterruptedException {    test();}
    @Test void test55 () throws IOException, InterruptedException {    test();}
    @Test void test56 () throws IOException, InterruptedException {    test();}
    @Test void test57 () throws IOException, InterruptedException {    test();}
    @Test void test58 () throws IOException, InterruptedException {    test();}
    @Test void test59 () throws IOException, InterruptedException {    test();}
    @Test void test60 () throws IOException, InterruptedException {    test();}
    @Test void test61 () throws IOException, InterruptedException {    test();}
    @Test void test62 () throws IOException, InterruptedException {    test();}
    @Test void test63 () throws IOException, InterruptedException {    test();}
    @Test void test64 () throws IOException, InterruptedException {    test();}
    @Test void test65 () throws IOException, InterruptedException {    test();}
    @Test void test66 () throws IOException, InterruptedException {    test();}
    @Test void test67 () throws IOException, InterruptedException {    test();}
    @Test void test68 () throws IOException, InterruptedException {    test();}
    @Test void test69 () throws IOException, InterruptedException {    test();}
    @Test void test70 () throws IOException, InterruptedException {    test();}
    @Test void test71 () throws IOException, InterruptedException {    test();}
    @Test void test72 () throws IOException, InterruptedException {    test();}
    @Test void test73 () throws IOException, InterruptedException {    test();}
    @Test void test74 () throws IOException, InterruptedException {    test();}
    @Test void test75 () throws IOException, InterruptedException {    test();}
    @Test void test76 () throws IOException, InterruptedException {    test();}
    @Test void test77 () throws IOException, InterruptedException {    test();}
    @Test void test78 () throws IOException, InterruptedException {    test();}
    @Test void test79 () throws IOException, InterruptedException {    test();}
    @Test void test80 () throws IOException, InterruptedException {    test();}
    @Test void test81 () throws IOException, InterruptedException {    test();}
    @Test void test82 () throws IOException, InterruptedException {    test();}
    @Test void test83 () throws IOException, InterruptedException {    test();}
    @Test void test84 () throws IOException, InterruptedException {    test();}
    @Test void test85 () throws IOException, InterruptedException {    test();}
    @Test void test86 () throws IOException, InterruptedException {    test();}
    @Test void test87 () throws IOException, InterruptedException {    test();}
    @Test void test88 () throws IOException, InterruptedException {    test();}
    @Test void test89 () throws IOException, InterruptedException {    test();}
    @Test void test90 () throws IOException, InterruptedException {    test();}
    @Test void test91 () throws IOException, InterruptedException {    test();}
    @Test void test92 () throws IOException, InterruptedException {    test();}
    @Test void test93 () throws IOException, InterruptedException {    test();}
    @Test void test94 () throws IOException, InterruptedException {    test();}
    @Test void test95 () throws IOException, InterruptedException {    test();}
    @Test void test96 () throws IOException, InterruptedException {    test();}
    @Test void test97 () throws IOException, InterruptedException {    test();}
    @Test void test98 () throws IOException, InterruptedException {    test();}
    @Test void test99 () throws IOException, InterruptedException {    test();}
    @Test void test100 () throws IOException, InterruptedException {    test();}



    void closeBrowser() {

        driver.quit();
    }

}
