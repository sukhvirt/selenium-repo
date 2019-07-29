package com.walmart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.walmart.base.BaseClass;

public class SignInPage extends BaseClass {

	// define webelements
	@FindBy(id = "login-form-email")
	WebElement email;
	@FindBy(xpath = "//*[@id=\"sign-in-page-form-wrap\"]/div[1]/section[3]/form/fieldset/label[3]/input")
	WebElement password;
	@FindBy(id = "account-signin-btn")
	WebElement signInBtn;

	// Initialize pagefactory

	public SignInPage() {
		PageFactory.initElements(driver, this);
	}

	public String validateTitleOfLoginPage() {
		return driver.getTitle();
	}

	public void SignIn(String Email, String Password) throws InterruptedException {
		email.sendKeys(Email);
		password.sendKeys(Password);
		// scroll window
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		signInBtn.click();
		//return new HomePage();
	}
}
