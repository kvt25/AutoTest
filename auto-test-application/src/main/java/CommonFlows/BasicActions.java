package CommonFlows;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.hamcrest.core.IsNull;

import com.autotest.configure.Environment;
import com.autotest.utils.Utils;

public class BasicActions extends Environment{

	//Initialize 
	 public boolean excelReadableRunStep(String clsName, String stepName, String[] param) throws Exception
	    {
		 //System.out.println("==============Running excelReadableRunStep method===========>");
		 
	        boolean isExec = false;
	        try
	        {
	            // Init class instance
	            Class<?> cls = Class.forName(clsName);
	            Object obj = cls.newInstance();
	            Method[] tempmethod = cls.getDeclaredMethods();

	            ArrayList<String> listMethod = new ArrayList<String>();
	            for (int i = 0; i < tempmethod.length; i++)
	            {
	                listMethod.add(tempmethod[i].getName());
	            }

	            // Invoke method
	            if (listMethod.contains(stepName))
	            {
	                Method method = null;
	                if (param.length == 0)
	                {
	                    try
	                    {
	                        method = cls.getDeclaredMethod(stepName);
	                        method.invoke(obj);
	                    }
	                    catch (Exception e)
	                    {
	                       System.out.println("Cannot invoke method with null param: " + e.getMessage());
	                       utils.setFalseResult("Cannot invoke method with null param: " + e.getMessage());
	                    }
	                }
	                else
	                {
	                    try
	                    {
	                        method = cls.getDeclaredMethod(stepName, param.getClass());
	                        method.invoke(obj, (Object) param);
	                    }
	                    catch (Exception e)
	                    {
	                    	System.out.println("Cannot invoke method: " + e.getMessage());
	                    	utils.setFalseResult("Cannot invoke method: " + e.getMessage());
	                    }
	                }
	                isExec = true;
	            }
	            else
	            {
	            	System.out.println("Không tìm thấy stepName: " + stepName + " !?!");
	            	utils.setFalseResult("Không tìm thấy stepName: " + stepName + " !?!");
	            }
	            return isExec;
	        }
	        catch (Exception e)
	        {
	        	System.out.println(e.getMessage());
	        	utils.setFalseResult(e.getMessage());
	        }
			return isExec;
	    }
}
