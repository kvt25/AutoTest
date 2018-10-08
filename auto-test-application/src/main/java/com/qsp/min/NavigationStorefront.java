package com.qsp.min;

import com.autotest.configure.Environment;
import Locators.LoginPage;

public class NavigationStorefront extends Environment{
	public void startTheBrowser(String browser) throws Exception {
		try {
			log.info("============Running method==============>");
			driver.browserControling.startBrowser(browser);
			
			//driver.waitControling.implicitlyWaitBySecond(wait);
			driver.waitControling.sleep(waitingTime);
			
			driver.browserControling.openURL(LoginPage.homeUrl);
			driver.waitControling.sleep(waitingTime);
			utils.setTrueResult("Start browser and go to homepage successfully...");
			screenshotPath =  driver.elementEventControling.captureFullScreen();
			
		}catch (Exception e) {
			// TODO: handle exception
			log.info("<========== Error on goToHomePage method===============>");
			screenshotPath =  driver.elementEventControling.captureFullScreen();
		}
		
	}
	
	public void goToHomePage() throws Exception {
		try {
			log.info("============Running method==============>");
			//driver.waitControling.implicitlyWaitBySecond(wait);
			driver.waitControling.sleep(waitingTime);
			
			driver.browserControling.openURL(LoginPage.homeUrl);
			driver.waitControling.sleep(waitingTime);
			utils.setTrueResult("Start browser and go to homepage successfully...");
			screenshotPath =  driver.elementEventControling.captureFullScreen();
			
		}catch (Exception e) {
			// TODO: handle exception
			log.info("<========== Error on goToHomePage method===============>");
			screenshotPath =  driver.elementEventControling.captureFullScreen();
		}
		
	}
	
}
