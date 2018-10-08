package com.qsp.min;

import com.autotest.configure.Environment;

import Locators.*;

public class CheckoutFlows extends Environment{
	//=================Initialize==============================
	
	LoginFlows lf = new LoginFlows();
	SearchFlows sf = new SearchFlows(); 
	
	
	//======================Methods==========================
	
	//=======Add to cart===========================
	public void addtoCart() throws Exception {
		try {
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.addToCartPDP);
			//click checkout button
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.checkoutButton);
			
			driver.waitControling.sleep(waitingTime);
			
			screenshotPath = driver.elementEventControling.captureFullScreen();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("==== Error on addtoCart method====>");
			screenshotPath = driver.elementEventControling.captureFullScreen();
		}
	}
	
	
	//====add to cart PDP
	public void addtoCartPDP() {
		try {
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.addToCartPDP);
			//click checkout button
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.checkoutButton);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.proceedToCheckout);
			
			driver.waitControling.sleep(waitingTime);
			
			screenshotPath = driver.elementEventControling.captureFullScreen();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("==== Error on addtoCartPDP method====>");
		}
	}
	//====
	public void addtoCartProcessToCheckout() {
		try {
			//click checkout button
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.checkoutButton);
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.proceedToCheckout);
			
			driver.waitControling.sleep(waitingTime);
			
			screenshotPath = driver.elementEventControling.captureFullScreen();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("==== Error on addtoCartProcessToCheckout method====>");
		}
	}
	//Checkout step 1
	public void checkoutStep1_PaymentType(int data) {
		//cost center: //select[@id='costCenterSelect']//option[2]
		switch(data) {
		case 1:
			try {
				driver.waitControling.sleep(waitingTime);
				driver.elementEventControling.click(CheckoutPage.cartPaymentRadioBtn);
				driver.waitControling.sleep(waitingTime);
				driver.elementEventControling.type(CheckoutPage.PONumber, "19001009");
				driver.waitControling.sleep(waitingTime);
				driver.elementEventControling.click(CheckoutPage.NextOnPaymentTypeButton);
				driver.waitControling.sleep(waitingTime);
				screenshotPath = driver.elementEventControling.captureFullScreen();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				driver.waitControling.sleep(waitingTime);
				driver.elementEventControling.click(CheckoutPage.cartPaymentRadioBtn2);
				driver.waitControling.sleep(waitingTime);
				driver.elementEventControling.type(CheckoutPage.PONumber, "19001009");
				driver.waitControling.sleep(waitingTime);
				driver.elementEventControling.click(CheckoutPage.costCenter);
				driver.waitControling.sleep(waitingTime);
				driver.elementEventControling.click(CheckoutPage.costCenterCustomerRetail);
				driver.waitControling.sleep(waitingTime);
				driver.elementEventControling.click(CheckoutPage.NextOnPaymentTypeButton);
				driver.waitControling.sleep(waitingTime);
				screenshotPath = driver.elementEventControling.captureFullScreen();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("Error occured in CheckoutStep1_PaymentType method");
		}
	}
	
	//===============Checkout step 2==============
	public void checkoutStep2_ShippingAddress() throws Exception {
		try {
			
			driver.waitControling.sleep(waitingTime);
			if(driver.elementChecking.isElementDisplay(CheckoutPage.addressBookBtn)) {
				driver.elementEventControling.click(CheckoutPage.addressBookBtn);
				driver.waitControling.sleep(waitingTime);
				driver.elementEventControling.click(CheckoutPage.firstInAddressBook);
				driver.waitControling.sleep(waitingTime);
				screenshotPath = driver.elementEventControling.captureFullScreen();
				
			}
			else {
				System.out.println("Only have 1 address ==> Moving to next step...");
			}
		
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("==== Error on CheckoutStep2_ShippingAddress method====>");
			screenshotPath = driver.elementEventControling.captureFullScreen();
		}
	}
	//checkout Step 3
	public void checkoutStep3_ShippingMethod(int method) throws Exception {
		try {
			
			driver.waitControling.sleep(waitingTime);
			//checkout step 3
			//option[@value='standard-net']
			//option[@value='premium-net']
			switch(method) {
			case 1:
				driver.elementEventControling.click(CheckoutPage.ShippingMethodStandard);
				break;
			case 2 :
				driver.elementEventControling.click(CheckoutPage.ShippingMethodPremium);
				break;
			default:
				System.out.println("Error at checkoutStep3_ShippingMethod");
			}
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.nextOnShippingMethod);	
		
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			screenshotPath = driver.elementEventControling.captureFullScreen();
			System.out.println("=========Exception on checkoutStep3_ShippingMethod method============");
			e.printStackTrace();
		}

	}
	//Checkout Step 4 
	
	public void checkoutStep4_FinalReviewReplenishment() throws Exception {
		try {
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.termCheckbox);
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.scheduleAReplenishmentBtnStep4);
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			screenshotPath = driver.elementEventControling.captureFullScreen();
			System.out.println("=========Exception on checkoutStep4_FinalReview method============");
			e.printStackTrace();
		}

	}
	
	public void checkoutStep4_FinalReview() throws Exception {
		try {
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.termCheckbox);
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.placeOrderBtn);
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			screenshotPath = driver.elementEventControling.captureFullScreen();
			System.out.println("=========Exception on checkoutStep4_FinalReview method============");
			e.printStackTrace();
		}

	}
	
	//Step 4: Payment & Billing Address pay by card
	public void checkoutStep4_PaymentNewBillingAddress(String country, String title, String firstName, 
			String lastName, String add1, String city) throws Exception {
		try {
			
			driver.waitControling.sleep(waitingTime);
			
			driver.elementEventControling.click(CheckoutPage.sameAsShippingAddressCheckbox);
			
			driver.waitControling.sleep(waitingTime);
			
			driver.elementEventControling.click(CheckoutPage.BillingAddressCountry);
			
			CheckoutPage.BillingAddressCountryValue = CheckoutPage.BillingAddressCountryValue + country + "')]"; 
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.BillingAddressCountryValue);
			
			CheckoutPage.BillingAddressTitleValue = CheckoutPage.BillingAddressTitleValue + title + "')]";
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.BillingAddressTitleValue);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CheckoutPage.BillingAddressFirstName,firstName);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CheckoutPage.BillingAddressLastName,lastName);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CheckoutPage.BillingAddressAddLine1,add1);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CheckoutPage.BillingAddressTownCity,city);
		
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CheckoutPage.BillingAddressPostcode,"700000");
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.BillingAddressNextFinalReviewBtn);

			
			//cyberSourcePortal("Min", "Tran", "Etown", "Ho Chi Minh", "Tan Binh", "700000", "0989898989", "minmin@yopmail.com");
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.elementEventControling.captureFullScreen();
			System.out.println("=========Exception on checkoutStep4_PaymentNewBillingAddress method============");
			screenshotPath = driver.elementEventControling.captureFullScreen();
			e.printStackTrace();
		}

	}
	
	//step 4 pay by card no edit
	
	public void checkoutStep4_PaymentAndBillingAddress() throws Exception {
		try {
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.BillingAddressNextFinalReviewBtn);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			screenshotPath = driver.elementEventControling.captureFullScreen();
			System.out.println("=========Exception on checkoutStep4_PaymentAndBillingAddress method============");
			e.printStackTrace();
		}

	}
	//step 5 pay by card
	public void checkoutStep5_FinalReview() throws Exception {
		try {
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.termCheckbox);
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.placeOrderBtn);
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			screenshotPath = driver.elementEventControling.captureFullScreen();
			System.out.println("=========Exception on checkoutStep5_FinalReview method============");
			e.printStackTrace();
		}

	}
	
	public void checkoutStep5_FinalReviewReplenishment() throws Exception {
		try {
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.termCheckbox);
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.scheduleAReplenishmentInCheckoutStep);
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			screenshotPath = driver.elementEventControling.captureFullScreen();
			System.out.println("=========Exception on checkoutStep5_FinalReview method============");
			e.printStackTrace();
		}

	}
	
	//cybersource payment
	
	public void cyberSourcePortal(String firstName, String lastName, String address, String city, 
			String state, String zipCode, String phoneNuber, String email , String cardNum, String cardCVN) throws Exception {
		try {
			
			//driver.elementEventControling.click(CyberSourcePage.firstName);
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CyberSourcePage.firstName, firstName);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CyberSourcePage.lastName, lastName);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CyberSourcePage.address, address);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CyberSourcePage.city, city);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CyberSourcePage.country);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CyberSourcePage.countryVietnam);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CyberSourcePage.StateProvine, state);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CyberSourcePage.zipPostalCode, zipCode);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CyberSourcePage.phoneNumber, phoneNuber);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CyberSourcePage.email, email);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CyberSourcePage.nextBtn);
			
			//
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CyberSourcePage.cardTypeCheckbox);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CyberSourcePage.cardNumber, cardNum);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CyberSourcePage.cardCVN, cardCVN);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CyberSourcePage.cardMonth);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CyberSourcePage.cardMonth05);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CyberSourcePage.cardYear);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CyberSourcePage.cardYear21);
			
			if(driver.elementChecking.isElementDisplay(CyberSourcePage.errorCardNumMsg)) {
				utils.setFalseResult("Invalid card number");	
			}
			else {
				driver.waitControling.sleep(waitingTime);
				driver.elementEventControling.click(CyberSourcePage.nextBtn);
				
				driver.waitControling.sleep(waitingTime);
				driver.elementEventControling.click(CyberSourcePage.PayBtn);
				
				if(driver.elementChecking.isElementDisplay(CyberSourcePage.orderFailedMsg)) {
					
					utils.setFalseResult(driver.elementEventControling.getText(CyberSourcePage.orderFailedDetailMsg).toString());	
				}
				else {

					utils.setTrueResult("Success");
				}
			}
			
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
		} catch (Exception e) {
				driver.elementEventControling.captureFullScreen();
			// TODO Auto-generated catch block
			System.out.println("============Exception on cyberSource method=========");
			screenshotPath = driver.elementEventControling.captureFullScreen();
			e.printStackTrace();
		}
		
	}
	
	//===================Quick order================
	
	public void quickOrder(String product1, String product2, String product3) {
		try {
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.OrderImage);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.quickOrder);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CheckoutPage.productOne, product1);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.productTwo);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CheckoutPage.productTwo, product2);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.productOne);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CheckoutPage.productThree, product3);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.productTwo);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.addToCartQuickOrder);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.checkoutButton);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.proceedToCheckout);
			//=======Next login and checkout payment method
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("=========Exception on quickOrder method============");
			e.printStackTrace();
		}
		
	}
	
	
	//=============Checkout step 1 Payment Type============
	

	//Order form function
	public void orderFormBuyFirst3(String searhPhrase) {
		
		try {
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.OrderImage);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.orderForm);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.type(CheckoutPage.SearchOrderForm, searhPhrase);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.searchOrderFormBtn);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.orderFormFirstProduct);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.orderFormSecondProduct);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.orderFormThirdProduct);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.addToCartOrderForm);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.checkoutButton);
			
			driver.waitControling.sleep(waitingTime);
			if(driver.elementChecking.isElementDisplay(CheckoutPage.proceedToCheckout)) {
				driver.elementEventControling.click(CheckoutPage.proceedToCheckout);
				
				driver.waitControling.sleep(waitingTime);
				screenshotPath = driver.elementEventControling.captureFullScreen();
			}
			else {
				utils.setFalseResult("Not have enough stock to fulfill");
			}
		} catch (Exception e) {
			System.out.println("Error on OrderFormBuyFirst3 method");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void addNewSaveCart(String name, String description) {
		
		try {
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.saveCartNewCartBtn);
			//driver.elementEventControling.click(CheckoutPage.EmptysaveCartNewCartBtn);
			driver.elementEventControling.type(CheckoutPage.saveCartName, name);
			driver.elementEventControling.type(CheckoutPage.saveCartDescription, description);
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.saveCartSavePopupBtn);
			
			driver.waitControling.sleep(waitingTime);
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//View saved cart
	public void viewSavedCart(String name) throws Exception {
		
		try {
			driver.waitControling.sleep(waitingTime);
			
			driver.elementEventControling.click(CheckoutPage.saveCartSaveCartsBtn);
			
			driver.waitControling.sleep(waitingTime);
			
			CheckoutPage.saveCartViewCart = "//td//*[contains(text(),'"+ name +"')]";
			
			driver.elementEventControling.click(CheckoutPage.saveCartViewCart);
			
			screenshotPath = driver.elementEventControling.captureFullScreen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			screenshotPath = driver.elementEventControling.captureFullScreen();
			e.printStackTrace();
		}
	}
//Restore saved cart
public void RestoreSavedCart(String name) throws Exception {
		
		try {
			driver.waitControling.sleep(waitingTime);
			
			driver.elementEventControling.click(CheckoutPage.saveCartRestoreBtn);
			driver.elementEventControling.click(CheckoutPage.saveCartRestorePopupBtn);
			
			driver.waitControling.sleep(waitingTime);
			driver.elementEventControling.click(CheckoutPage.proceedToCheckout);
			screenshotPath = driver.elementEventControling.captureFullScreen();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			screenshotPath = driver.elementEventControling.captureFullScreen();
			e.printStackTrace();
		}
	}
	

//==========================================

public void scheduleAReplenishmentDaily(String day) throws Exception {
	
	try {
		
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(CheckoutPage.chooseReplenishmentDate);
		driver.waitControling.sleep(waitingTime);
		CheckoutPage.chooseADay = "//td[@data-handler='selectDay']//*[contains(text(),'"+day +"')]";
		driver.elementEventControling.click(CheckoutPage.chooseADay);
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(CheckoutPage.emptyclick);
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(CheckoutPage.scheduleDaily);
		driver.waitControling.sleep(waitingTime);
		//missing a step for input a day
		driver.elementEventControling.click(CheckoutPage.scheduleAReplenishmentBtn);
		driver.waitControling.sleep(waitingTime);
		screenshotPath = driver.elementEventControling.captureFullScreen();
		driver.waitControling.sleep(waitingTime);
	} catch (Exception e) 
		{
		// TODO Auto-generated catch block
		screenshotPath = driver.elementEventControling.captureFullScreen();
		e.printStackTrace();
		}
	}
//===========================Handle number of items in cart==========

public void inputProductItemsAmount(int position, int value) throws Exception {
	
	try {
		
		driver.waitControling.sleep(waitingTime);
		//checkout step 3
		driver.elementEventControling.click(CheckoutPage.nextOnShippingMethod);	
		
		//checkout step 4
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(CheckoutPage.termCheckbox);
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(CheckoutPage.scheduleAReplenishmentInCheckoutStep);
		driver.waitControling.sleep(waitingTime);
		driver.elementEventControling.click(CheckoutPage.chooseReplenishmentDate);
		
	} catch (Exception e) 
		{
		// TODO Auto-generated catch block
		screenshotPath = driver.elementEventControling.captureFullScreen();
		e.printStackTrace();
		}
	}


}
