package com.walmart.test.packag;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.walmart.base.BaseClass;
import com.walmart.qa.pages.SignUpPage;

public class TestSignUpPage extends BaseClass {
	SignUpPage signup;
	public TestSignUpPage() {
		super();
	}

	@BeforeMethod
	public void setup() {
		intialization();
		
		signup = new SignUpPage();
		Actions clickAct = new Actions(driver);
		WebElement getsighin = driver.findElement(By.id("my-account-wrap"));
		clickAct.moveToElement(getsighin).click().build().perform();
	}
	@Test(priority=2)
	public void verifyEmptyEmail() throws InterruptedException {
		signup.checkEmptyEmail("");
	}
	/*
	@Test(priority=1)
	public void verifyemail() {
		signup.typeEmail("76889999999996");
	}
	@Test(priority=3)
	public void createAccTest() throws InterruptedException {
		signup.createAcc("sukhvirt@gmail.com","374676537", "sukhvir", "taggar", "dsfdsfsdf", "fdsfdsf");
	}*/
	@AfterMethod
	public void teardown() {
	driver.close();
}
}
