package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlActions;

public class CartPage extends ControlActions{
	
	@FindBy(xpath = "//div[@class='cart']/ul")
	List<WebElement> listOfProduct;
	
	@FindBy(xpath = "//div[@class='cart']/ul//h3")
	List<WebElement> listOfProductName;
	
	@FindBy(xpath = "//button[@class='btn btn-danger']")
	List<WebElement> listOfDeleteBtn;
	
	@FindBy(xpath = "//h1[text()='No Products in Your Cart !']")
	WebElement noProductsInCart;
	
	@FindBy(xpath = "//button[contains(text(),'Cart')]/label")
	WebElement cartMenuProductCount;
	
	@FindBy(xpath = "//h1[text()='My Cart']")
	WebElement myCartHeader;
	
	public CartPage(){
		PageFactory.initElements(driver, this);
	}
	
	public void waitForCartPageLoad() {
		waitForElementToBeVisible(myCartHeader);
	}
	
	public int getTotalNumberOfProductsInMyCart() {
		return listOfProduct.size();
	}
	
	public List<String> getListOfProducstNameInMyCart(){
		return getElementTextList(listOfProduct);
	}
	
	public boolean isProductAvailableInMyCart(String productName) {
		return getElementTextList(listOfProduct).contains(productName);
	}
	
	public boolean isProductDisplayedAtTheEndOfList(String productName) {
		 int lastProdIndex = getTotalNumberOfProductsInMyCart()-1;
		 if(getListOfProducstNameInMyCart().get(lastProdIndex).equals(productName))
			 return true;
		 else 
			 return false;
	}
	
	public String getProductPrice(String productName) {
		String locator = String.format("//h3[text()='%s']/../following-sibling::div[@class='prodTotal cartSection']/p", productName);
		return getElementText("XPATH", locator, false);
	}
	
	public void clickOnRemoveProductBtn(String productName) {
		String locator = String.format("//h3[text()='%s']/following::div[contains(@class,'removeWrap')][1]", productName);
		clickOnElement("XPATH", locator, true);
	}
	
	public void removeAllProductsFromCart() {
		for(WebElement e : listOfDeleteBtn) {
			e.click();
		}
	}
	
	public boolean isNoProductInCartIsDisplayed() {
		return isElementDisplayed(noProductsInCart);
	}
	
	public void clickOnBuyNowBtn(String productName) {
		String locator = String.format("//h3[text()='%s']//following::button[contains(@class,'btn-primary')][1]", productName);
		clickOnElement("XAPTH", locator, true);
	}
	
	public int getProductCountOnCartMenu() {
		return Integer.parseInt(getElementText(cartMenuProductCount,true));
	}
	
	public List<String> getProductDetail(String productName){
		List<String> productDetailsList = new ArrayList<String>();
		String locator = String.format("//h3[text()='%s']/preceding-sibling::p", productName);
		productDetailsList.add(getElementText("XPATH", locator, true));
		productDetailsList.add(productName);
		
		locator = String.format("//h3[text()='%s']//following-sibling::p[1]", productName);
		productDetailsList.add(getElementText("XPATH", locator, false).trim());
		
		locator = String.format("//h3[text()='%s']//following-sibling::p[2]", productName);
		productDetailsList.add(getElementText("XPATH", locator, false).trim());
		
		productDetailsList.add(getProductPrice(productName));
	
		return productDetailsList;
	}
}
