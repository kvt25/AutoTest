package Locators;

public class BackOfficePage {
	
public static String urlBO = "https://qspb2b-dev.sai-digital.com/backoffice/";
public static String username = "//input[@name='j_username']";
public static String password = "//input[@name='j_password']";
public static String loginBtn = "//button[contains(text(),'Login')]";
//===============Tree================

//expand view Tree
public static String expandViewTree = "(//i[@class='z-south-icon z-icon-caret-down'])[1]";

public static String marketing = "//span[contains(text(),'Marketing')]";
public static String promotionRules = "//span[contains(text(),'Promotion Rules')]";
//--
public static String showPublishedAndUnpublished = "//span[contains(text(),'Show Published and Unpublished')]";
public static String showAll = "//span[contains(text(),'Show All')]";
public static String promotionRulesCreateNew = "(//img[contains(@src,'icon_action_add_default.png')])[1]";

public static String promotionRulesCreateNewCode = "(//input[@class='ye-input-text ye-com_hybris_cockpitng_editor_defaulttext z-textbox'])[4]";

public static String promotionRulesCreateNewName = "(//input[@class='ye-input-text ye-com_hybris_cockpitng_editor_defaulttext z-textbox'])[5]";

public static String promotionRulesCreateNewDoneBtn = "//button[contains(text(),'Done')]";
//search Promotion rules
public static String promotionRulesSearch = "//input[@class='z-bandbox-input z-bandbox-rightedge']";
public static String promotionRulesSearchBtn = "//button[@class='yw-textsearch-searchbutton y-btn-primary z-button']";
//first result
public static String promotionRulesSearchFirstResult1 = "(//span[contains(text(),'";
public static String promotionRulesSearchFirstResult2 = "')])[1]";

public static String promotionRulesExpandView = "(//button[@class='yw-expandCollapse z-button'])[1]";


}
