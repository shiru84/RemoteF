package OPT.GBS22328002_1026;

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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class GBS22328002_1026_testQAlinks {
    public String URL;
    static ChromeDriver driver;
    static WebDriverWait wait;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static WebElement expectedTATest;
    public int columnQA = 4;
    public int ROW = 0;
    public int wb = 0;
    static String XLS = System.getProperty("user.dir")+ "/src/test/java/OPT/GBS22328002_1026/testfile.xlsx";


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

        void findTA(){
URL = driver.findElement(By.linkText("Price Promise")).getText();
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
        row.createCell(c).setCellValue(URL);
        writeFile();
    }
    public static void takeScreenshot(Integer row) {
        // Cast WebDriver instance to TakesScreenshot to access screenshot functionality
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;

        // Capture the screenshot and store it as a file
        File srcFile = screenshotTaker.getScreenshotAs(OutputType.FILE);

        // Define the destination file path
        File destFile = new File( System.getProperty("user.dir")+ "/src/test/java/OPT/GBS22328002_1026/Screenshots/" + row + ".jpg");

        try {
            // Copy the screenshot to the specified location
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved as " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
    void test(Integer row, Integer cQA) throws IOException, InterruptedException {
        Setup();
        getTestData(row, cQA);
        driver.get(URL);
        findTA();
        writeResult(row, cQA+1);
        takeScreenshot(row);
        closeBrowser();
    }

//    @Test
//    void test01 () throws IOException, InterruptedException {
//        row=row+1;
//        test( row,1);
//    }
@Test void test001 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test002 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test003 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test004 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test005 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test006 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test007 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test008 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test009 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test010 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test011 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test012 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test013 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test014 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test015 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test016 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test017 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test018 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test019 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test020 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test021 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test022 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test023 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test024 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test025 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test026 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}
    @Test void test027 () throws IOException, InterruptedException { ROW=ROW+1; test( ROW, columnQA);}



//    @AfterSuite
    void closeBrowser() {

        driver.quit();
    }

}
