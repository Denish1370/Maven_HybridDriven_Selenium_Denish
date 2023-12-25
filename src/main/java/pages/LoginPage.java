package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlActions;
import constants.ConstantsPath;


public class LoginPage extends ControlActions{
	
	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement userEmailElement;

	@FindBy(xpath = "//input[@id='userPassword']")
	WebElement userPasswordElement;

	@FindBy(xpath = "//input[@id='login']")
	WebElement loginBtnElement;

	@FindBy(xpath = "//div[@aria-label='Login Successfully']")
	WebElement loginSuccessfullElement;

	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
	WebElement loginUnSuccessfullElement;

	@FindBy(xpath = "//div[text()='*Email is required']")
	WebElement emailIsRequiredElement;

	@FindBy(xpath = "//div[text()='*Password is required']")
	WebElement passedIsRequiredElement;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void login(String email, String password) {
		enterUserEmail(email);
		enterPassword(password);
		clickOnLoginBtn();
	}
	
	public void enterUserEmail(String email) {
		System.out.println("STEP: Entered Email address");
		userEmailElement.sendKeys(email);
	}
	
	public void enterPassword(String password) {
		System.out.println("STEP: Entered Password");
		userPasswordElement.sendKeys(password);
	}
	
	public DashboardPage clickOnLoginBtn() {
		System.out.println("STEP : Click on Login Button");
		loginBtnElement.click();
		return new DashboardPage();
	}
	
	public boolean isLoginSuccessFullyDisplayed() {
		return isElementDisplayedWithWait(loginSuccessfullElement,ConstantsPath.FAST_WAIT);
	}
	
	public boolean isLoginUnSuccessfulElementDisplayed() {
		return isElementDisplayedWithWait(loginUnSuccessfullElement,ConstantsPath.FAST_WAIT);
	}

	public boolean isEmailRequiredElementDisplayed() {
		return isElementDisplayed(emailIsRequiredElement);
	}

	public boolean isPasswordRequiredElementDisplayed() {
		return isElementDisplayed(passedIsRequiredElement);
	}

	public String getCurrentURL() {
		return super.getCurrentURL();
	}
}
