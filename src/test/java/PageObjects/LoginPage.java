package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	//constructor
	//WebDriver driver; 
	
	public LoginPage(WebDriver driver)
	{
		//this.driver = driver; 
		//PageFactory.initElements(driver, this);
		super(driver);
	}
	
	//Locators
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txt_Email; 
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txt_Password; 
	
	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement btn_Login; 
	
	//Action
	public void setEmail(String email)
	{
		txt_Email.sendKeys(email);
	}
	
	public void setPwd(String pwd)
	{
		txt_Password.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		btn_Login.click();
	}
}
