package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AccountSummaryPage;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.PayBillsPage;
import testdata.ReadExcelData;

public class AddNewPayee extends BaseTest {
  
	PayBillsPage paybills;
	
	@Parameters({"username","password"})
	@BeforeTest
	public void beforeTest(@Optional("username") String uname,@Optional("password") String pword)
	{
		HomePage home=new HomePage();
		home.clickSignInButton();
		//Assert.assertEquals(driver.getTitle(), "Zero - Log in");
		LoginPage login=new LoginPage();
		Assert.assertEquals(login.getPageTitle(), "Zero - Log in");
		login.doLogin("username", "password");
		AccountSummaryPage accpage=new AccountSummaryPage();
		accpage.navigateToTab("Pay Bills");	
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		paybills=new PayBillsPage();
		paybills.navigateToTab("Add New Payee");	
	}


@Test(dataProvider = "dp")
  public void addNewPayee(String p1, String p2,String p3,String p4 ) {
	test = report.createTest("Add New Payee for test data "+p1);
	paybills.doAddNewPayee(p1,"", "", "");
  }

@Test
public void test02() throws Exception {
	test = report.createTest("Sample test");
	Assert.assertEquals(1, 2);
}


@DataProvider
public Object[][] dp() throws IOException {
	  ReadExcelData data_=new ReadExcelData("PayeeDetails.xlsx","PayeeDetails");
	  String[][] data = data_.getdata();
    return data;
  }

@AfterTest
public void afterTest()
{
	LogoutPage logout= new LogoutPage();
	logout.doLogout();
}
}
