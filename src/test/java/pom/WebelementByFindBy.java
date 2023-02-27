package pom;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class WebelementByFindBy {
	
	@FindBy(xpath="//button[text()='Add Filter']")
	WebElement ele;
	
	
	
	
	
	
	public void webElements()
	{
		ele.click();
		
	}

}
