package com.qsp.min;

import com.autotest.configure.Environment;
import Locators.LoginPage;
import Locators.MyAccountPage;

public class MyAccount extends Environment{

	public void editPersonalDetails(String title, String firstName, String lastName) throws Exception {
		
		try {
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(LoginPage.myAccount);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(LoginPage.personalDetails);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(MyAccountPage.title);
			
			driver.waitControling.sleep(waitingTime);
			String newTitleXpath = "//select[@id='profile.title']//option[@value='" + title.toLowerCase()+ "']";
			MyAccountPage.newtitle = newTitleXpath;
			driver.elementEventControling.click(MyAccountPage.newtitle);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(MyAccountPage.firstName, firstName);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(MyAccountPage.lastName, lastName);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(MyAccountPage.update);
			
			//Your profile has been updated
			if(driver.elementEventControling.getText(MyAccountPage.AccountPageCompletionMsg).contains("Your profile has been updated")) {
				utils.setTrueResult("Success");	
			}
			else if (driver.elementEventControling.getText(MyAccountPage.AccountPageCompletionMsg).contains("Please enter a first name") 
					|| driver.elementEventControling.getText(MyAccountPage.AccountPageCompletionMsg).contains("Please enter a last name")) {
				utils.setTrueResult("Success");	
			}
			else {
				utils.setFalseResult("Failed");
			}
			
			
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
			//need to verify on success or error base on text: Your profile has been updated
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error on editPersonalDetail method");
			screenshotPath = driver.elementEventControling.captureFullScreen();
		}
		
	}

	public void editEmailAddress(String newEmail, String password) throws Exception {
		
		try {
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(LoginPage.myAccount);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(LoginPage.emailAddress);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(MyAccountPage.newEmail, newEmail);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(MyAccountPage.newEmailConfirm, newEmail);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(MyAccountPage.emailPassword, password);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(MyAccountPage.updateEmailBtn);
			driver.waitControling.sleep(waitingTime);
			
			
			
			if(driver.elementEventControling.getText(MyAccountPage.AccountPageCompletionMsg).contains("Your profile has been updated")) {
				utils.setTrueResult("Success");	
			}
			else {
				utils.setFalseResult("Failed");
			}
			//need to verify on success or error base on text: Your profile has been updated
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error on editEmailAddress method");
			screenshotPath = driver.elementEventControling.captureFullScreen();
		}
		
	}	

	public void deletePaymentDetails(String postion) throws Exception {
		try {
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(LoginPage.myAccount);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(LoginPage.paymentDetails);
			
			driver.waitControling.sleep(waitingTime);
			//
			String newDeletePaymentXpath = "//div[@class='col-xs-12 col-sm-6 col-md-4 card'][" + postion +"]//a[@class='action-links removePaymentDetailsButton']";
			MyAccountPage.deletePayment = newDeletePaymentXpath;
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(MyAccountPage.deletePayment);
			
			driver.waitControling.sleep(waitingTime);
			String xpath = driver.elementEventControling.getHiddenAttribute(MyAccountPage.deletePaymentBtnA, "value");
			MyAccountPage.deletePaymentBtnFinal = "//form[@id='removePaymentDetails"+xpath+"']//button[@class='btn btn-default btn-primary btn-block paymentsDeleteBtn']"; 
			
			driver.waitControling.sleep(waitingTime);
			System.out.println(MyAccountPage.deletePaymentBtnFinal);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(MyAccountPage.deletePaymentBtnFinal);
			
			//Payment Card removed successfully
			if(MyAccountPage.AccountPageCompletionMsg.equalsIgnoreCase("Payment Card removed successfully")) {
				utils.setTrueResult("Success");	
			}
			else {
				utils.setFalseResult("Failed");
			}
			
			
			driver.waitControling.sleep(waitingTime);
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
		} catch (Exception e) {
			System.out.println("Error on deletePaymentDetails method");
			// TODO Auto-generated catch block
			e.printStackTrace();
			screenshotPath = driver.elementEventControling.captureFullScreen();
		}
	}
	
	//update password
	
	public void updatePassword(String currentPass, String newPass) throws Exception {
		
		try {
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(LoginPage.myAccount);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(MyAccountPage.password);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(MyAccountPage.currentPassword, currentPass);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(MyAccountPage.newPassword, newPass);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(MyAccountPage.checkNewPassword, newPass);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(MyAccountPage.updatePasswordBtn);
			driver.waitControling.sleep(waitingTime);
			
			if(driver.elementEventControling.getText(MyAccountPage.AccountPageCompletionMsg).contains("Your password has been changed")) 
			{
				utils.setTrueResult("Success");	
			}
			else {
				utils.setFalseResult("Failed");
			}
			//need to verify on success or error base on text: Your profile has been updated
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error on updatePassword method");
			screenshotPath = driver.elementEventControling.captureFullScreen();
		}
		
	}
	
