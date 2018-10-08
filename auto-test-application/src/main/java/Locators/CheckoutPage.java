package Locators;

public class CheckoutPage {

	
	//public static String allProduct = "//div[@class='text-all-product']";
	public static String addToCartPDP ="//button[@id='addToCartButton']";
	public static String productToAddToCart ="//form[@id='addToCartForm8000102']//button[@class='btn btn-primary btn-block js-enable-btn']";
	
	public static String checkoutButton = "//*[@class='btn btn-primary btn-block add-to-cart-button']";
	public static String proceedToCheckout = "//button[@class='btn btn-primary btn-block btn--continue-checkout js-continue-checkout-button']";
	
	//1.Payment Type
	public static String cartPaymentRadioBtn = "//div[@class='step-body']//label[@for='PaymentTypeSelection_CARD']";
	public static String cartPaymentRadioBtn2 = "//div[@class='step-body']//label[@for='PaymentTypeSelection_ACCOUNT']";
	
	public static String costCenter ="//select[@id='costCenterSelect']";
	public static String costCenterCustomerRetail="//select[@id='costCenterSelect']//option[2]";
	
	public static String PONumber = "//input[@name='purchaseOrderNumber']";
	public static String NextOnPaymentTypeButton = "//button[@id='choosePaymentType_continue_button']";
	
	//2.Shipping Address
	
	public static String addressBookBtn = "//button[@class='btn btn-default btn-block js-address-book']";
	public static String firstInAddressBook = "//div[1][@class='addressEntry']//button[@class='btn btn-primary btn-block']";
	
	//3.Shipping Method
	public static String ShippingMethodStandard="//select[@id='delivery_method']//option[@value='standard-net']";
	public static String ShippingMethodPremium="//select[@id='delivery_method']//option[@value='premium-net']";
	public static String nextOnShippingMethod="//button[@id='deliveryMethodSubmit']";//Final Review Button
	
	
	//4. Payment & Billing Address
	public static String nextOnPaymentAndBillingAddress="//button[@id='deliveryMethodSubmit']";
	//--Billing Address
	//Same As Shipping Address checkbox
	public static String sameAsShippingAddressCheckbox="//div[@class='checkbox']//input[@id='useDeliveryAddress']";
	public static String BillingAddressCountry="//select[@id='address.country']";
	public static String BillingAddressCountryValue="//option[contains(text(),'";
	
	public static String BillingAddressTitle="//select[@id='address.title']";
	public static String BillingAddressTitleValue="//option[contains(text(),'";
	
	public static String BillingAddressFirstName="//input[@id='address.firstName']";
	public static String BillingAddressLastName="//input[@id='address.surname']";
	public static String BillingAddressAddLine1="//input[@id='address.line1']";
	public static String BillingAddressAddLine2="//input[@id='address.line2']";
	public static String BillingAddressTownCity="//input[@id='address.townCity']";
	public static String BillingAddressPostcode="//input[@id='address.postcode']";
	public static String BillingAddressPhone="//input[@id='address.phone']";
	
	public static String BillingAddressNextFinalReviewBtn="//button[@id='paymentModeSubmit']";
	

	
	
	
	//5.Final Review
	public static String termCheckbox ="//input[@id='Terms1']";
	public static String placeOrderBtn="//button[@id='placeOrder']";
	
	
	//after click place order button need to wait about 15s

	//===================================================================
	
	// Quick Order
	
	public static String OrderImage = "//div[@class='col-sm-15 quik-order-block js-btn-all-product']";
	public static String quickOrder = "//a[@href='/qspb2bstorefront/qspb2b/en/USD/quickOrder']";
	public static String productOne = "//li[@class='item__list--item js-li-container'][1]//input[@class='js-sku-input-field form-control']";
	public static String productTwo = "//li[@class='item__list--item js-li-container'][2]//input[@class='js-sku-input-field form-control']";
	public static String productThree = "//li[@class='item__list--item js-li-container'][3]//input[@class='js-sku-input-field form-control']";
	
