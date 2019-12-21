package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

	By UserName=By.id("user_login");
	By Password=By.id("user_password");
	By SignInButton=By.name("submit");
	
	public void doLogin(String uname,String pword)
	{
		enterText(UserName,uname,false);
		enterText(Password,pword,false);
		click(SignInButton);
	}
	
	public String getPageTitle()
	{
		return driver.getTitle();
	}
}
