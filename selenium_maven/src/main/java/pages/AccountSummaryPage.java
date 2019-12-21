package pages;

import org.openqa.selenium.By;

public class AccountSummaryPage extends BasePage{

	/*
	By PayBillsLink=By.xpath("//a[text()='Pay Bills']");
	public void clickPayBillsLink()
	{
		click(PayBillsLink);
	}
	*/
	
	By TabsLink=By.xpath("//ul[@class='nav nav-tabs']//a");
	
	public void navigateToTab(String tabname)
	{
		getElementByText(TabsLink,tabname).click();
	}
}