	public void viewOrderHistory(String orderNumber) throws Exception {
		
		try {
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(LoginPage.myAccount);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(MyAccountPage.orderHistory);
			
			/*MyAccountPage.anOrder = MyAccountPage.anOrder + orderNumber + "']";
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(MyAccountPage.anOrder);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(MyAccountPage.reorderBtn);
			
			driver.waitControling.sleep(waitingTime);*/
			
			if(driver.elementChecking.isElementDisplay(MyAccountPage.orderHistoryLable)) 
			{
				utils.setTrueResult("Success");	
			}
			else {
				utils.setFalseResult("Failed");
			}
			
			//=========
/*			if(driver.elementChecking.isElementDisplay(MyAccountPage.secureCheckoutHeadline)) 
			{
				utils.setTrueResult("Success");	
			}
			else {
				utils.setFalseResult("Failed");
			}*/
			
			//need to verify on success or error base on text: Your profile has been updated
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			screenshotPath = driver.elementEventControling.captureFullScreen();
			e.printStackTrace();
			System.out.println("Error on viewOrderHistory method");
			
		}
		
	}
	
	//===========================
	
public String createQuotes(String quantity) throws Exception {
		
		String quotesName = "";
		try {
			
			driver.waitControling.sleep(3000);
			driver.elementEventControling.type(MyAccountPage.quotesQuantity, quantity);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(MyAccountPage.quotesRequestInCart);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(MyAccountPage.quotesSubmitBtn);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(MyAccountPage.quotesPopupYesBtn);
			//Quote submitted with success.
			driver.waitControling.sleep(waitingTime);
			
			if(driver.elementEventControling.getText(MyAccountPage.AccountPageCompletionMsg).contains("Quote submitted with success.")) 
			{
				utils.setTrueResult("Success");
				quotesName = driver.elementEventControling.getValue(MyAccountPage.quotesName);
			}
			else {
				utils.setFalseResult("Failed");
			}
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error on createQuotes method");
			screenshotPath = driver.elementEventControling.captureFullScreen();
		}
		return quotesName;
		
	}
	//===============View Quotes

