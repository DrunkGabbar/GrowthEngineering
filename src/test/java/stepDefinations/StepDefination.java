package stepDefinations;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefination {

	public static final WebDriver driver = new ChromeDriver();
	String homePage = "https://www.growthengineering.co.uk/";
	String url = "";
	Iterator<WebElement> it;
	HttpURLConnection huc = null;
	int respCode = 200;

	@Given("^navigating to growth engineering$")
	public void navingating_To_GrowthEngineering() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver.manage().window().maximize();
		driver.get(homePage);

	}

	@When("^getting all the links present in top navigation$")
	public void getting_Links() throws Throwable {
		System.out.println(driver.findElements(By.tagName("a")).size());
		// Limiting the webDriver scope for Top navigation
		WebElement topNavigation = driver
				.findElement(By.xpath("//section[contains(@class,'elementor-section-items-middle elementor-sticky')]"));

		WebElement topNavTab = topNavigation.findElement(By.xpath("//div[@data-id='448a189']"));

		List<WebElement> links = topNavTab.findElements(By.tagName("a"));
		it = links.iterator();
	}

	@Then("^all links should work$")
	public void all_links_should_work() throws Throwable {

		while (it.hasNext()) {

			url = it.next().getAttribute("href");
			System.out.println(url);

			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			if (!url.startsWith(homePage)) {
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}

			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();

				if (respCode >= 400) {
					System.out.println(url + " is a broken link");
				} else {
					System.out.println(url + " is a valid link");
				}

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	
	  @When("^user fill all the detils in contact form required fields$") 
	  public void detils_in_required_fields() throws Throwable {
		  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  
	  driver.findElement(By.cssSelector("div[class='elementor-button-wrapper'] #contact-button")).click();
	  driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("John");
	  driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Doe");
	  
	  
	  
	  }
	  
	  @Then("^check that the form validation is working$") 
	  public void check_that_the_form_validation_is_working() throws Throwable {
	        final String randomEmail = randomEmail();
	        
	        // Find the email form field
	        WebElement email = driver.findElement(By.cssSelector("input[type='email']"));
	        
	        // Type the random email to the form
	        email.sendKeys(randomEmail);
	        

	        
	    }

	    private static String randomEmail() {
	        return "random-" + UUID.randomUUID().toString() + "@example.com";
	    }
	
	  
	  }
	 
