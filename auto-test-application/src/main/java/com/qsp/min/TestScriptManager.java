package com.qsp.min;

import com.qsp.min.*;


public class TestScriptManager {

	//======###############==LOGIN==###########================
	LoginFlows lf = new LoginFlows();
	NavigationStorefront nv = new NavigationStorefront();
	CheckoutFlows cf = new CheckoutFlows();
	SearchFlows sf = new SearchFlows();
	MyAccount ma = new MyAccount();
	BackOffice bo = new BackOffice();
	
	public void startTheBrowser(String ...data) throws Exception{
		nv.startTheBrowser(data[0]);
	}
	
	public void goToHomePage() throws Exception{
		nv.goToHomePage();
	}
	//===============================
	
	
	public void signIn(final String... data) throws Exception {
		lf.signIn(data[0], data[1]);
	}
	//================================
	public void signOut() throws Exception {
		lf.signOut();
	}
	
	
	
	//===============####===Search Flow===###=================
	public void searhProductByName(String... data) throws Exception {
		sf.searhProductByName(data[0]);
	}
	
	//====================
	public void addtoCartPDP() throws Exception {
		cf.addtoCartPDP();
	}
	
	public void addtoCart() throws Exception {
		cf.addtoCart();
	}
	
	public void scheduleAReplenishmentDaily(String... data) throws Exception {
		cf.scheduleAReplenishmentDaily(data[0]);
	}
	
	//===============####===Quick Order Flow===###=================
	public void quickOrder(String...data) {
		cf.quickOrder(data[0], data[1], data[2]);
	}
	
	//Checkout flows
	public void checkoutStep1_PaymentType(String... data) throws Exception {
		cf.checkoutStep1_PaymentType(Integer.parseInt(data[0]));
	}
	
	public void checkoutStep2_ShippingAddress() throws Exception {
		cf.checkoutStep2_ShippingAddress();
	}
	
	public void checkoutStep3_ShippingMethod(String... data) throws Exception {
		cf.checkoutStep3_ShippingMethod(Integer.parseInt(data[0]));
	}
	
	public void checkoutStep4_FinalReview() throws Exception {
		cf.checkoutStep4_FinalReview();
	}
	
	public void checkoutStep4_FinalReviewReplenishment() throws Exception {
		cf.checkoutStep4_FinalReviewReplenishment();
	}
	
	public void checkoutStep4_PaymentNewBillingAddress(String...data) throws Exception {
		cf.checkoutStep4_PaymentNewBillingAddress(data[0],data[1],data[2],data[3],data[4],data[5]);
	}
	
	public void checkoutStep4_PaymentAndBillingAddress() throws Exception {
		cf.checkoutStep4_PaymentAndBillingAddress();
	}
	
	public void checkoutStep5_FinalReview() throws Exception {
		cf.checkoutStep5_FinalReview();
	}
	
	public void checkoutStep5_FinalReviewReplenishment() throws Exception {
		cf.checkoutStep5_FinalReviewReplenishment();
	}
	
	//Cybersource
	public void cyberSourcePortal(String...data) throws Exception {
		cf.cyberSourcePortal(data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9]);
	}
	
	
	
	//=======================Order Form First ===========================
	
	public void orderFormBuyFirst3(String...data) throws Exception {
		cf.orderFormBuyFirst3(data[0]);
	}
	//========================Save cart=================================

	
	public void addNewSaveCart(String...data) throws Exception {
		cf.addNewSaveCart(data[0], data[1]);
	}
	
	public void viewSavedCart(String...data) throws Exception {
		cf.viewSavedCart(data[0]);
	}
	
	//=======================Delete payment detaiil======================
	
	public void deletePaymentDetails(String...data) throws Exception {
		ma.deletePaymentDetails(data[0]);
	}
	
	//==========================Edit Personal Details=======================
	public void editPersonalDetails(String...data) throws Exception {
		ma.editPersonalDetails(data[0], data[1], data[2]);
	}
	
	public void editEmailAddress(String...data) throws Exception {
		ma.editEmailAddress(data[0], data[1]);
	}

	//======================Update password=================================
	
	public void updatePassword(String...data) throws Exception {
		ma.updatePassword(data[0], data[1]);
	}
	
	//=====================================viewOrderHistory=========================
	public void viewOrderHistory(String...data) throws Exception {
		ma.viewOrderHistory(data[0]);
	}
	
	//==================View quotes
	public void viewQuotes(String...data) throws Exception {
		ma.viewQuotes(data[0]);
	}
	
	public String createQuotes(String...data) throws Exception {
		return ma.createQuotes(data[0]);
	}
	
	//support ticket
	
	public void addSupportTicket(String...data) throws Exception {
		ma.addSupportTicket(data[0],data[1],data[2],data[3]);
	}
	
	public void viewSupportTicket(String...data) throws Exception {
		ma.viewSupportTicket(data[0]);
	}
	
	
	public void NavigateToSupportTicketList() throws Exception {
		ma.NavigateToSupportTicketList();
	}
	
	public void supportTicketAddNewMsg(String...data) throws Exception {
		ma.supportTicketAddNewMsg(data[0]);
	}
	
	//Return order
	
	public void navigateToReturnHistory() throws Exception {
		ma.navigateToReturnHistory();
	}
	
	public void viewReturnOrder(String...data) throws Exception {
		ma.viewReturnOrder(data[0]);
	}
	
	//=====================================BO===================
	
	public void NavigateToBO() throws Exception {
		bo.NavigateToBO();
	}
	
	public void loginBO(String...data) throws Exception {
		bo.loginBO(data[0],data[1]);
	}
	
	public void viewPromotionRule(String...data) throws Exception {
		bo.viewPromotionRule(data[0]);
	}
}


