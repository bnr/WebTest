package pageobject;

/**
 * @author 
 */
// Objects and functions related to home page

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;

public class HomePageObject {

	WebDriver driver;
//	static Logger log = Logger.getLogger(HomePageObject.class);
	private static Logger log = Logger.getLogger(HomePageObject.class.getName());

	static String path = System.getProperty("user.dir");

	static ArrayList<String> names = new ArrayList<String>();

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		DOMConfigurator.configure(path+ "\\src\\test\\log4j.xml");
		
	}

	public void launchBroswer() {

		System.out.println("Path is: "+path);
		System.setProperty("webdriver.chrome.driver", path + "\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://coinmarketcap.com/");
		driver.manage().window().maximize();
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@FindBy(xpath = "//*/button[contains(text(),'Log In')]")
	public WebElement btnLogIn;

	@FindBy(xpath = "//*/button[contains(text(),'Sign up')]")
	public WebElement btnSignUp;

	@FindBy(xpath = "//*/button[contains(text(),'Categories')]")
	public WebElement btnCategories;

	@FindBy(xpath = "//*/button[contains(text(),'DeFi')]")
	public WebElement btnDeFi;

	@FindBy(xpath = "//*/button[contains(text(),'NFT')]")
	public WebElement btnNFT;

	@FindBy(xpath = "//*/button[contains(text(),'Metaverse')]")
	public WebElement btnMetaverse;

	@FindBy(xpath = "//*/button[contains(text(),'Polkadot')]")
	public WebElement btnPolkadot;

	@FindBy(xpath = "//*/button[contains(text(),'BNB Chain')]")
	public WebElement btnBNBChain;

	@FindBy(xpath = "//*/button[contains(text(),'Solana')]")
	public WebElement btnSolana;

	@FindBy(xpath = "//*/button[contains(text(),'Avalanche')]")
	public WebElement btnAvalanche;

	// @FindBy(xpath = "//*/button[contains(text(),'Filters')]")
	@FindBy(xpath = "//*/div/button[contains(text(),'Filters')]")
	public WebElement btnFilters;

	@FindBy(xpath = "//*/button[contains(text(),'Customize')]")
	public WebElement btnCustomize;

	@FindBy(xpath = "//*/button[contains(text(),'Categories')]")
	public WebElement btnCategory;

	@FindBy(xpath = "//*/button[contains(text(),'Algorithm')]")
	public WebElement btnAlgorithm;

	@FindBy(xpath = "//*/button[contains(text(),'Platform')]")
	public WebElement btnPlatform;

	@FindBy(xpath = "//*/button[contains(text(),'Industry')]")
	public WebElement btnIndustry;

	@FindBy(xpath = "//*/button[contains(text(),'+')]")
	public WebElement btnMoreFilter;

	@FindBy(xpath = "//*/button[contains(text(),'Coins')]")
	public WebElement btnCoins;

	@FindBy(xpath = "//*/button[contains(text(),'Price')]")
	public WebElement btnPrice;

	@FindBy(xpath = "//*/button[contains(text(),'All Cryptocurrencies')]")
	public WebElement btnCryptocurrencies;

	@FindBy(xpath = "//*[@id=\"mineable\"]/span")
	public WebElement toMineable;

	@FindBy(xpath = "//button[contains(text(),'Show results')]")
	public WebElement btnShowResults;

//data table
	@FindBy(xpath = "//*[@id='__next']//table")
	public WebElement dataTable;

	// record count filer
	@FindBy(xpath = "//*//div[2]//div[3]/div[2]/div[2]/div[2]/div[1]/div")
	public WebElement drpCount;

//Verify that all the components are displayed under home page
	public boolean verifyHomeComponents() {
		int count = 0;
		boolean returnType = false;
		List<WebElement> compomentList = new ArrayList<WebElement>();
		log.info("Started validating Home Page UI Components");

		Collections.addAll(compomentList, btnLogIn, btnSignUp, btnCryptocurrencies, btnCategories, btnDeFi, btnNFT,
				btnMetaverse, btnPolkadot, btnBNBChain, btnSolana, btnAvalanche);
		Iterator itr = compomentList.iterator();
		while (itr.hasNext()) {
			WebElement element = (WebElement) itr.next();
			if (!checkObjectIsDisplay(element)) {
				count++;
			} else {
				System.out.println("Pass: " + element.toString());
				log.info("PASS: " + element.toString());
			}

		}
		if (count == 0) {
			returnType = true;
		}
		log.info("Completed validating Home Page UI Components");
		return returnType;
	}

//Function to check object dispay		
	boolean checkObjectIsDisplay(WebElement element) {
		boolean returnValue = false;
		try {
			returnValue = element.isDisplayed();
		} catch (Exception e) {
			log.error("Issue while checking for object" + e.toString());
		}
		return returnValue;
	}

	public void setFilterForRowCount(String count) {
		drpCount.click();
		driver.findElement(By.xpath("//*/button[contains(text(),'20')]")).click();
	}

	public void setFilterForRowCount() {
		drpCount.click();
		driver.findElement(By.xpath("//*[@id='tippy-1']/div/div[1]/div//button[3]")).click();
	}

	public boolean verifyRecordCountDisplayed(String count) {
		boolean returnType = false;
		try {
			log.info("Row count: " + count);
			System.out.println("Row count: " + count);
			scrollWebPage();
			Thread.sleep(40);
			List<WebElement> listOfElements = driver.findElements(By.xpath("//*/button[contains(text(),count)]"));

			if (Integer.valueOf(count) == listOfElements.size()) {
				returnType = true;
				System.out.println("Actual: " + listOfElements.size() + "Expeced is " + count);
			}
		} catch (Exception e) {
		}
		{
			log.error("Issue with the record count filter");
		}
		return returnType;
	}

	public boolean verifyRecordCountDisplayed() throws IOException, InterruptedException {
		boolean returnType = false;
		int count = 20;
		screenShot(driver);
		scrollWebPage();
		log.info("Started validating the row count");
		log.debug("Started validating the row count");
		try {
			log.debug("roun count: " + count);
			List<WebElement> listOfElements = driver.findElements(By.xpath("//*/div/div[4]/table/tbody/tr"));

			if (Integer.valueOf(count) == listOfElements.size()) {
				returnType = true;
			}
		} catch (Exception e) {
		}
		{
			log.error("Issue with the record count filter");
		}
		return returnType;
	}

	public void setFilter(String key, String value) {
		btnFilters.click();
		btnAlgorithm.click();
		driver.findElement(By.xpath("//*[@id='__next']/div//div[5]/div/ul/li[4]")).click();
	}

	public void selectMineableAndCrypto() {
		btnMoreFilter.click();
		driver.findElement(By.xpath("//*[@id='tippy-9']/div/div[1]/div/div/div[1]/ul/li[4]")).click();

	}

	public void setAlgorithFilter() {

		scrollWebPage();
		btnFilters.click();
		btnAlgorithm.click();
		driver.findElement(By.xpath("//li[normalize-space(text())='PoW']")).click();
	}

	public void setMineableAndCoin() {
		btnMoreFilter.click();
		btnCryptocurrencies.click();
		btnCoins.click();
		btnPrice.click();

		// Enter price
		driver.findElement(By.xpath("//*/div//div[5]//div[4]/div[1]/div[1]/input[1]")).sendKeys("100");
		driver.findElement(By.xpath("//*/div//div[5]//div[4]/div[1]/div[1]/input[2]")).sendKeys("1000");
		toMineable.click();
		btnShowResults.click();
	}

	public void setFiltersUnderMorefilters() {
		try {
			log.info("Started setting filters under More filters");
			// scrollWebPage();
			btnMoreFilter.click();

			// Set Mineable
			driver.findElement(By.xpath("//*[@id='tippy-17']//div[1]/ul/li[5]")).click();
			// Set coin
			btnCoins.click();

			// set price
			btnPrice.click();
			driver.findElement(By.xpath("//*[@id='__next']/div//div[2]/div/div[4]//input[1]")).sendKeys("100");
			driver.findElement(By.xpath("//*[@id='__next']/div//div[2]/div/div[4]//input[2]")).sendKeys("10000");
			driver.wait(2000);
			btnShowResults.click();

		} catch (Exception e) {
		}
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	public static void screenShot(WebDriver driver) throws IOException, InterruptedException {
		log.info("Taking screen shot");
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File dest = new File(path + "screenShot/screenshot_" + timestamp() + ".png");
		FileUtils.copyFile(scr, dest);
		Thread.sleep(3000);
	}

	public static String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

	void scrollWebPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
	}

	public void storeFilterData() {
		scrollWebPage();
		for (int count = 1; count <= 20; count++) {
			names.add(driver.findElement(By.xpath("//table/tbody/tr[" + count + "]/td[3]/div/a/div/div/p")).getText());
			scrollWebPage();
		}
	}

	public void getCompanyNames() {
		for (int count = 1; count <= 19; count++) {
			System.out.println("company names is :" + names.get(count));
		}
	}

	public void updateFilters() {
		// Set
		btnFilters.click();
		btnAlgorithm.click();
		driver.findElement(By.xpath("//li[normalize-space(text())='PoW']")).click();

		// update filters under more
		setMineableAndCoin();
	}

	public boolean verifyFilteredData() {
		boolean returnValue = false;
		ArrayList<String> namesAfterFilter = new ArrayList<String>();
		// Get the data displayed after filter
		// Get row count
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		int rowCount = driver.findElements(By.xpath("//table/tbody/tr")).size();
		for (int count = 1; count <= 5; count++) {
			namesAfterFilter.add(
					driver.findElement(By.xpath("//table/tbody/tr[" + count + "]/td[3]/div/a/div/div/p")).getText());
		}
		return returnValue = names.containsAll(namesAfterFilter);
		
	}
}
