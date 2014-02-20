/**
 * @(#) ScreenCapManager.java Created on 2012-12-13
 *
 * Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.android.screen.capture;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.util.Log;

import com.braver.android.util.ExecCommand;

/**
 * The class <code>ScreenCapManager</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class ScreenCapManager {

	public final static String TAG = "ScreenCapManager";

	public final static String CAPTURE_APP_NAME = "screencapture.jar";
	public final static String START_UP_SHELL = "startAgent";
	public final static String CAPTURE_JNI_23 = "libscreencapture23.so";
	public static String capture_location;

	public final static String[] CAPTURE_APP_FILES = new String[] {
			CAPTURE_APP_NAME, CAPTURE_JNI_23 };

	/**
	 * Start agent daemon process.
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static void startAgentDaemon(Context mContext) throws Exception {
		ExecCommand.killProcess("app_process");
		capture_location = mContext.getFilesDir().getAbsolutePath()
				+ File.separator;
		copyRawFile(mContext, R.raw.screencapture, new StringBuffer(
				capture_location).append(CAPTURE_APP_NAME).toString());
		copyRawFile(mContext, R.raw.libscreencapture23, new StringBuffer(
				capture_location).append(CAPTURE_JNI_23).toString());
		prepareShellCmd();
		final String[] cmds = new String[2];
		cmds[0] = "chmod 777 " + capture_location + "*";
		cmds[1] = "sh " + capture_location + START_UP_SHELL + " & > /dev/null";
		ExecCommand.execCommandsAsRoot(cmds);
		Log.i(TAG, "Start capture ok.");
	}

	public static void prepareShellCmd() throws Exception {
		final File file = new File(capture_location + START_UP_SHELL);
		final BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		final String ldpath = "export LD_LIBRARY_PATH=" + capture_location
				+ ":$LD_LIBRARY_PATH" + "\n";
		final String classpath = "export CLASSPATH=" + capture_location
				+ CAPTURE_APP_NAME + "\n";
		writer.append(ldpath)
				.append(classpath)
				.append("exec app_process /system/bin com.braver.android.ScreenCaptureMain ")
				.append("6000").append(" ").append("256");
		writer.flush();
		writer.close();
	}

	private static boolean copyRawFile(Context mContext, int raw,
			String filename) throws IOException {
		final InputStream in = mContext.getResources().openRawResource(raw);
		final FileOutputStream out = new FileOutputStream(filename);
		final byte[] bs = new byte[1024];
		int len = 0;
		while (-1 != (len = in.read(bs))) {
			out.write(bs, 0, len);
		}
		out.flush();
		out.close();
		in.close();
		return true;
	}

	// private static void createFileFromAssert(String outDir, String outName,
	// String fileName, AssetManager am) throws IOException {
	// InputStream in = null;
	// FileOutputStream fout = null;
	// ZipInputStream zipIn = null;
	// Log.i(TAG, "Create agent daemon app file: " + fileName);
	// in = am.open(fileName);
	//
	// try {
	// if (fileName.endsWith(".zip")) {
	// zipIn = new ZipInputStream(in);
	// ZipEntry entry = zipIn.getNextEntry();
	// File file = null;
	// while (entry != null) {
	// if (entry.isDirectory()) {
	// String name = entry.getName();
	// file = new File(outDir + File.separator + name);
	// file.mkdir();
	//
	// } else {
	// fout = new FileOutputStream(outDir + File.separator
	// + entry.getName());
	// writeBuffer(fout, zipIn);
	// fout.close();
	// fout = null;
	// }
	// // 读取下一个ZipEntry
	// entry = zipIn.getNextEntry();
	// }
	// return;
	// }
	// fout = new FileOutputStream(outDir + outName);
	// writeBuffer(fout, in);
	// } catch (Exception ex) {
	// throw new IOException("Fail to create daemon app file: " + fileName);
	// } finally {
	// if (zipIn != null) {
	// try {
	// zipIn.close();
	// } catch (Exception e) {
	// Log.w(TAG, "Close daemon app file zipInput-stream error.",
	// e);
	// }
	// }
	// if (in != null) {
	// try {
	// in.close();
	// } catch (Exception e) {
	// Log.w(TAG, "Close daemon app file input-stream error.", e);
	// }
	// }
	// if (fout != null) {
	// try {
	// fout.close();
	// } catch (Exception e) {
	// Log.w(TAG, "Close daemon app file output-stream error.", e);
	// }
	// }
	//
	// }
	// }

	// private static void writeBuffer(FileOutputStream fout, InputStream in) {
	// int read = 0;
	// FileChannel fc = fout.getChannel();
	// ByteBuffer buffer = ByteBuffer.allocate(1024);
	// try {
	// while ((read = in.read(buffer.array())) != -1) {
	// buffer.limit(read);
	// fc.write(buffer);
	// buffer.rewind();
	// }
	// } catch (Exception e) {
	// if (fc != null) {
	// try {
	// fc.close();
	// } catch (Exception ex) {
	// Log.w(TAG, "Close daemon app file output-channel error.", e);
	// }
	// }
	// }
	// }

	/**
	 * 获取系统SDK版本号
	 * 
	 * @return
	 */
	public static int getSdkVersion() {
		int sdkVersion;
		try {
			sdkVersion = Integer.valueOf(android.os.Build.VERSION.SDK);
		} catch (NumberFormatException e) {
			sdkVersion = 0;
		}
		return sdkVersion;
	}

}
