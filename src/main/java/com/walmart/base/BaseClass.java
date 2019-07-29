package com.walmart.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.walmart.util.TestUtils;
import com.walmart.util.WebEventListener;

public class BaseClass {
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver edriver;
	public static WebEventListener eventlistner;

	public BaseClass() {
		try {
			prop = new Properties();
			FileInputStream input = new FileInputStream(
					"C:\\Users\\JSFOREVER\\eclipse-workspace\\POM_SEL\\src\\main\\java\\com\\walmart\\config\\config.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	public void intialization() {
		String browser_name = prop.getProperty("browser");
		if(browser_name.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
			 driver = new FirefoxDriver();
		}
		else if(browser_name.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\JSFOREVER\\Desktop\\sukhvir testing\\chromedriver.exe");
			 driver = new ChromeDriver();
		}
		edriver = new EventFiringWebDriver(driver);
		eventlistner = new WebEventListener();
		edriver.register(eventlistner);
		driver = edriver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(TestUtils.ImplicitTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PageLoadTimeout,TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
	}
}
