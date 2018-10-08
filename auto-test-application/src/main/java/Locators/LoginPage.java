package Locators;

public class LoginPage {
	//Search
	public static String searchBox = "//input[@id='js-site-search-input']";
	public static String searchButton ="//button[@class='btn btn-link js_search_button']//span[@class='glyphicon glyphicon-search']";
	public static String searchFirstProduct = "//ul[@class='product__listing product__grid']//div[@class='col-sm-4'][1]";
	//--------
	
	public static String searchNoResultMsg ="//div[@class='search-empty']//div[@class='headline']";
	//Products found
	public static String searchHaveResultMsg ="//div[@class='pagination-bar-results']";
	
	
	//Login
	public static String loggedInLable = "//h6[@class='title title-drodown']//a[@href='/qspb2bstorefront/qspb2b/en/USD/login']";
	public static String homeUrl = "https://qspb2b-dev.sai-digital.com/qspb2bstorefront/?site=qspb2b&clear=true";
	public static String loginLink = "//a[@href='/qspb2bstorefront/qspb2b/en/USD/login']";
	public static String loginButton = "//*[contains(text(),'Log In')]";
	public static String emailAdress = "//input[@id='j_username']";
	public static String passWord = "//input[@id='j_password']";
	//Logout
	public static String hiText = "//h6[@class='title title-drodown']";
	public static String myAccount = "//div[@class='dropdown account-dropdown']//button";		
	public static String logoutLink = "//a[@href='/qspb2bstorefront/qspb2b/en/USD/logout']";
	//Forgot password
	public static String forgotPass = "//a[@class='js-password-forgotten btn btn-block btn-forgot-pw']";
	public static String forgotEmail = "//input[@id='forgottenPwd.email']";
	public static String forgotResetButton = "//div[@class='control-group']//*[contains(text(),'Reset Password')]";
	
	//My account
	public static String personalDetails ="//a[@href='/qspb2bstorefront/qspb2b/en/USD/my-account/update-profile']";
	public static String emailAddress = "//a[@href='/qspb2bstorefront/qspb2b/en/USD/my-account/update-email']";
	
	public static String paymentDetails ="//a[@href='/qspb2bstorefront/qspb2b/en/USD/my-account/payment-details']";
	//-------Message
	public static String loginPageCompletionMsg = "//div[@class='alert alert-danger alert-dismissable']";
	

	
	
	
	
	
}
