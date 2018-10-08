package com.autotest.framework.library;

import java.io.File;
import java.text.MessageFormat;

import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.autotest.configure.Environment;
import com.autotest.configure.AutoTestConstants;
/*import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.Relative;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.sikuli.api.visual.Canvas;
import org.sikuli.api.visual.DesktopCanvas;*/
import com.autotest.utils.StackTraceInfo;

public class SikuliFunctions extends Environment {
	
	public SikuliFunctions() {
		
    }

    private final Screen scr = new Screen();
    private final float fSimilarityRate = (float) 0.6;
    
    public String getImagePath(String img) throws Exception {

//        try {
//            String imagePath = new File("").getAbsolutePath();
//            switch(modelName) {
//                case "storefront":
//                    imagePath = imagePath + AutoTestConstants.imgSikuli + ModelPath.storefront + "/" + img;
//                    break;
//                case "hmc":
//                    imagePath = imagePath + AutoTestConstants.imgSikuli + ModelPath.hmc + "/" + img;
//                    break;
//                case "cmscockpit":
//                    imagePath = imagePath + AutoTestConstants.imgSikuli + ModelPath.cmscockpit + "/" + img;
//                    break;
//                case "cscockpit":
//                    imagePath = imagePath + AutoTestConstants.imgSikuli + ModelPath.cscockpit + "/" + img;
//                    break;
//                case "productcockpit":
//                    imagePath = imagePath + AutoTestConstants.imgSikuli + ModelPath.productcockpit + "/" + img;
//                    break;
//                default:
//                    utils.setFalseResult(MessageFormat.format("Could not find image \"{0}\" on folder \"{1}\". Check test data again!!!", img, modelName));
//                    break;
//            }
//            imagePath = imagePath.replace("\\", "/");
//            log.info(MessageFormat.format("Path of image = \"{0}\" is: \"{1}\" ", img, imagePath));
//            return imagePath;
//        }
//        catch(Exception e) {
//            log.error(StackTraceInfo.getCurrentClassName());
//            log.error(StackTraceInfo.getCurrentFileName());
//            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
//            throw new Exception();
//        }
    	return null;

    }


    public boolean vrfImagePresent(String img) throws Exception {

        try {
            if (isImagePresent(img)) {
                log.info(MessageFormat.format("Image \"{0}\" is displayed", img));
                return true;
            }
            else {
                log.error(MessageFormat.format("Image \"{0}\" IS NOT displayed", img));
                return false;
            }
        }
        catch (Exception e) {
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
            throw new Exception();
        }

    }


    public void click(String img) throws Exception {

        try {
            if (isImagePresent(img)) {
                scr.click(getImagePath(img));
                log.info(MessageFormat.format("Clicked Image = \"{0}\"", img));
            }
            else {
                log.error(MessageFormat.format("Image= \"{0}\" is not presented", img));
                throw new Exception();
            }
        }
        catch (Exception e) {
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
            throw new Exception();
        }

    }


    public boolean isImagePresent(String img, int secs) throws Exception {

        try {
            Match match = scr.exists(getImagePath(img),secs);
            if (match == null) {
                log.error(MessageFormat.format("Image \"{0}\" is not presented on browser", img));
                return false;
            }
            else {
                log.info(MessageFormat.format("Image \"{0}\" is presented on browser", img));
                return true;
            }
        }
        catch (Exception e) {
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
            throw new Exception();
        }
    }


    public boolean isImagePresent(String img) throws Exception {

        try {
            Match match = scr.exists(getImagePath(img),6);
            if (match == null) {
                // find similarity pattern
                Pattern patternImg = new Pattern(getImagePath(img));
                if(scr.exists(patternImg.similar(fSimilarityRate), 6) != null) {
                    log.info(MessageFormat.format("Image \"{0}\" is presented on browser", img));
                    return true;
                }
                else {
                    log.error(MessageFormat.format("Image \"{0}\" IS NOT presented on browser", img));
                    return false;
                }
            }
            else {
                log.info(MessageFormat.format("Image \"{0}\" is presented on browser", img));
                return true;
            }
        }
        catch (Exception e) {
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
            throw new Exception();
        }
    }
}