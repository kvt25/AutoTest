package com.autotest.framework.library;

import com.autotest.configure.Environment;
import com.autotest.utils.StackTraceInfo;

public class VerifyFunctions extends Environment {

    public void isFalse(boolean bCondition, String failReason) throws Exception {
        try {
            if(!bCondition) {
                utils.setFalseResult(failReason);
            }
        }
        catch (Exception e) {
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
            throw new Exception();
        }
    }

    public void isTrue(boolean bCondition, String actual) throws Exception {

        try {
            if(bCondition) {
                utils.setTrueResult(actual);
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