	public static String resetForm = "//button[@id='js-reset-quick-order-form-btn-bottom']";
	
	public static String addToCartQuickOrder = "//button[@id='js-add-to-cart-quick-order-btn-bottom']";
	
	//Same with above
	//public static String requestAQuote = "//button[@class='btn btn-primary btn-block btn--request-a-quote js-continue-shopping-button']";
	
	//=======================================================================
	//Order form
	public static String orderForm = "//a[@href='/qspb2bstorefront/qspb2b/en/USD/search/advanced']";
	public static String SearchOrderForm ="//input[@id='js-product-ids']";
	public static String searchOrderFormBtn ="//div[@class='searchInput input-group']//button[@class='btn btn-block btn-primary positive adv_search_button']";
	////li[@class='item__list--item'][1]//span[@class='glyphicon glyphicon-plus js-order_form-item__quantity_plus']
	public static String orderFormFirstProduct = "//li[@class='item__list--item'][1]//span[@class='glyphicon glyphicon-plus js-order_form-item__quantity_plus']";
	
	public static String orderFormSecondProduct = "//li[@class='item__list--item'][2]//span[@class='glyphicon glyphicon-plus js-order_form-item__quantity_plus']";
	
	public static String orderFormThirdProduct = "//li[@class='item__list--item'][3]//span[@class='glyphicon glyphicon-plus js-order_form-item__quantity_plus']";
	
	public static String addToCartOrderForm ="//button[@id='js-add-to-cart-order-form-btn-bottom']";

	//=================================Cart==========================================
	//Promotion on checkout page
	public static String couponCode ="//input[@id='js-voucher-code-text']";
	public static String couponCodeApplyBtn ="//input[@id='js-voucher-apply-btn']";
	public static String orderDiscountLable ="//div[@class='col-xs-6 cart-totals-left discount']";
	public static String couponCodeSuccessLable ="//div[@class='js-voucher-validation-container help-block cart-voucher__help-block']";
	//Save cart
	
	public static String saveCartNewCartBtn ="//div[@class='container-fluid cart-page-wrap']//a[@class='new__cart--link cart__head--link";
	public static String EmptysaveCartNewCartBtn ="//div[@id='cboxTitle']";
	public static String saveCartName ="//input[@id='saveCartName']";
	public static String saveCartDescription ="//textarea[@id='saveCartDescription']";
	public static String saveCartSavePopupBtn ="//button[@id='saveCartButton']";
	public static String saveCartSaveCartsBtn = "//div[@class='container-fluid cart-page-wrap']//a[@href='/qspb2bstorefront/qspb2b/en/USD/my-account/saved-carts']";
	//view cart
	public static String saveCartViewCart = "//td//*[contains(text(),'')]";
	
	public static String saveCartRestoreBtn = "//button[@id='restoreButton']";
	public static String saveCartRestorePopupBtn = "//button[@class='js-save-cart-restore-btn btn btn-primary btn-block']";
	
	//===========================================================================
	//Schedule a replenishment
	
	public static String scheduleAReplenishmentInCheckoutStep = "//div[@class='step-body']//button[@id='scheduleReplenishment']";
	public static String chooseReplenishmentDate ="//i[@class='glyphicon glyphicon-calendar js-open-datepicker']";
	
	public static String chooseADay ="//td[@data-handler='selectDay']//*[contains(text(),'')]";
	
	public static String scheduleDaily ="//div[@class='replenishmentFrequency']//input[@id='replenishmentFrequencyD']";
	public static String scheduleWeekly ="//input[@id='replenishmentFrequencyW']";
	public static String scheduleMonthly ="//input[@id='replenishmentFrequencyM']";
	
	public static String scheduleAReplenishmentBtn ="//button[@id='placeReplenishmentOrder']";
	public static String scheduleAReplenishmentBtnStep4 ="(//button[@id='scheduleReplenishment'])[1]";
	
	public static String emptyclick ="//div[@id='cboxLoadedContent']";
}
