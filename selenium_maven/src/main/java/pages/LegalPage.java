package pages;

public class LegalPage  extends BasePage{
	public String title;
	
	public LegalPage(String t)
	{
		switchControlToWindowByTitle(t);
		title=driver.getTitle();
	}
	
	public boolean close_page()
	{
		return close();
	}
}
