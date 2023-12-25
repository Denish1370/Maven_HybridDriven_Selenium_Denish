package testscripts;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.ControlActions;
import pages.DashboardPage;
import pages.LoginPage;

public class TestBase {
	
	DashboardPage dashboardPage;
	LoginPage loginPage;
	static int count = 1;
	
	@BeforeMethod
	public void setUp() {
		ControlActions.launchBrowser();
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		System.out.println(result.getStatus());
		if(ITestResult.FAILURE == result.getStatus())
			ControlActions.takesScreenShot(result.getName() + "_"+ count++);
		ControlActions.closeBrowser();
	}
	
	@Test
	public void verifyLogin() {
		loginPage.login("denishsantoki74@gmail.com", "D@d10033517#");
		boolean flag = loginPage.isLoginSuccessFullyDisplayed();
		Assert.assertTrue(flag);
	}
}
