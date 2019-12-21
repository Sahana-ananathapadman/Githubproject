package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {
	
	By SignInButton=By.id("signin_button");
	By PrivacyLink= By.xpath("(//a[contains(text(),'http')])[1]");
	
	public void clickSignInButton()
	{
		click(SignInButton);
	}
	
	public void clickPrivacyLink()
	{
		click(PrivacyLink);
	}
	
}
