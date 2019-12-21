package pages;

import org.openqa.selenium.By;

public class PayBillsPage extends BasePage {

	By SubTabs=By.xpath("//ul[contains(@class,'ui-tabs-nav ')]//a");
	By PayeeNameTextField=By.id("np_new_payee_name");
	By CurrencyDropDown= By.id("pc_currency");
	
	
	public void navigateToTab(String tabname)
	{
		getElementByText(SubTabs,tabname).click();
	}
	
	public void doAddNewPayee(String name,String addrs,String acc,String details)
	{
		enterText(PayeeNameTextField,name,true);
	}
	
	public void selectCurrency(String name)
	{
		selectFromDropDown(CurrencyDropDown,name,true);
	}
	
}
