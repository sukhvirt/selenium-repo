package com.walmart.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.walmart.base.BaseClass;

public class TestUtils extends BaseClass {

	public static long PageLoadTimeout = 20;
	public static long ImplicitTimeout = 10;
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		TakesScreenshot scrnshot = (TakesScreenshot) driver;
		File screenshotfile = scrnshot.getScreenshotAs(OutputType.FILE);
		String CurrentDir = System.getProperty("user.dir");
		FileUtils.copyFile(screenshotfile, new File(CurrentDir +"/screenshots/"+System.currentTimeMillis()+ ".png"));
	}
}
