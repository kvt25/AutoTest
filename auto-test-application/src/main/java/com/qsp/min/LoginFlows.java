package com.qsp.min;

import com.autotest.configure.Environment;
import com.autotest.utils.StackTraceInfo;

import Locators.LoginPage;
import Locators.MyAccountPage;

public class LoginFlows extends Environment{
	
	
	public NavigationStorefront nv = new NavigationStorefront();
	
	public void signIn(String username, String password) throws Exception
	{
		
		try {
			
			if(driver.elementChecking.isElementDisplay(LoginPage.loggedInLable)) {
			//nv.goToHomePage();
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(LoginPage.loginLink);
			driver.waitControling.sleep(waitingTime);
			
			//check email
			if(!driver.elementChecking.isElementDisplay(LoginPage.emailAdress)) {
				System.out.println("Email element is not display...");
				throw new Exception();
			}else
			{	
				driver.elementEventControling.type(LoginPage.emailAdress, username);
			}
			//check pass
			
			driver.waitControling.sleep(waitingTime);
			
			if(!driver.elementChecking.isElementDisplay(LoginPage.passWord)) {
				System.out.println("Password element is not display...");
				throw new Exception();
			}else
			{	
				driver.elementEventControling.type(LoginPage.passWord, password);
			}
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(LoginPage.loginButton);
			driver.waitControling.sleep(waitingTime);
			screenshotPath =  driver.elementEventControling.captureFullScreen();
			
			//Your username or password was incorrect.
			
			if(driver.elementEventControling.getText(LoginPage.loginPageCompletionMsg).contains("Your username or password was incorrect.")) {
				utils.setFalseResult("Failed");	
			}
			else {
				utils.setTrueResult("Success");
			}
			
			}
			else {
				System.out.println("skip Login step !");
			}
		}catch (Exception e) {
			screenshotPath =  driver.elementEventControling.captureFullScreen();
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			
		}
	}
	
	
	public void signOut() throws Exception {
		try {
			//check account
			if(!driver.elementChecking.isElementDisplay(LoginPage.hiText))
			{
				System.out.println("=========You r not sign in yet. Starting to signing in now....====>");
				signIn("oanh.nguyen@sai-digital.com", "12341234");
				driver.waitControling.sleep(waitingTime);
				
				driver.elementEventControling.click(LoginPage.myAccount);
				
				driver.waitControling.sleep(waitingTime);
				
				driver.elementEventControling.click(LoginPage.logoutLink);
			}else 
			{
				driver.waitControling.sleep(waitingTime);
				
				driver.elementEventControling.click(LoginPage.myAccount);
				driver.waitControling.sleep(waitingTime);
				
				driver.elementEventControling.click(LoginPage.logoutLink);
			}
			
			screenshotPath =  driver.elementEventControling.captureFullScreen();
			
			driver.waitControling.sleep(waitingTime);
			driver.browserControling.browserQuit();
			
		}catch (Exception e) {
			screenshotPath = driver.elementEventControling.captureFullScreen();
			System.out.println("====================Error on logOut method");
			utils.setFalseResult("Error on logOut method");
		}
	}

	public void ForgotPassword(String email) {
		try {
			
			driver.elementEventControling.click(LoginPage.loginLink);
			driver.waitControling.implicitlyWaitByMiliSecond(200);
			
			driver.elementEventControling.click(LoginPage.forgotPass);
			driver.waitControling.implicitlyWaitBySecond(3);
			driver.waitControling.implicitlyWaitBySecond(3);
			driver.elementEventControling.type(LoginPage.forgotEmail, email);
			driver.elementEventControling.click(LoginPage.forgotResetButton);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("=====================Error on ForgotPassword method ==================");
		}
	}
}
