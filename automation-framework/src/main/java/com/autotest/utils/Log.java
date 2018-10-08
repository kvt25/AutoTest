package com.autotest.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.autotest.configure.AutoTestConstants;

/**
 * @Name: Log
 * @author DatNguyen
 * @Description: This class implement log4j
 * @CreatedDate: 04/03/2014
 */
public class Log
{
    private final Logger log;
    public final String logLine = "======================================================";
    public final String tab = " >>> ";

    @SuppressWarnings("rawtypes")
    public Log(Class className)
    {
        log = Logger.getLogger(className);
        //configure();
    }

    public void configure()
    {
        String randomFile = AutoTestConstants.RESULT_FOLDER
                + "log.html";
        System.setProperty("randomFile", randomFile);

        PropertyConfigurator.configure("log4j.properties");
    }

    /**
     * Log Info
     * 
     * @param logMessage
     */
    public void info(String logMessage)
    {
        log.info(logMessage);
    }

    /**
     * Log Debug
     * 
     * @param logMessage
     */
    public void debug(String logMessage)
    {
        log.debug(logMessage);
    }

    /**
     * Log Warning
     * 
     * @param logMessage
     */
    public void warning(String logMessage)
    {
        log.warn(logMessage);
    }

    /**
     * Log error
     * 
     * @param logMessage
     */
    public void error(String logMessage)
    {
        log.error(logMessage);
    }

    /**
     * Log fatal
     * 
     * @param logMessage
     */
    public void fatal(String logMessage)
    {
        log.fatal(logMessage);
    }
}
