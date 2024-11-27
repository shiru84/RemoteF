package CUG.CUGMaster;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

//48 tests

public class CUGMaster {
WebDriver driver;
WebDriverWait wait;
static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static String URL;
    static String COPY;
    static int r =0;
    static int c =1;
    static String XLSXpath = System.getProperty("user.dir")+"/src/test/java/CUG/CUGMaster/CUGtestfile.xlsx";
    public WebElement copyHolder;
    public static String positionOne = "/html/body/div[1]/section[3]/div/div[2]/div[4]/div[1]/div/div[2]/div[2]/ul/li/div/div[2]/div[2]/div[1]";
    public static String positionTwo = "/html/body/div[1]/section[3]/div/div[2]/div[4]/div[1]/div/div[2]/div[2]/ul/li/div/div[2]/div[2]/div[1]/div[2]/p/span";
String preURL1 = "https://www.samsung.com/uk/multistore/uk_student/";
    String preURL2 = "https://www.samsung.com/uk/multistore/uk_networks/";
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
void getFromExcell(String position) throws IOException {
        workbook = new XSSFWorkbook(XLSXpath);
        sheet = workbook.getSheetAt(1);
        URL = sheet.getRow(r).getCell(c).getStringCellValue();
        driver.get(URL);
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html/body/div[1]/div[4]/nav/div/div")));
    COPY = sheet.getRow(r).getCell(c+1).getStringCellValue();
    System.out.println(COPY);
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html/body/div[1]/section[3]/div/div[2]/div[4]/div[1]/div/div[2]/div[2]/ul/li/div/div[2]/div[2]/div[1]")));
    copyHolder = driver.findElement(By.xpath(position));


}
    void writeFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+XLSXpath);
        workbook.write(fos);
        fos.close();
        workbook.close();

    }
    void writeResult (Integer cValue) throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+XLSXpath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheetAt(1);
        Row row = sheet.getRow(r);
        row.createCell(cValue).setCellValue(copyHolder.getText());
        writeFile();

    }
    public void TEST(String POSITION) throws IOException {
        r=r+1;
        getFromExcell(POSITION);
        writeResult (3);

    }
    @Test
    void test001() throws IOException {
        TEST(positionOne);
        System.out.println(copyHolder.getText());
        Assert.assertTrue(copyHolder.getText().contains(COPY));
    }
    @Test
    void test002() throws IOException {
        TEST(positionOne);
        Assert.assertTrue(copyHolder.getText().contains(COPY));
    }
    @Test
    void test003() throws IOException {
          TEST(positionOne);
        Assert.assertTrue(copyHolder.getText().contains(COPY));
    }
    @Test
    void test004() throws IOException {
          TEST(positionOne);
        Assert.assertTrue(copyHolder.getText().contains(COPY));
    }



}

