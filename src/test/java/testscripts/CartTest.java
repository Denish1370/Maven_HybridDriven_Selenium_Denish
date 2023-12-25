package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;

public class CartTest extends TestBase{
	
	@Test
	public void verifyAddToCartFuncationality() {
		verifyLogin();
		String productName = "ADIDAS ORIGINAL";
		System.out.println("STEP : Click on Add To Cart Button And product Select");
		dashboardPage.addToCart(productName);
		dashboardPage.waitForSpinnerToBeDisappear();
		
		System.out.println("STEP : Navigate to cart menu");
		CartPage cartPage = dashboardPage.clickOnCartMenu();
		cartPage.waitForCartPageLoad();
		
		System.out.println("STEP : Get Count of Product added into CartMenu");
		int countOfProduct = cartPage.getProductCountOnCartMenu();
		System.out.println("VERIFY - Count should be 1");
		Assert.assertEquals(countOfProduct, 1);
		
		System.out.println("VERIFY : Count of Product in MyCart should 1");
		countOfProduct = cartPage.getTotalNumberOfProductsInMyCart();
		Assert.assertEquals(countOfProduct, 1);
		
		System.out.println("VERIFY : Added Product is visible in MyCart");
		boolean productVisiblityFlag = cartPage.isProductAvailableInMyCart(productName);
		Assert.assertTrue(productVisiblityFlag);
	}
	
	@Test
	public void verifyMultipleAddToCartProductFunctionality() {
		verifyLogin();
		String productName1 = "ADIDAS ORIGINAL";
		String productName2 = "ZARA COAT 3";
		
		System.out.println("STEP : Click On AddToCart to add products");
		dashboardPage.addToCart(productName1);
		dashboardPage.waitForSpinnerToBeDisappear();
		dashboardPage.addToCart(productName2);
		dashboardPage.waitForSpinnerToBeDisappear();
		
		System.out.println("STEP : Navigate to cart menu");
		CartPage cartPage = dashboardPage.clickOnCartMenu();
		cartPage.waitForCartPageLoad();
		
		System.out.println("STEP : Get Count of Product added into CartMenu");
		int countOfProduct = cartPage.getProductCountOnCartMenu();
		System.out.println("VERIFY - Count should be 1");
		Assert.assertEquals(countOfProduct, 2);
		
		System.out.println("VERIFY : Count of Product in MyCart should 2");
		countOfProduct = cartPage.getTotalNumberOfProductsInMyCart();
		Assert.assertEquals(countOfProduct, 2);
		
		System.out.println("VERIFY : Added Product is visible in MyCart");
		boolean productVisiblityFlag = cartPage.isProductAvailableInMyCart(productName1);
		Assert.assertTrue(productVisiblityFlag);
		productVisiblityFlag = cartPage.isProductAvailableInMyCart(productName2);
		Assert.assertTrue(productVisiblityFlag);
	}
	
	@Test
	public void verifyCartListAfterLogout() {
		verifyLogin();
		String productName = "ADIDAS ORIGINAL";
		System.out.println("STEP : Click On AddToCart to add Zara COAT 3 Product into Cart list");
		dashboardPage.addToCart(productName);
		dashboardPage.waitForSpinnerToBeDisappear();
		
		System.out.println("STEP : Navigate to cart menu");
		CartPage cartPage = dashboardPage.clickOnCartMenu();
		cartPage.waitForCartPageLoad();
		
		System.out.println("STEP : Get Count of Product added into CartMenu");
		int countOfProduct = cartPage.getProductCountOnCartMenu();
		System.out.println("VERIFY - Count should be 1");
		Assert.assertEquals(countOfProduct, 1);
		
		System.out.println("STEP : Logout from application");
		dashboardPage.clickOnLogOut();
		
		System.out.println("STEP : Login to application");
		verifyLogin();
		
		System.out.println("STEP : Click on MycartMenu");
		dashboardPage.clickOnCartMenu();
		
		System.out.println("VERIFY : No Products in Your Cart ! is displayed");
		cartPage.waitForCartPageLoad();
		boolean noProductMessageFlag = cartPage.isNoProductInCartIsDisplayed();
		Assert.assertTrue(noProductMessageFlag);
	}
}
