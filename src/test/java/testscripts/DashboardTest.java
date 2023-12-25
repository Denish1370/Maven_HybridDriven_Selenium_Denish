package testscripts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardTest extends TestBase{
	
	@Test
	public void verifyItemListInSideFilterOptions() {
		List<String> expectedCatoriesList 
			= new ArrayList<String>(Arrays.asList("fashion","electronics","household"));
		
		List<String> expectedSubCatoriesList 
		= new ArrayList<String>(Arrays.asList("t-shirts","shirts","shoes","mobiles","laptops"));
		
		List<String> expectedSearchForList 
		= new ArrayList<String>(Arrays.asList("men","women"));
		
		verifyLogin();
		System.out.println("STEP : Get Total number of Items in categories");
		int actualCategoriesItemSize = dashboardPage.getTotalItemInCategories();
		
		System.out.println("VERIFY : Number of Items visible in categories");
		Assert.assertEquals(actualCategoriesItemSize, 3);
		
		System.out.println("VERIFY : Items in Catories");
		List<String> actualCategoriesList = dashboardPage.getCategoriesItemTextList();
		System.out.println(actualCategoriesList);
		Assert.assertEquals(actualCategoriesList, expectedCatoriesList);
		
		System.out.println("STEP : Get Total number of Items in Sub Categories");
		int actualSubCatoriesItemSize = dashboardPage.getTotalItemInSubCategories();
		
		System.out.println("VERIFY : Number of Items visible in Sub Categories");
		Assert.assertEquals(actualSubCatoriesItemSize, 5);
		
		System.out.println("VERIFY : Items in Sub Catories");
		List<String> actualSubCategoriesList = dashboardPage.getSubCategoriesItemTextList();
		System.out.println(actualSubCategoriesList);
		Assert.assertEquals(actualSubCategoriesList, expectedSubCatoriesList);
		
		System.out.println("STEP : Get Total number of Items in Search For");
		int actualSearchForItemSize = dashboardPage.getTotalItemInSerachFor();
		
		System.out.println("VERIFY : Number of Items visible in Search For");
		Assert.assertEquals(actualSearchForItemSize, 2);
		
		System.out.println("VERIFY : Items in Search For");
		List<String> actualSearchForList = dashboardPage.getSearchForItemTextList();
		System.out.println(actualSearchForList);
		Assert.assertEquals(actualSearchForList, expectedSearchForList);
	}
	
	@Test
	public void verifyFilterTest() {
		verifyLogin();
		System.out.println("STEP - user select the eletronics check box under Categories");
		dashboardPage.selectOptionItem("electronics");
		
		System.out.println("VERIFY - Option item is selected");
		boolean optionItemSelectedFlag = dashboardPage.isOptionItemSelected("electronics");
		Assert.assertTrue(optionItemSelectedFlag);
		
		System.out.println("VERIFY - Options are visible as per applied filter");
		int totalCards = dashboardPage.getTotalNumberOfVisibleCards();
		Assert.assertEquals(totalCards, 1);
	}
}
