package StepDefination;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pageobject.HomePageObject;

public class HomePage {

	private WebDriver driver;
	private HomePageObject home;
	static String path = System.getProperty("user.dir");

	@Given("^User launches the web application$")
	public void user_launches_the_web_application() throws Throwable {
		launchBrowser();
	}

	@Given("^User verifies the components under home page$")
	public void user_verifies_the_components_under_home_page() throws Throwable {
		HomePageObject home = new HomePageObject(driver);
		Assert.assertTrue("Component verificationpassed under home page", home.verifyHomeComponents());
		driver.quit();
	}

	// @When("^User sets the record count filter to \"(.*?)\"$")
	@Given("User sets the record count filter to 20$")
	public void _user_sets_the_record_count_filter() throws Throwable {
		HomePageObject home = new HomePageObject(driver);
		home.setFilterForRowCount();
	}

	@When("User sets the record count filter to {string}")
	public void _user_sets_the_record_count_filter(String count) throws Throwable {
		HomePageObject home = new HomePageObject(driver);
		home.setFilterForRowCount(count);
	}

	@SuppressWarnings("deprecation")
	@Then("User should see only {string} records displayed")
	public void user_should_see_only_recordcount_set_in_filter(String items) throws Throwable {
		HomePageObject home = new HomePageObject(driver);
		Assert.assertTrue("Total record count matches with the filter set", home.verifyRecordCountDisplayed(items));
		driver.quit();
	}

	@When("^User set filters under More Filter$")
	public void user_set_filters_under_More_Filter() throws Throwable {
		HomePageObject home = new HomePageObject(driver);
		home.setMineableAndCoin();
	}

	@When("^User set Algorith filter to PoW and filters under More Filter$")
	public void user_set_algorith_filter_to_PoW_and_filters_under_More_Filter() throws Throwable {
		HomePageObject home = new HomePageObject(driver);
		home.updateFilters();
	}

	@Then("^User stored the data displayed$")
	public void user_stored_the_data_displayed() throws Throwable {
		HomePageObject home = new HomePageObject(driver);
		home.storeFilterData();
	}

	@When("^User set Algorith filter to PoW$")
	public void user_set_Algorith_filter_to_pow() throws Throwable {
		HomePageObject home = new HomePageObject(driver);
		home.setAlgorithFilter();
		// driver.quit();
	}

	@Then("User should be able to see the filer set")
	public void user_should_be_able_to_see_the_filer_set() throws Throwable {
		HomePageObject home = new HomePageObject(driver);
		 driver.quit();
	}

	@Then("user prints the values")
	public void sdss() throws Throwable {
		HomePageObject home = new HomePageObject(driver);
		home.getCompanyNames();
		// driver.quit();
	}

	@Then("^User should s login successfully$")
	WebDriver launchBrowser() {
		System.out.println("Path is: "+path);
		System.setProperty("webdriver.chrome.driver", path + "\\src\\test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://coinmarketcap.com/");
		driver.manage().window().maximize();
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return this.driver;
	}

	@Then("^User compares the data between filter and unfilter data$")
	public void user_compares_the_data_between_filter_and_unfilter_data() throws Throwable {
		HomePageObject home = new HomePageObject(driver);
		Assert.assertFalse("Validation of data with and without filer", home.verifyFilteredData());
		
		driver.quit();

	}
}
