package testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import constants.ConstantsPath;
import utility.ExcelOperations;

public class LoginTest extends TestBase{
	
	@Test
	public void verifyLogin() {
		loginPage.login("denishsantoki74@gmail.com", "D@d10033517#");
		boolean flag = loginPage.isLoginSuccessFullyDisplayed();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void verifyErrorMessage() {
		System.out.println("STEP : Click On Login Button");
		loginPage.clickOnLoginBtn();
		
		System.err.println("VERIFY : Email Required Error Message Is Visible");
		boolean emailErrorMsg = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertTrue(emailErrorMsg);
		
		System.err.println("VERIFY : Password Required Error Message Is Visible");
		boolean passwordErrorMsg = loginPage.isPasswordRequiredElementDisplayed();
		Assert.assertTrue(passwordErrorMsg);
	}
	
	@Test
	public void verifyPasswordErrorMessage() {
		System.out.println("STEP : Enter Valid User Email");
		loginPage.enterUserEmail("denishsantoki74@gmail.com");
		
		System.out.println("STEP : Click On Login Button");
		loginPage.clickOnLoginBtn();
		
		System.out.println("VERIFY : Email Required Error Message Is Not Visible");
		boolean emailErrorMsg = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertFalse(emailErrorMsg);
		
		System.err.println("VERIFY : Password Required Error Message Is Visible");
		boolean passwordErrorMsg = loginPage.isPasswordRequiredElementDisplayed();
		Assert.assertTrue(passwordErrorMsg);
	}
	
	@Test
	public void verifyEmailErrorMessage() {
		System.out.println("STEP : Enter Valid Password");
		loginPage.enterPassword("D@d10033517#");
		
		System.out.println("STEP : Click On Login Button");
		loginPage.clickOnLoginBtn();
		
		System.out.println("VERIFY : Email Required Error Message Is Visible");
		boolean emailErrorMsg = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertTrue(emailErrorMsg);
		
		System.out.println("VERIFY : Password Required Error Message Is Not Visible");
		boolean passwordErrorMsg =loginPage.isPasswordRequiredElementDisplayed();
		Assert.assertFalse(passwordErrorMsg);
	}
	
	@Test(dataProvider = "LoginData")
	public void verifyloginUsingDataDriven(String username, String password, String loginStatus) {
		System.out.println("STEP : Login with given Data");
		loginPage.login(username, password);
		String currentURL = "";
		boolean loginFlag;
		if(loginStatus.equalsIgnoreCase("pass")) {
			System.out.println("VERIFY : Login Successful Toast Message Displayed");
			loginFlag = loginPage.isLoginSuccessFullyDisplayed();
			Assert.assertTrue(loginFlag, "Login Successfully Message Not Displayed");
			
			System.out.println("VERIFY : Incorrect Email Or Password Message Not Displayed");
			loginFlag = loginPage.isLoginUnSuccessfulElementDisplayed();
			Assert.assertFalse(loginFlag, "Incorrect Email Or Password Message Displayed");
			
			currentURL = loginPage.getCurrentURL();
			System.out.println("VERIFY : Application Should Redirect To Dahsboard Page");
			Assert.assertTrue(currentURL.endsWith("dashboard/dash"), "Current URL :" + currentURL);
		}else {
			loginFlag = loginPage.isLoginUnSuccessfulElementDisplayed();
			Assert.assertTrue(loginFlag, "Incorrect Email Or Password Message Not Displayed");
			
			loginFlag = loginPage.isLoginSuccessFullyDisplayed();
			Assert.assertFalse(loginFlag, "Login Successfully Message Displayed");
		
			currentURL = loginPage.getCurrentURL();
			Assert.assertTrue(currentURL.endsWith("auth/login"));
		}
	}
	
	@DataProvider(name="LoginData")
	public Object[][] getLoginData() throws IOException{
		return ExcelOperations.getAllRows(ConstantsPath.LOGIN_WORKBOOK_PATH, "Login");
	}
}
