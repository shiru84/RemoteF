package CUG.GBS22328002_1054;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

//48 tests

public class GBS22328002_1054 {
WebDriver driver;
WebDriverWait wait;
static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static String dataXLS;
    static String COPY;
    static Integer workBook = 0;
    static int ROW =1;
    static int COLUMN =3;
    static String XLSXpath = System.getProperty("user.dir")+"/src/test/java/CUG/GBS22328002_1054/CUGtestfile.xlsx";
    public WebElement copyHolder;
    static WebElement expectedTATest;
    public String preURL1 = "https://www.samsung.com/uk/multistore/uk_student/";
    public String preURL2 = "https://www.samsung.com/uk/multistore/uk_networks/";
    @BeforeTest
    void Setup() {
        //C:\Program Files\Google\Chrome\Application
        //chrome.exe --remote-debugging-port=9222 --user-data-dir="C:\Users\mykola.r\IdeaProjects\RmoteF\src\test\resources\ChromeProfile"
        System.setProperty("webdriver.chrome.driver", "C:/Users/mykola.r/IdeaProjects/RmoteF/src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(preURL1);

    }
void getTestData(Integer COLUMN) throws IOException {
        workbook = new XSSFWorkbook(XLSXpath);
        sheet = workbook.getSheetAt(workBook);
    dataXLS = sheet.getRow(ROW).getCell(COLUMN).getStringCellValue();
    System.out.println(dataXLS);

//    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html/body/div[1]/div[4]/nav/div/div")));
//    COPY = sheet.getRow(ROW).getCell(COLUMN+1).getStringCellValue();
//    System.out.println(COPY);
//    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html/body/div[1]/section[3]/div/div[2]/div[4]/div[1]/div/div[2]/div[2]/ul/li/div/div[2]/div[2]/div[1]")));
//    copyHolder = driver.findElement(By.xpath(position));

}
    void findTACopy() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        expectedTATest = driver.findElement(By.cssSelector(dataXLS));
        expectedTATest = driver.findElement(By.xpath(dataXLS));
//        expectedTATest = driver.findElement(By.linkText(dataXLS));
        Actions actions = new Actions(driver);
        actions.moveToElement(expectedTATest).perform();

        COPY=expectedTATest.getText();
        System.out.println(COPY);
    }
    void writeFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(XLSXpath);
        workbook.write(fos);
        fos.close();
        workbook.close();

    }
    void writeResult () throws IOException{
        FileInputStream fis = new FileInputStream(XLSXpath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheetAt(workBook);
        Row row = sheet.getRow(ROW);
        row.createCell(COLUMN+2).setCellValue(COPY);
        writeFile();

    }
    public void takeScreenshot(Integer ROW) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,700)");
        // Cast WebDriver instance to TakesScreenshot to access screenshot functionality
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;

        // Capture the screenshot and store it as a file
        File srcFile = screenshotTaker.getScreenshotAs(OutputType.FILE);

        // Define the destination file path
        File destFile = new File( System.getProperty("user.dir")+ "/src/test/java/OPT/GBS22328002_1054/Screenshots/" + ROW + ".jpg");

        try {
            // Copy the screenshot to the specified location
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved as " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
    public void TEST() throws IOException, InterruptedException {
        ROW=ROW+1;
        System.out.println(COLUMN);
        System.out.println(ROW);
        getTestData(COLUMN);
        driver.get(dataXLS);
        getTestData(COLUMN-3);
        findTACopy();
        takeScreenshot(ROW);
        writeResult ();

    }
    @Test
    void test001() throws IOException, InterruptedException {

        TEST();
    }
    @Test
    void test002() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test003() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test004() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test005() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test006() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test007() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test008() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test009() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test010() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test011() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test012() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test013() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test014() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test015() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test016() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test017() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test018() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test019() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test020() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test021() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test022() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test023() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test024() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test025() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test026() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test027() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test028() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test029() throws IOException, InterruptedException {
        TEST();
    }
    @Test
    void test030() throws IOException, InterruptedException {
        TEST();
    }


}

