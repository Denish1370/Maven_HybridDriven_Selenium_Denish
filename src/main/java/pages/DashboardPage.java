package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlActions;

public class DashboardPage extends ControlActions{
	
	@FindBy(xpath = "//section[@id='sidebar']//h6[text()='Categories']//following-sibling::div[not(@id)]/label")
	List<WebElement> categoriesElementList ;
	
	@FindBy(xpath = "//section[@id= 'sidebar']//h6[text()='Sub Categories']/following-sibling::div[not(@id)]/label")
	List<WebElement> subCategoriesElementList; 
	
	@FindBy(xpath = "//section//h6[text()='Search For']//following-sibling::div[contains(@class,'form-group')]/label")
	List<WebElement> searchForElementList;
	
	@FindBy(xpath = "(//label[text()='mobiles'])[2]/preceding-sibling::input")
	WebElement mobileCheckboxElement;
	
	@FindBy(xpath = "//div[@class='card']")
	List<WebElement> listOfCards;

	@FindBy(xpath = "//button[@class='btn btn-custom'  and contains(text(),'Cart')]")
	WebElement cartMenu;
	
	@FindBy(xpath = "//div[contains(@class, 'la-ball-scale-multiple ng-star-inserted')]")
	WebElement spinnerElement;
	
	@FindBy(xpath = "//button[@class='btn btn-custom' and contains(text(),'Sign Out')]")
	WebElement logoutElement;
	
	private CartPage cartPage;
	
	public DashboardPage() {
		PageFactory.initElements(driver, this);
		cartPage = new CartPage();
	}
	
	public int getTotalItemInCategories() {
		return categoriesElementList.size();
	}
	
	public List<String> getCategoriesItemTextList(){
		return getElementTextList(categoriesElementList);
	}
	
	public int getTotalItemInSubCategories() {
		return subCategoriesElementList.size();
	}
	
	public List<String> getSubCategoriesItemTextList(){
		List<String> subCategoriesItemTextList = getElementTextList(subCategoriesElementList);
		return subCategoriesItemTextList;
	}
	
	public int getTotalItemInSerachFor() {
		return searchForElementList.size();
	}
	
	public List<String> getSearchForItemTextList(){
		return getElementTextList(searchForElementList);
	}
	
	public void selectOptionItem(String itemName) {
		//String locator = "(//label[text()='"+itemName+"'])[2]/preceding-sibling::input";
		String locatorValue = String.format("(//label[text()='%s'])[2]/preceding-sibling::input", itemName);
		//WebElement e = getElement("XPATH", locatorValue, true);
		clickOnElement("XPATH", locatorValue, true);
	}
	
	public boolean isOptionItemSelected(String itemName) {
		String locatorValue = String.format("(//label[text()='%s'])[2]/preceding-sibling::input", itemName);
		return getElement("XPATH",locatorValue,false).isSelected();
	}
	
	public int getTotalNumberOfVisibleCards() {
		return listOfCards.size();
	}
	
	public void addToCart(String productName) {
		productName = productName.toLowerCase();
		String locator = 
				String.format("//b[text()='%s']/parent::h5/following-sibling::button[contains(text(),' Add To Cart')]",productName);
	
		clickOnElement("XPATH", locator, true);
	}
	
	public void waitForSpinnerToBeDisappear() {
		waitForElementToBeInvisible(spinnerElement);
	}
	
	public CartPage clickOnCartMenu() {
		clickOnElement(cartMenu, true);
		return cartPage;
	}
	
	public void clickOnLogOut() {
		clickOnElement(logoutElement, false);
	}
}
