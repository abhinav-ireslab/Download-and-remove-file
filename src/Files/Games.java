package Files;

import java.awt.Window;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Games {

	public static WebDriver driver;

	public static void main(String[] args) throws IOException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\E2E\\Desktop\\Drivers\\chromedriver.exe");
		// driver = new FirefoxDriver();
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.game.tv/");

		WebElement element = driver.findElement(By.xpath("/html/body/div/section[11]/div/h3"));

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);

		// List <WebElement> game = (List<WebElement>)
		// driver.findElements(By.xpath("/html/body/div/section[11]/div/div[1]/ul"));

		for (WebElement game : driver.findElements(By.xpath("/html/body/div/section[11]/div/div[1]/ul"))) {

			// for(int i =0; i<=game.size(); i++) {

			System.out.println("List of Games: " + "\n" + game.getText());
			break;
		}

		List <WebElement> game = (List<WebElement>)driver.findElements(By.xpath("/html/body/div/section[11]/div/div[1]/ul"));
		for(int i=game.size(); i<=0; i--) {
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sh = wb.createSheet("sheet");
			XSSFRow rw = sh.createRow(0);
			XSSFCell cell = rw.createCell(i);

			cell.setCellValue(i);

			FileOutputStream fout = new FileOutputStream("C:\\Users\\E2E\\Downloads\\Games.xlsx");
			wb.write(fout);
			fout.close();
		}

	}
}
