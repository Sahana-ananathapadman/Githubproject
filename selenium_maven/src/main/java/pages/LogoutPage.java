package pages;

import org.openqa.selenium.By;

public class LogoutPage extends BasePage{
	
	By UserNameMenu=By.xpath("(//a[@class='dropdown-toggle'])[2]");
	By LogoutMenuItem=By.xpath("//a[text()='Logout']");
	
	
	public void doLogout()
	{
		click(UserNameMenu);
		click(LogoutMenuItem);
	}
}
