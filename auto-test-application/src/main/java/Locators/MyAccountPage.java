package Locators;

public class MyAccountPage {

	//Personal details
	public static String personalDetails ="//li[@class='yCmsComponent active']//a[@href='/qspb2bstorefront/qspb2b/en/USD/my-account/update-profile']";
	
	public static String title ="//select[@id='profile.title']";
	public static String newtitle ="//select[@id='profile.title']//option[@value='mr']";
/*	public static String titleMrs ="//select[@id='profile.title']//option[@value='mrs']";
	public static String titleMiss ="//select[@id='profile.title']//option[@value='miss']";
	public static String titleMs ="//select[@id='profile.title']//option[@value='ms']";
	public static String titleDr ="//select[@id='profile.title']//option[@value='dr']";
	public static String titleRev ="//select[@id='profile.title']//option[@value='rev']";*/
	
	public static String firstName ="//input[@id='profile.firstName']";
	public static String lastName ="//input[@id='profile.lastName']";
	
	public static String update ="//button[@class='btn btn-primary btn-block']";
	public static String cancel ="//button[@class='btn btn-default btn-block backToHome']";
	
	//Email address
	public static String emailDetails ="//li[@class='yCmsComponent UpdateEmailLink']//a[@href='/qspb2bstorefront/qspb2b/en/USD/my-account/update-email']";
	public static String newEmail ="//input[@id='profile.email']";
	public static String newEmailConfirm ="//input[@id='profile.checkEmail']";
	public static String emailPassword ="//input[@id='profile.pwd']";
	
	public static String updateEmailBtn ="//button[@class='btn btn-primary btn-block']";
	public static String cancelUpdateEmailBtn ="//button[@class='btn btn-default btn-block backToHome']";
	
	
	//Payment details
	public static String paymentDetails ="//li[@class='yCmsComponent PaymentDetailsLink active']//a[@href='/qspb2bstorefront/qspb2b/en/USD/my-account/payment-details']";
	public static String deletePayment ="//div[@class='col-xs-12 col-sm-6 col-md-4 card'][1]//a[@class='action-links removePaymentDetailsButton']";
	
	public static String deletePaymentBtnA ="//div[@id='cboxWrapper']//input[@name='paymentInfoId']";
	public static String deletePaymentBtnFinal ="//form[@id='removePaymentDetails']//button[@class='btn btn-default btn-primary btn-block paymentsDeleteBtn']";
	
	public static String AccountPageCompletionMsg = "//div[@class='alert alert-info alert-dismissable']";
	
	//Personal detail
	
	public static String frirstNameError = "//span[@id='firstName.errors']";
	public static String lastNameError = "//span[@id='lastName.errors']";
	
	//Update password
	
	public static String password = "//li[@class='yCmsComponent']//a[@title='Password']";
	
	public static String currentPassword = "//input[@id='currentPassword']";
	public static String newPassword = "//input[@id='newPassword']";
	public static String checkNewPassword = "//input[@id='checkNewPassword']";
	public static String updatePasswordBtn = "//div[@class='accountActions']//button[@type='submit']";
	
	
	//Order history
	public static String orderHistoryLable = "//span[@class='account-section-header']";
	public static String orderHistory = "//li[@class='yCmsComponent']//a[@title='Order History']";
	//a order to view
	public static String anOrder = "//td[@class='responsive-table-cell']//a[@href='/qspb2bstorefront/qspb2b/en/USD/my-account/order/";
	
	public static String orderHistoryFirstOrder = "(//div[@class='responsive-table']//td)[2]";
	
	
	
	public static String reorderBtn = "//div[@class='AccountOrderDetailsOverviewComponent-ReorderAction']//button[@id='reorderButton']";
	//after click reorder then perform checkout step 4: final review

	public static String cancelOrderBtn = "//div[@class='AccountOrderDetailsOverviewComponent-CancelOrderAction']//button[@id='cancelOrderButton']";
	public static String secureCheckoutHeadline = "//div[@class='checkout-headline']";
	
	//address books
	
	public static String addressBook = "//li[@class='yCmsComponent']//a[@title='Address Book']";
	
	public static String updateAddressBackBtn = "//button[@class='addressBackBtn btn btn-back']";
	
