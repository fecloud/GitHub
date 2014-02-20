package com.example.resourcepaser;

import android.test.AndroidTestCase;
import android.util.Log;

import com.aspire.android.test.execute.config.CustomizeResource;
import com.aspire.android.test.execute.config.OcrParam;

public class MainTest extends AndroidTestCase {

    private static final String TAG = "MainTest";

    public void test_Main() {
         CustomizeResource resource = CustomizeResource.getInstance();
         final OcrParam ocrParam = resource.getOcrParam("DCD_AddChannel_MT_DATA", "HuaweiT2211");
         Log.d(TAG, ocrParam.toString());
        // new String("");
//        final ViewConfiguration configuration = ViewConfiguration.get(mContext);
//        int mTouchSlop = configuration.getScaledTouchSlop();
//        fail("" + mTouchSlop);
    }

}
