package com.aspire.android.I9108;


public class PGLoingtime extends ASPTestCase {
	public final static String TRANSACTION_ID_LoginTime006 = "999006";
/*
 * 登陆模式1
 * (non-Javadoc)
 * @see com.aspire.android.test.testcase.AbstractTestCase#doExecute()
 */
	@Override
	protected void doExecute() throws Exception {
		// TODO Auto-generated method stub

		logDebug("启动事务");
		deviceEntity.beginTransaction(TRANSACTION_ID_LoginTime006);
		logDebug("运行评估程序1");
		if (!deviceEntity.runApp("评估程序1"))
		{
			logDebug("启动评估程序失败");
			deviceEntity.endTransaction(TRANSACTION_ID_LoginTime006, "-1", "10", true);
		}
		else
		{
		        long start_time = System.currentTimeMillis();
		        long end_time;
		        long login_time = 0;
		        deviceEntity.waitMillis(3000);
		       // int findlocal1[] = findInScreenCom(0, 250, 470, 690, "SamSungI9108_999010_1_1_find.bmp", 0, 0, 1, 1, 80, 60, 60, 60000);
		        int findlocal[] = findInScreenCom(0, 250, 470, 300, "SamSungI9108_999006_5_5_find.bmp", 0, 0, 5, 5, 10, 30, 30, 20000);
//		        deviceEntity.waitMillis(2000);
//		        if (noVerifyScreen(160, 405, "SamSungI9108_999006160405_150_60_find.bmp", 150, 60, 30, 60000)){
//			        end_time = System.currentTimeMillis();
//			        login_time = end_time - start_time;
//			        logDebug("Login_time:" + login_time);
//			        deviceEntity.endTransaction(TRANSACTION_ID_LoginTime006, parseMs2MStr(login_time), "00", false);
//			       
//		        }
//
		        if (findlocal != null)
		        {
			        end_time = System.currentTimeMillis();
			        login_time = end_time - start_time;
			        deviceEntity.endTransaction(TRANSACTION_ID_LoginTime006, parseMs2MStr(login_time), "00", false);
		        }
		        else
		        	deviceEntity.endTransaction(TRANSACTION_ID_LoginTime006, "-1", "04", true);
	      }

		deviceEntity.waitMillis(1000);
		//exitApp();
	}
	

	
}
