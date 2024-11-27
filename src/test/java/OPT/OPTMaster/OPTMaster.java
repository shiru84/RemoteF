package OPT.OPTMaster;

import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class OPTMaster {
    public String dataXLS;
    static ChromeDriver driver;
    static WebDriverWait wait;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static WebElement expectedTATest;
    public String expectedTATestLocator = "/html/body/div[1]/div[3]/div/div/div[3]/div/div[4]/section/div/div[2]/div[2]/div[1]/div[1]/div/div/div[2]/div[3]/div[7]/button[2]";
    public int columnPreQA = 2;
    public int columnQA = 3;
    public int ROW = 2;
    public int wb = 0;
    static String XLS = System.getProperty("user.dir")+ "/src/test/java/OPT/Master/testfile.xlsx";

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
        dataXLS = sheet.getRow(r).getCell(c).getStringCellValue();
        Thread.sleep(1000);
    }

    void findCardCopy(Integer rP, Integer cP) throws IOException, InterruptedException {
        getTestData(rP, cP);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        expectedTATest = driver.findElement(By.xpath(dataXLS));
        Actions actions = new Actions (driver);
        actions.moveToElement(expectedTATest);
        actions.perform();
        System.out.println(expectedTATest.getText());

    }
    void clickCTA(Integer rP, Integer cP) throws IOException, InterruptedException {
        getTestData(rP, cP+2);
        System.out.println(dataXLS);
//        driver.findElement(By.xpath(dataXLS)).click();
        driver.findElement(By.linkText("Continue")).click();

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
    void test(Integer row, Integer cP, Integer cQA) throws IOException, InterruptedException {
        getTestData(row, cP);
        driver.get(dataXLS);
        clickCTA(row, cP);
        getTestData(row,cQA);
        driver.get(dataXLS);
        findCardCopy(row,0);
        writeResult(row, cP);
    }
    @Test
    void test00 () throws IOException, InterruptedException {
        test( 3, 2, 3);
       
    }
    @Test
    void test01 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test(ROW, columnPreQA, columnQA);
    }
    @Test
    void test02 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test(ROW, columnPreQA, columnQA);

    }
    @Test
    void test03 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test(ROW, columnPreQA, columnQA);

    }
    @Test
    void test04 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test(ROW, columnPreQA, columnQA);

    }
    @Test
    void test05 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test(ROW, columnPreQA, columnQA);

    }
    @Test
    void test06 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test(ROW, columnPreQA, columnQA);

    }
    @Test
    void test07 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test(ROW, columnPreQA, columnQA);

    }
    @Test
    void test08 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test(ROW, columnPreQA, columnQA);

    }@Test
    void test09 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test(ROW, columnPreQA, columnQA);

    }@Test
    void test10 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test(ROW, columnPreQA, columnQA);

    }@Test
    void test11 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test(ROW, columnPreQA, columnQA);

    }@Test
    void test12 () throws IOException, InterruptedException {
        ROW = ROW+1;
        test(ROW, columnPreQA, columnQA);

    }

    @AfterSuite
    void closeBrowser() {

        driver.quit();
    }

}
