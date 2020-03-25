package Files;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.codehaus.plexus.util.Base64;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Billing {

	public static WebDriver driver;

	@Test(priority = 1)
	public static void main(String[] args) throws InterruptedException, IOException {

		// System.setProperty("webdriver.gecko.driver",".\\Drivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\E2E\\Desktop\\Drivers\\chromedriver.exe");
		// driver = new FirefoxDriver();
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://e2e-cfrc.signin.aws.amazon.com/console");
		// Enter the username
		driver.findElement(By.id("username")).sendKeys("shantanu@e2enetworks.com");

		// Enter the password
		driver.findElement(By.id("password")).sendKeys("Admin!2019");

		// Click Signed In button
		driver.findElement(By.id("signin_button")).click();

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/h1/span[1]")));

		// Redirect to new url
		driver.navigate().to("https://console.aws.amazon.com/billing/home#/reports/usage");

		// Select value from drop down
		Select select = new Select(driver.findElement(By.id("service-selection")));
		select.selectByVisibleText("Amazon CloudFront");

		// WebElement ele2 = driver.findElement(By.id("period-selection"));
		Select select1 = new Select(driver.findElement(By.id("period-selection")));
		select1.selectByVisibleText("Custom date range");

		// WebDriverWait wait1 = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start-date-input")));

		// Select start date
		WebElement startdate = driver.findElement(By.id("start-date-input"));
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
		startdate.clear();
		// Thread.sleep(2000);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		startdate.sendKeys(formatter.format(cal.getTime()));
		// System.out.println(startdate.getText());

		// Closed Calendar
		driver.findElement(By.xpath(
				"//html/body/div[1]/div[2]/div/div[1]/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div[2]/div[2]/div[2]/div[1]/ul[1]/li[3]/button"))
				.click();

		// Report Granularity
		// WebElement reports = driver.findElement(By.id("granularity-selection"));
		Select selects = new Select(driver.findElement(By.id("granularity-selection")));
		selects.selectByVisibleText("Hours");

		// delete the file from folder before download
		File to_files = new File("C:\\Users\\E2E\\Desktop\\Reports\\reports.csv");
		if (to_files.delete()) {
			System.out.println("C:\\Users\\E2E\\Desktop\\Reports\\reports.csv File deleted");
		} else
			System.out.println("File C:\\Users\\E2E\\Desktop\\Reports\\reports.csv doesn't exist");

		// Download CSV
		WebElement save = driver.findElement(By.id("csv-button"));
		save.click();
		// driver.navigate().refresh();
		// Thread.sleep(5000);

		File from_file = new File("C:\\Users\\E2E\\Downloads\\report.csv");
		System.out.println(from_file);

		File to_file = new File("C:\\Users\\E2E\\Desktop\\Reports\\reports.csv");

		if (from_file.renameTo(to_file))
		{
			System.out.println("Successfully moved file");
		}
		else

			System.out.println("Error while moving file");

	}

}
