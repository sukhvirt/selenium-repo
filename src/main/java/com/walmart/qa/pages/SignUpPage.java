package com.walmart.qa.pages;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.walmart.base.BaseClass;

public class SignUpPage extends BaseClass {
	By GetSignInLink = By.id("my-account-wrap");
	By SignUpLink = By.linkText("Sign in");
	By Email = By.className("create-account-email");
	By CreateAccBtn = By.className("create-account-create-btn");
	By CreateAccBtn2 = By.xpath("//*[@id=\"create-account-btn\"]");
	By EmailErrorMsg = By.xpath("//*[@id=\"create-account-partial\"]/div/form/fieldset/label/span[2]");
	By PhoneNo = By.cssSelector("#phone");
	By FirstName = By.id("firstName");
	By LastName = By.id("lastName");
	By Password = By.id("password");
	By ConfPassword = By.id("confirmPassword");
	By PrivacyPolicyCheckBox = By.xpath("//*[@id=\"tnc\"]");
	By EmailOptionCheckBox = By.name("emailOptIn");
	By CreateAccButton = By.id("create-account-btn");
	By ErrorInPassword = By
			.xpath("/html/body/div[1]/div[2]/div[1]/section/section/section[4]/div/form[2]/fieldset/label[6]/span[2]");
	By ErrorInFname = By
			.xpath("/html/body/div[1]/div[2]/div[1]/section/section/section[4]/div/form[2]/fieldset/label[4]/span[2]");
	By ErrorInLname = By
			.xpath("/html/body/div[1]/div[2]/div[1]/section/section/section[4]/div/form[2]/fieldset/label[5]/span[2]");
	By ErrorInConfPass = By
			.xpath("/html/body/div[1]/div[2]/div[1]/section/section/section[4]/div/form[2]/fieldset/label[7]/span[2]");
	By ErrorInSelectingPrivacyPolicy = By
			.xpath("/html/body/div[1]/div[2]/div[1]/section/section/section[4]/div/form[2]/fieldset/label[8]/span[2]");
	By LoginEmail = By.id("login-form-email");
	By LoginPassword = By.xpath("//*[@id=\"sign-in-page-form-wrap\"]/div[1]/section[3]/form/fieldset/label[3]/input");
	By SignInBtn = By.id("account-signin-btn");
	By ErrorLoginEmail = By
			.xpath("//*[@id=\"sign-in-page-form-wrap\"]/div[1]/section[3]/form/fieldset/label[2]/span[2]");
	By ErrorLoginPassword = By
			.xpath("//*[@id=\"sign-in-page-form-wrap\"]/div[1]/section[3]/form/fieldset/label[3]/span[2]");
	By AlertLoginError = By
			.xpath("/html/body/div[1]/div[2]/div[1]/section/section/div[1]/section[3]/form/fieldset/label[1]/div/p");
	By cookieAlert = By.id("accept-privacy-policies");

	
	

	
	// Register_EmptyEmail

	public void checkEmptyEmail(String email) throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(Email).sendKeys(email);
		driver.findElement(CreateAccBtn).click();
		String expectedMsg = "Email is a required field";
		String actualMsg = driver.findElement(EmailErrorMsg).getText();
		int flag = expectedMsg.compareTo(actualMsg);
		if (flag == 0) {
			System.out.println("Test Passed, Email is not entered");
		} else {
			System.out.println("Test failed, Email is entered");
		}
	}

	// Register - Send email string text

	public void typeEmail(String email) {
		driver.findElement(Email).sendKeys(email);
		driver.findElement(CreateAccBtn).click();
		String currentUrl = driver.getCurrentUrl();
		String expectedUrl = "https://www.walmart.ca/create-account";
		int flag = currentUrl.compareTo(expectedUrl);
		if (flag == 0) {
			System.out.println("Test Passed, email is valid");
		} else {
			String errorMsg = driver.findElement(EmailErrorMsg).getText();
			System.out.println(errorMsg);
			System.out.println("Test failed, email is not vaild");
		}
	}

	// checking password and other fields required

	public void createAcc(String email, String phone, String fname, String lname, String password, String confPassword)
			throws InterruptedException {
		// call method to type email
		typeEmail(email);
		Thread.sleep(1000);
		driver.findElement(PhoneNo).sendKeys(phone);
		driver.findElement(FirstName).sendKeys(fname);
		driver.findElement(LastName).sendKeys(lname);
		driver.findElement(Password).sendKeys(password);
		driver.findElement(ConfPassword).sendKeys(confPassword);
		// scroll window
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");

		// create action to click check boxes
		WebElement privacycheck = driver.findElement(PrivacyPolicyCheckBox);
		WebElement emailcheck = driver.findElement(EmailOptionCheckBox);
		Actions clickAct = new Actions(driver);
		// privacy check box
		clickAct.moveToElement(privacycheck).click().build().perform();// click on
		clickAct.moveToElement(emailcheck).click().build().perform();// click on email subscription
		driver.findElement(CreateAccBtn2).click();
		Thread.sleep(2000);
		String currentUrl = driver.getCurrentUrl();
		boolean flag = currentUrl.equals("https://www.walmart.ca/my-account");
		if (flag == true) {
			System.out.println("Account is created successfully");
		} else {

			String error[] = {};
			ArrayList<String> myList = new ArrayList<String>(Arrays.asList(error));
			String fnameError, lnameError, passError, confPassError, privacyPolicyError = null;
			Boolean fnameErrorExist, lnameErrorExist, passErrorExist, confPassErrorExist, privacyErrorExist;
			// check if error exist
			fnameErrorExist = driver.findElements(ErrorInFname).size() > 0;
			lnameErrorExist = driver.findElements(ErrorInLname).size() > 0;
			passErrorExist = driver.findElements(ErrorInPassword).size() > 0;
			confPassErrorExist = driver.findElements(ErrorInConfPass).size() > 0;
			privacyErrorExist = driver.findElements(ErrorInSelectingPrivacyPolicy).size() > 0;

			// Add errors to list
			if (fnameErrorExist == true) {
				fnameError = driver.findElement(ErrorInFname).getText();
				myList.add(fnameError);
			}

			if (lnameErrorExist == true) {
				lnameError = driver.findElement(ErrorInLname).getText();
				myList.add(lnameError);
			}
			if (passErrorExist == true) {
				passError = driver.findElement(ErrorInPassword).getText();
				myList.add(passError);
			}
			if (confPassErrorExist == true) {
				confPassError = driver.findElement(ErrorInConfPass).getText();
				myList.add(confPassError);
			}
			if (privacyErrorExist == true) {
				privacyPolicyError = driver.findElement(ErrorInSelectingPrivacyPolicy).getText();
				myList.add(privacyPolicyError);
			}
			// display list
			error = myList.toArray(error);
			System.out.println("Please check these errors:  \n");
			for (String singleerror : error) {
				System.out.println(singleerror);
			}

		}
	}


}
