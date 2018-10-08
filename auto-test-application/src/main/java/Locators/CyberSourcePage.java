package Locators;

public class CyberSourcePage {
	public static String firstName = "//input[@id='bill_to_forename']";
	public static String lastName = "//input[@id='bill_to_surname']";
	public static String address = "//input[@id='bill_to_address_line1']";
	public static String city = "//input[@id='bill_to_address_city']";
	public static String country = "//select[@id='bill_to_address_country']";
	public static String countryVietnam = "//select[@id='bill_to_address_country']//option[@value='VN']";
	
	public static String StateProvine = "//input[@id='bill_to_address_state']";
	public static String zipPostalCode = "//input[@id='bill_to_address_postal_code']";
	public static String phoneNumber = "//input[@id='bill_to_phone']";
	public static String email = "//input[@id='bill_to_email']";
	public static String nextBtn = "//input[@name='commit']";
	
	public static String cardTypeCheckbox = "//input[@name='card_type']";
	
	public static String cardNumber = "//input[@id='card_number']";
	
	public static String cardCVN = "//input[@id='card_cvn']";
	
	public static String cardMonth = "//select[@id='card_expiry_month']";
	
	public static String cardMonth05 = "//select[@id='card_expiry_month']//option[@value='05']";
	
	public static String cardYear = "//select[@id='card_expiry_year']";
	
	public static String cardYear21 = "//select[@id='card_expiry_year']//option[@value='2021']";
	
	public static String nextBtnPayment = "//input[@name='commit']";
	
	public static String PayBtn = "//input[@value='Pay']";
	
	public static String errorCardNumMsg = "//div[contains(text(),'Enter a valid card number')]";
	
	public static String orderFailedMsg = "//div[@class='checkout-success']//span[contains(text(),'Transaction failed')]";
	
	public static String orderFailedDetailMsg = "//p[contains(text(),'is failed')]";
	
	
	

}
