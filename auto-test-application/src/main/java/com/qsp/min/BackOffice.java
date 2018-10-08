package com.qsp.min;

import com.autotest.configure.Environment;

import Locators.*;

public class BackOffice extends Environment{

	public void NavigateToBO() {
		try {			
			driver.waitControling.sleep(waitingTime);
			driver.browserControling.openURL(BackOfficePage.urlBO);
			screenshotPath = driver.elementEventControling.captureFullScreen();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("==== Error on NavigateToBO method====>");
		}
	}
	
	
	public void loginBO(String username,String password) throws Exception {
		try {
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(BackOfficePage.username,username);
			//click checkout button
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(BackOfficePage.password,password);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(BackOfficePage.loginBtn);
			
			driver.waitControling.sleep(waitingTime);
			
			screenshotPath = driver.elementEventControling.captureFullScreen();
		}catch (Exception e) {
			// TODO: handle exception
			screenshotPath = driver.elementEventControling.captureFullScreen();
			System.out.println("==== Error on loginBO method====>");
		}
	}
	
	
	public void viewPromotionRule(String name) throws Exception {
		try {
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(BackOfficePage.expandViewTree);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(BackOfficePage.marketing);
			//click checkout button
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(BackOfficePage.promotionRules);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(BackOfficePage.showAll);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(BackOfficePage.promotionRulesSearch,name);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(BackOfficePage.promotionRulesSearchBtn);
			
			String resultXpath = BackOfficePage.promotionRulesSearchFirstResult1 + name + BackOfficePage.promotionRulesSearchFirstResult2;
			
			driver.waitControling.sleep(5000);
			driver.elementEventControling.click(resultXpath);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(BackOfficePage.promotionRulesExpandView);
			
			screenshotPath = driver.elementEventControling.captureFullScreen();
		}catch (Exception e) {
			// TODO: handle exception
			screenshotPath = driver.elementEventControling.captureFullScreen();
			System.out.println("==== Error on viewPromotionRule method====>");
		}
	}
}
