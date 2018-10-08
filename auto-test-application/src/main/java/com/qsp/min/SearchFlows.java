package com.qsp.min;

import com.autotest.configure.Environment;
import com.autotest.utils.StackTraceInfo;
import com.autotest.utils.Utils;

import Locators.LoginPage;
import Locators.CheckoutPage;
public class SearchFlows extends Environment{
	Utils util = new Utils();
	NavigationStorefront nv = new NavigationStorefront();
	LoginFlows lf = new LoginFlows();
	
	public void searhProductByName(String productName) throws Exception {
		try {
			
			driver.waitControling.implicitlyWaitBySecond(waitingTime);
			driver.elementEventControling.click(LoginPage.searchBox);
			driver.elementEventControling.type(LoginPage.searchBox, productName);
			
			driver.waitControling.sleep(waitingTime);
			
			driver.elementEventControling.click(LoginPage.searchButton);
			driver.waitControling.sleep(waitingTime);
			
			driver.waitControling.sleep(waitingTime);
			if(driver.elementEventControling.getText(LoginPage.searchHaveResultMsg).contains("Products found") 
					|| driver.elementEventControling.getText(LoginPage.searchNoResultMsg).contains("0 items found")) {
				utils.setTrueResult("Success");
			}else {
				utils.setFalseResult("Failed");
			}
			
			driver.elementEventControling.click(LoginPage.searchFirstProduct);
			driver.waitControling.sleep(waitingTime);
			
			screenshotPath = driver.elementEventControling.captureFullScreen();
			

			
		}catch (Exception e) {
			screenshotPath = driver.elementEventControling.captureFullScreen();
			// TODO: handle exception
			System.out.println("==============Error on searhProductByName method===========>");
		}
	}
	
	
}
