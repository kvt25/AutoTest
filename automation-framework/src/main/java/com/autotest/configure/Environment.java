package com.autotest.configure;

import org.junit.Assert;

import com.autotest.framework.library.CommonFunctions;
import com.autotest.framework.library.JQueryFunctions;
import com.autotest.framework.library.SeleniumFunctions;
import com.autotest.framework.library.SikuliFunctions;
import com.autotest.framework.library.VerifyFunctions;
import com.autotest.utils.ExcelHandler;
import com.autotest.utils.Log;
import com.autotest.utils.AutoTestUtils;


public class Environment
{
    public static SikuliFunctions sikuliFunc;
        
    
    public static Log log = new Log(Environment.class);
    
    // init Framework Library
    public static CommonFunctions driver = new CommonFunctions();
    public static JQueryFunctions jqueryFunc = new JQueryFunctions(driver);
    public static SeleniumFunctions selFunc = new SeleniumFunctions(driver);
    public static VerifyFunctions verifyFunc =  new VerifyFunctions();
    //public static SikuliFunctions sikuliFunc = new SikuliFunctions();
    
    //new added for waiting time.
    public static int waitingTime = 500;
    
}
