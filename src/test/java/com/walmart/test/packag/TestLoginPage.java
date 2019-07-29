package com.walmart.test.packag;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.walmart.base.BaseClass;
import com.walmart.qa.pages.HomePage;
import com.walmart.qa.pages.SignInPage;

public class TestLoginPage extends BaseClass {
	SignInPage signin;
	HomePage homepage;

	public TestLoginPage() {
		super();
	}

	@BeforeMethod
	public void setup() {
		intialization();
		signin = new SignInPage();
	}

	@Test(priority = 1)
	public void loginTitleTest() {
		String title = signin.validateTitleOfLoginPage();
		Assert.assertEquals(title, "Walmart Canada");

	}

	@Test(priority = 2)
	public void loginTest() throws InterruptedException {
		// create action
		Actions clickAct = new Actions(driver);
		WebElement getsighin = driver.findElement(By.id("my-account-wrap"));
		clickAct.moveToElement(getsighin).click().build().perform();
		signin.SignIn("sukhvirt@gmail.com", "canada@4S");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}
}
