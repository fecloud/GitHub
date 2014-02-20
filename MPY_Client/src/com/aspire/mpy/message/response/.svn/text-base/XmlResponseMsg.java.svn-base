package com.aspire.mpy.message.response;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.aspire.mpy.exception.MpyException;

import android.sax.Element;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Log;

public abstract class XmlResponseMsg implements IResponseMsg {
	private static final String TAG = "XmlResponseMsg";
	/** 消息类型 */
	public String cmd = "";

	/** 协议版本号 */
	public String version = "1.0";

	/** 返回值ֵ */
	public int result = -1;

	public RootElement root;

	/**
	 * @param resp
	 *            区分不同平台返报文跟节点是resp或Resp
	 */
	public XmlResponseMsg(String resp) {
		root = new RootElement(resp);
		Element msgTypeTag = root.getChild("Cmd");
		// 监听标签体,标签体结束的时候将文本内容放到ArrayList里面,下同
		msgTypeTag.setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				cmd = body;
			}
		});

		Element versionTag = root.getChild("Version");
		versionTag.setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				version = body;
			}
		});

		Element retCodeTag = root.getChild("Result");
		retCodeTag.setEndTextElementListener(new EndTextElementListener() {
			public void end(String body) {
				result = Integer.parseInt(body);
			}
		});
	}

	public void parseInputStream(InputStream in) throws MpyException, IOException {
		if (null == in) {
			throw new IOException();
		}

		try {
			XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			reader.setContentHandler(root.getContentHandler());

			// test // sax解析
			String xml = convertStreamToString(in);
			Log.i(TAG, "response xmlMsg=" + xml);
			InputStream ins = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			InputSource isx = new InputSource(ins);

			// InputSource isx = new InputSource(in);
			reader.parse(isx);

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			throw new MpyException(" cmd=" + this.cmd + "  " + e.toString());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			throw new MpyException(" cmd=" + this.cmd + "  " + e.toString());
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			throw new MpyException(e.toString());
		} catch (IOException e) {
			throw new MpyException(" cmd=" + this.cmd + "  " + e.toString());
		}
	}

	private String convertStreamToString(InputStream is) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// MidLog.servLogger.info("mid MidResp "+sb.toString());
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(" cmd = " + cmd);
		sb.append(" version = " + version);
		sb.append(" result = " + result);
		sb.append("]");
		return sb.toString();
	}

	@Override
	public void parseInputStream(InputStream in, HttpURLConnection httpConn) throws MpyException,
			IOException {
		parseInputStream(in);

	}
}
