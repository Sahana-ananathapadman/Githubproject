package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.BasePage;
import pages.HomePage;
import pages.LegalPage;

public class MediaLinksVerfication extends BaseTest{
  @Test
  public void f() {
	  test = report.createTest("Media link verfication ");
	  HomePage home=new HomePage();
	  home.clickPrivacyLink();
	  LegalPage legal=new LegalPage("Legal - About | Micro Focus");
	  Assert.assertEquals("Legal - About | Micro Focus",legal.title);
	  Assert.assertTrue(legal.close(),"Either only one window is available or you are trying to close the parent window");
  }
}
