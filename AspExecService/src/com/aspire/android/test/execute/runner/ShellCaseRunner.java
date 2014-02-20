/**
 * @(#) ShellCaseRunner.java Created on 2012-4-28
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.runner;

import java.io.IOException;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.execute.CommandConstants;
import com.aspire.android.test.execute.ContentValues;
import com.aspire.android.util.DeviceUtil;

/**
 * The class <code>ShellCaseRunner</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class ShellCaseRunner implements ICaseRunner {

    /**
     * Constant
     */
    private static final String DOT = ".";
    private static final String EMPTY = "";

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.execute.runner.ICaseRunner#runCase(com.aspire.android.test.execute.ContentValues,
     *      com.aspire.android.test.execute.ContentValues, com.aspire.android.test.execute.ContentValues)
     */
    public boolean runCase(ContentValues setting, ContentValues globalVariables, ContentValues params)
            throws MException {
        String caseClassName = params.getAsString(CommandConstants.KEY_CASE_CLASSNAME);
        if (caseClassName == null) {
            throw new MException("Case ClassName is null");
        }

        String casePackageName = EMPTY;
        if (caseClassName.lastIndexOf(DOT) > 0) {
            casePackageName = caseClassName.substring(0, caseClassName.lastIndexOf(DOT));
        }

        String command = "am instrument -w -e class " + caseClassName + "#testStart " + casePackageName
                + "/android.test.InstrumentationTestRunner";
        try {
            String result = DeviceUtil.execute(command);
            return result != null && result.contains("OK (1 test)");
        } catch (IOException e) {
            throw new MException(e);
        } catch (InterruptedException e) {
            throw new MException(e);
        }
    }

}