	public static String updateAddressCountry = "//select[@id='address.country']";
	public static String updateAddressTitle = "//select[@id='address.title']";
	public static String updateAddressFirstName = "//select[@id='address.firstName']";
	public static String updateAddressLastName = "//select[@id='address.surname']";
	public static String updateAddressLine1 = "//select[@id='address.line1']";
	public static String updateAddressLine2 = "//select[@id='address.line2']";
	public static String updateAddressTownCity = "//select[@id='address.townCity']";
	public static String updateAddressPostcode = "//select[@id='address.postcode']";
	public static String updateAddressPhone = "//select[@id='address.phone']";
	public static String updateAddressSaveBtn = "//button[contains(text(),'Save')]";
	public static String updateAddressCancelBtn = "//a[@href='/qspb2bstorefront/qspb2b/en/USD/my-account/address-book']";
	
	//========================Quotes========================
	public static String quotes = "//li[@class='yCmsComponent']//a[@title='Quotes']";
	
	public static String quotesRequestInCart = "//button[contains(text(),'Request a Quote')]";
	//product with high price: 8000401, 8000403
	public static String quotesSubmitBtn = "//button[contains(text(),'Submit Quote')]";
	
	public static String quotesDescription ="//input[@id='js-quote-description']";
	public static String quotesComment ="//textarea[@id='comment']";
	
	public static String quotesPopupYesBtn = "//button[@id='submitYesButton']";
	
	public static String quotesNumber = "//a[contains(text(),'";
	
	public static String quotesDetailsHeadline = "//div[@class='back-link']//span";
	
	public static String quotesQuantity = "//div[@class='item__quantity hidden-xs hidden-sm']//input[@class='form-control js-update-entry-quantity-input']";
	
	public static String quotesName = "//div[@id='js-quote-submit-modal']//p[@class='modal__top--text js-modal-quote-name']";
	
	
	////Support ticket
	public static String supportTicket = "//li[@class='yCmsComponent']//a[@title='Support Tickets']";
	
	public static String addSupportTicket = "//a[@href='add-support-ticket']";
	public static String addSupportTicketSubject = "//input[@id='createTicket-subject']";
	public static String addSupportTicketMessage = "//textarea[@id='createTicket-message']";
	//attachmentFiles
	public static String addSupportTicketChooseFile = "//input[@id='attachmentFiles']";
	
	public static String addSupportTicketAssociateTo = "//select[@id='associatedTo']";
	
	//associateTo value
	////option[@value='Order=00009113'][1]
	public static String addSupportTicketAssociateToValue = "//option[@value='Order=";
	//ticketCategory
	public static String addSupportTicketCategory = "//select[@id='ticketCategory']";
	//option category  ENQUIRY  COMPLAINT PROBLEM
	public static String addSupportTicketCategoryValue = "//option[@value='";
	
	public static String addSupportTicketAddBtn = "//button[@id='addTicket']";
	
	//Thank you for your customer support request. Your message has been sent to one of our Customer Service Agents who will contact you shortly.
	//View support ticket
	public static String supportTicketID = "//a[@href='/qspb2bstorefront/qspb2b/en/USD/my-account/support-ticket/";
	//latest support ticket 
	public static String supportTicketLatest = "//tr[@class='responsive-table-item'][1]//td//a[@class='responsive-table-link ticket-id']";
	
	//verification label
	public static String supportTicketLabel = "//span[contains(text(),'Update Support Ticket')]";
	//verification list label
	public static String supportTicketListLabel = "//span[contains(text(),'Support Ticket')]";
	
	//==============New message=============
	public static String addNewMessage = "//a[contains(text(),'Add New Message')]";
	
	public static String newMessageMessage = "//textarea[@id='message']";
	
	public static String newMessageStatus = "//select[@id='status']";
	
	public static String newMessageStatusValue = "//select[@id='status']//option[";
	
	public static String newMessageSubmitBtn = "//button[@id='updateTicket']";
	
	public static String newMessageChoseFile = "//button[@id='attachmentFiles']";
	
	//Thank you for your customer support request. Your message has been sent to one of our Customer Service Agents who will contact you shortly.
	
	//========Return History=============
	
	public static String returnHistory = "//div[@class='yCmsContentSlot']//a[@title='Returns History']";
	//00003000
	public static String returnHistoryRMA = "//a[@href='/qspb2bstorefront/qspb2b/en/USD/my-account/returns/";
	public static String returnHistoryLabel = "//div[@class='account-section-header support-ticket-header']//div[contains(text(),'Returns History')]";
	public static String returnDetailsLable = "//span[contains(text(),'Return Details')]";
	
}
