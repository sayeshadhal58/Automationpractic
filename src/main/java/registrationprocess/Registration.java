package registrationprocess;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;




public class Registration {
	
	static WebDriver driver;

	@BeforeClass
	public static void OpenBrowser() {
		String projectPath=System.getProperty("user.dir"); 
		System.setProperty("webdriver.chrome.driver", projectPath+"\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");

	}
	public  void  Userregistration(String emailId) {
		driver.findElement(By.xpath("//header/div[2]/div[1]/div[1]/nav/div[1]/a")).click();
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS );
		driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(emailId);
		driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]/span")).click();
	}

	
	public void fillUserResgistrationPage(String emailId,String firstName,String lastName,String pwd, String dob_date,String dob_month,String dob_year,
			String add_firstNamw,String add_lastName, String company,String add_address1, String add_address2, String city,String state, String postCode,
			String country,String registerAddress, String phoneNumber, String mobileNumber, String aliies){
		Userregistration(emailId);
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS );
		driver.findElement(By.id("id_gender2")).click();
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS );
		driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(pwd);
		Select daySelect=new Select(driver.findElement(By.id("days")));
		daySelect.selectByValue(dob_date);
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS );
		Select monthSelect =new Select(driver.findElement(By.id("months")));
		monthSelect.selectByVisibleText(dob_month );
		Select yearSelect =new Select(driver.findElement(By.id("years")));
		yearSelect.selectByValue(dob_year);
		driver.findElement(By.id("firstname")).sendKeys(add_firstNamw);
		driver.findElement(By.id("lastname")).sendKeys(add_lastName);
		driver.findElement(By.id("company")).sendKeys(company);
		driver.findElement(By.xpath("//input[@id='address1']")).sendKeys(add_address1);
		driver.findElement(By.xpath("//input[@id='address2']")).sendKeys(add_address2);
		driver.findElement(By.id("city")).sendKeys(city);
		Select state1= new Select(driver.findElement(By.id("id_state")));
		state1.selectByVisibleText(state);
		driver.findElement(By.xpath("//select[@id='id_state']")).click();
		driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys(postCode);
		Select country1= new Select(driver.findElement(By.id("id_country")));
		country1.selectByVisibleText(country);		
		driver.findElement(By.xpath("//textarea[@id='other']")).sendKeys(registerAddress);
		driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//input[@id='phone_mobile']")).sendKeys(mobileNumber);
		driver.findElement(By.xpath("//input[@id='alias']")).sendKeys(aliies);
		driver.findElement(By.xpath("//button[@id='submitAccount']")).click();
		

	}
	
	@Test
	public void tc_001() {
		fillUserResgistrationPage("abgc1@getnada.com","sayesha","dhal","watson","25","August ","2001","sayesha","watson","wipro","1stcrossmunnakolala","marathalli",
				"Bangalore","Indiana","00000","United States","register address","252001","943745200","Indian");
		WebElement successmsg=driver.findElement(By.xpath("//div[@id='center_column']/p"));	
		Assert.assertTrue(successmsg.getText().contains("Welcome to your account."));
	}

	/* Verify invalid email address error. */
	@Test
	public void tc_002() {
		Userregistration("abcd");
		WebElement errormsg=driver.findElement(By.xpath("//div[@id='create_account_error']/ol/li"));
		Assert.assertTrue(errormsg.getText().contains("Invalid email address."));
	}

	/* Verify error messages for mandatory fields */
	@Test
	public void tc_003() {
		Userregistration("abxjn@gmail.com");
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@id='submitAccount']")).click();
		WebElement firstNameErrorMsg=driver.findElement(By.xpath("(//li[contains(text(),' is required.')]/b)[2]"));
		Assert.assertTrue(firstNameErrorMsg.isDisplayed());
	}
	
	@Test
	public void tc_004() {
		fillUserResgistrationPage("abas1@getnada.com","1sht57","0xhgf","watson","25","August ","2001","sayesha","watson","wipro","1stcrossmunnakolala","marathalli",
				"Bangalore","Indiana","Asjhhjm","United States","register address","252001","ADXEGR","Indian");
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		WebElement lastNameErrorMsg=driver.findElement(By.xpath("(//li[contains(text(),' is invalid.')]/b)[1]"));
		WebElement firstNameErrorMsg=driver.findElement(By.xpath("(//li[contains(text(),' is invalid.')]/b)[2]"));
		WebElement phoneNumberErrorMsg=driver.findElement(By.xpath("(//li[contains(text(),' is invalid.')]/b)[3]"));
		Assert.assertTrue(lastNameErrorMsg.isDisplayed());
		Assert.assertTrue(firstNameErrorMsg.isDisplayed());
		Assert.assertTrue(phoneNumberErrorMsg.isDisplayed());	
	}
	
	@Test
	public void Womenlink(){
		Actions act= new Actions(driver);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		WebElement w= driver.findElement(By.xpath("//div[@id=\"block_top_menu\"]/ul/li[1]/a"));
		act.moveToElement(w).click().build().perform();
        driver.findElement(By.xpath("//div[@id=\"block_top_menu\"]/ul/li[1]/ul[1]/li[2]/ul/li[3]/a")).click();	
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        List<WebElement>element =  driver.findElements(By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[2]/ul[1]/li[3]/div[1]/div[2]/h5[1]/a"));
//        List<String> ls= new ArrayList<String>();
//        ls.add("element");
//        
//        System.out.println("text produce:" +ls);
    System.out.println("last product:" + element.size());
    for (int i = 0; i < element.size(); i++) {
    	System.out.println("text product:" + element.get(i).getSize());
    	
    }
	}
    	 
    	 
     //}
    
		
	
		

	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}