	public void viewQuotes(String quoteNumber) throws Exception {
	
	try {
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(LoginPage.myAccount);
		
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(MyAccountPage.quotes);
		
		MyAccountPage.quotesNumber = MyAccountPage.quotesNumber + quoteNumber + "')]";
		
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(MyAccountPage.quotesNumber);
		
		driver.waitControling.sleep(waitingTime);
		
		if(driver.elementChecking.isElementDisplay(MyAccountPage.quotesDetailsHeadline)) 
		{
			utils.setTrueResult("Success");	
		}
		else {
			utils.setFalseResult("Failed");
		}
		//need to verify on success or error base on text: Your profile has been updated
		screenshotPath = driver.elementEventControling.captureFullScreen();
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Error on editPersonalDetail method");
		screenshotPath = driver.elementEventControling.captureFullScreen();
	}
	
}

//Add support ticket
	public void addSupportTicket(String subject, String msg, String ascOrder, String category) throws Exception {
		
	try {
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(LoginPage.myAccount);
		
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(MyAccountPage.supportTicket);
		
		
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(MyAccountPage.addSupportTicket);
		
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.type(MyAccountPage.addSupportTicketSubject,subject);
		
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.type(MyAccountPage.addSupportTicketMessage,msg);
		
		
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(MyAccountPage.addSupportTicketAssociateTo);
		
		MyAccountPage.addSupportTicketAssociateToValue = MyAccountPage.addSupportTicketAssociateToValue + ascOrder+ "'][1]"; 
				
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(MyAccountPage.addSupportTicketAssociateToValue);
		
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(MyAccountPage.addSupportTicketCategory);
		
		MyAccountPage.addSupportTicketCategoryValue = MyAccountPage.addSupportTicketCategoryValue + category + "']";
		
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(MyAccountPage.addSupportTicketCategoryValue);
		
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(MyAccountPage.addSupportTicketAddBtn);
		
		if(driver.elementChecking.isElementDisplay(MyAccountPage.AccountPageCompletionMsg)) 
		{
			utils.setTrueResult("Success");	
		}
		else {
			utils.setFalseResult("Failed");
		}
		//need to verify on success or error base on text: Your profile has been updated
		screenshotPath = driver.elementEventControling.captureFullScreen();
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Error on addSupportTicket method");
		screenshotPath = driver.elementEventControling.captureFullScreen();
	}
	
}
//view support ticket
	public void viewSupportTicket(String id) throws Exception {
		
		try {
/*			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(LoginPage.myAccount);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(MyAccountPage.supportTicket);*/
			
			String  newSupportTicketID= MyAccountPage.supportTicketID + id + "']";
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(newSupportTicketID);
			
			
			if(driver.elementChecking.isElementDisplay(MyAccountPage.supportTicketLabel)) 
			{
				utils.setTrueResult("Success");	
			}
			else {
				utils.setFalseResult("Failed");
			}
			//need to verify on success or error base on text: Your profile has been updated
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error on viewSupportTicket method");
			screenshotPath = driver.elementEventControling.captureFullScreen();
		}
		
	}
	//view support ticket List
		public void NavigateToSupportTicketList() throws Exception {
			
			try {
				driver.waitControling.sleep(waitingTime);
				driver.elementEventControling.click(LoginPage.myAccount);
				
				driver.waitControling.sleep(waitingTime);
				driver.elementEventControling.click(MyAccountPage.supportTicket);
				
				if(driver.elementChecking.isElementDisplay(MyAccountPage.supportTicketListLabel)) 
				{
					utils.setTrueResult("Success");	
				}
				else {
					utils.setFalseResult("Failed");
				}
				//need to verify on success or error base on text: Your profile has been updated
				screenshotPath = driver.elementEventControling.captureFullScreen();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error on NavigateToSupportTicketList method");
				screenshotPath = driver.elementEventControling.captureFullScreen();
			}
			
		}
		//Add new message for support ticket
				public void supportTicketAddNewMsg(String msg) throws Exception {
					
					try {
						driver.waitControling.sleep(waitingTime);
						driver.elementEventControling.click(MyAccountPage.addNewMessage);
						
						driver.waitControling.sleep(waitingTime);
						driver.elementEventControling.type(MyAccountPage.newMessageMessage,msg);
						
						driver.waitControling.sleep(waitingTime);
						driver.elementEventControling.click(MyAccountPage.newMessageSubmitBtn);
						
						if(driver.elementChecking.isElementDisplay(MyAccountPage.supportTicketListLabel)) 
						{
							utils.setTrueResult("Success");	
						}
						else {
							utils.setFalseResult("Failed");
						}
						//need to verify on success or error base on text: Your profile has been updated
						screenshotPath = driver.elementEventControling.captureFullScreen();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Error on NavigateToSupportTicketList method");
						screenshotPath = driver.elementEventControling.captureFullScreen();
					}
					
				}

				//Navigate to Return History
				public void navigateToReturnHistory() throws Exception {
					
					try {
						driver.waitControling.sleep(waitingTime);
						driver.elementEventControling.click(LoginPage.myAccount);
						
						driver.waitControling.sleep(waitingTime);
						driver.elementEventControling.click(MyAccountPage.returnHistory);
						
						if(driver.elementChecking.isElementDisplay(MyAccountPage.returnHistoryLabel)) 
						{
							utils.setTrueResult("Success");	
						}
						else {
							utils.setFalseResult("Failed");
						}
						//need to verify on success or error base on text: Your profile has been updated
						screenshotPath = driver.elementEventControling.captureFullScreen();
						
					}  catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Error on navigateToReturnHistory method");
						screenshotPath = driver.elementEventControling.captureFullScreen();
					}
					
				}
				//View a return Order - RMA
				
				public void viewReturnOrder(String rma) throws Exception {
					
					try {
						
						MyAccountPage.returnHistoryRMA = MyAccountPage.returnHistoryRMA + rma + "']";
						
						driver.waitControling.sleep(waitingTime);
						driver.elementEventControling.click(MyAccountPage.returnHistoryRMA);
						
						
						if(driver.elementChecking.isElementDisplay(MyAccountPage.returnDetailsLable)) 
						{
							utils.setTrueResult("Success");	
						}
						else {
							utils.setFalseResult("Failed");
						}
						//need to verify on success or error base on text: Your profile has been updated
						screenshotPath = driver.elementEventControling.captureFullScreen();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Error on viewReturnOrder method");
						screenshotPath = driver.elementEventControling.captureFullScreen();
					}
					
				}
				
				
				

}
