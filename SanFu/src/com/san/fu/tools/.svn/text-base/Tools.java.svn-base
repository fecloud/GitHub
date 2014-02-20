package com.san.fu.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.san.fu.Const;
import com.san.fu.HttpProxy;
import com.san.fu.Log;

public class Tools {

    public static final HttpProxy HTTP_PROXY = null;// new HttpProxy("10.1.9.56", 8080);

    public static final String[] str = { "@qq.com", "@163.com", "@126.com", "@sohu.com", "@sogou.com", "@xunlei.com",
            "@360.com", "@hao123.com", "@sina.com", "@google.com", "@hotmail.com", "@yahos.com", "@wanmei.com",
            "@hotmail.com", "@139.com", "@tom.com", "@51.com" };

    public static final String[] user_agent = {
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.638.0 Safari/534.16",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.634.0 Safari/534.16",
            "Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/540.0 (KHTML,like Gecko) Chrome/9.1.0.0 Safari/540.0",
            "Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/540.0 (KHTML, like Gecko) Ubuntu/10.10 Chrome/9.1.0.0 Safari/540.0",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.19 (KHTML, like Gecko) Chrome/1.0.154.55 Safari/525.19",
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_6; en-US) AppleWebKit/530.6 (KHTML, like Gecko) Chrome/ Safari/530.6",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:2.0b8) Gecko/20100101 Firefox/4.0b8",
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:2.0b8pre) Gecko/20101114 Firefox/4.0b8pre",
            "Mozilla/5.0 (X11; Linux x86_64; rv:2.0b9pre) Gecko/20110111 Firefox/4.0b9pre",
            "Mozilla/5.0 (X11; Linux i686; rv:2.0b3pre) Gecko/20100731 Firefox/4.0b3pre",
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_5; de-de) AppleWebKit/534.15+ (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-HK) AppleWebKit/533.18.1 (KHTML, like Gecko) Version/5.0.2 Safari/533.18.5",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/533.19.4 (KHTML, like Gecko) Version/5.0.2 Safari/533.18.5",
            "Mozilla/5.0 (Windows; U; Windows NT 6.0; nb-NO) AppleWebKit/533.18.1 (KHTML, like Gecko) Version/5.0.2 Safari/533.18.5" };

    public static String openPostUrl(String url, NameValuePair[] data, String userAgent) throws IOException {
        return openPostUrl(url, data, null, userAgent);
    }

    /**
     * 
     * @param url
     * @param entity
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String openPostUrl(String url, NameValuePair[] data, List<Cookie[]> cookies, String userAgent)
            throws IOException {
        Log.log(Log.LOG_LEVEL_DEBUG, "POST请求地址：" + url);
        PostMethod post = new PostMethod(url);
        post.setRequestBody(data);
        HttpClient client = new HttpClient();
        try {
            client.getParams().setParameter(HttpMethodParams.USER_AGENT, userAgent);
            if (null != HTTP_PROXY) {
                client.getHostConfiguration().setProxy(HTTP_PROXY.getProxyHost(), HTTP_PROXY.getProxyPort());
            }
            Log.log(Log.LOG_LEVEL_DEBUG, "" + client.getParams().getParameter(HttpMethodParams.USER_AGENT));
            int status = client.executeMethod(post);
            Log.log(Log.LOG_LEVEL_ERROR, "返回状态码：" + status);
            if (status == HttpStatus.SC_OK) {
                if (null != cookies) {
                    cookies.add(client.getState().getCookies());
                }
                // read body
                final String strResult = new String(post.getResponseBodyAsString().getBytes("ISO-8859-1"),
                        Const.ENCODING);
                Log.log(Log.LOG_LEVEL_DEBUG, "POST请求返回：" + strResult);
                return strResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }
        return null;

    }

    /**
     * 
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String openGetUrl(String url, Cookie[] cookies) throws Exception {

        Log.log(Log.LOG_LEVEL_DEBUG, "GET请求地址：" + url);
        HttpClient client = new HttpClient();
        GetMethod get = new GetMethod(url);
        int status = 0;
        try {
            if (null != cookies) {
                client.getParams().setCookiePolicy(CookiePolicy.RFC_2109);// RFC_2109是支持较普遍的一个，还有其他cookie协议
                HttpState initialState = new HttpState();
                for (Cookie c : cookies) {
                    initialState.addCookie(c);
                }
                client.setState(initialState);
            }
            if (null != HTTP_PROXY) {
                client.getHostConfiguration().setProxy(HTTP_PROXY.getProxyHost(), HTTP_PROXY.getProxyPort());
            }
            Log.log(Log.LOG_LEVEL_DEBUG, "" + client.getParams().getParameter(HttpMethodParams.USER_AGENT));
            status = client.executeMethod(get);
            Log.log(Log.LOG_LEVEL_ERROR, "返回状态码：" + status);
            if (status == HttpStatus.SC_OK) {
                // read body
                final String strResult = new String(get.getResponseBodyAsString().getBytes("ISO-8859-1"),
                        Const.ENCODING);
                Log.log(Log.LOG_LEVEL_DEBUG, "GET请求返回：" + strResult);
                return strResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            get.releaseConnection();
        }
        return null;
    }

    public static String createmil() {
        final Random random = new Random();
        int i = random.nextInt(str.length);
        return str[i];
    }

    public static String createUserAgent() {
        final Random random = new Random();
        int i = random.nextInt(user_agent.length);
        return user_agent[i];
    }

    /**
     * 
     * @return
     */
    public static int random() {
        final Random random = new Random();
        return Math.abs(random.nextInt());
    }

    /**
     * 
     * @return
     */
    public static int random(int num) {
        final Random random = new Random();
        return Math.abs(random.nextInt(num));
    }

    /**
     * 
     * @return
     */
    public static int random(int min, int max) {
        final Random random = new Random();
        int rand = Math.abs(random.nextInt(max));
        if (rand < min || rand > max) {
            random(min, max);
        }
        return rand;
    }

    /**
     * 读取每一行
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public static List<String> readLines(String path) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String line = null;
        final List<String> strings = new ArrayList<String>();
        while (null != (line = reader.readLine())) {
            if (!"".equals(line.trim())) {
                strings.add(line);
            }
        }
        return strings;
    }

    public static void actionWait() {
        final int millis = Tools.random(Const.ACTION_MIN, Const.ACTION_MAX);
        final String threadName = Thread.currentThread().getName();
        Log.log(Log.LOG_LEVEL_DEBUG, "==================================线程" + threadName + "睡眠" + millis + "毫秒");
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.log(Log.LOG_LEVEL_DEBUG, "==================================线程" + threadName + "醒了");
    }

    public static String formatDate() {
        final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new Date());
    }
}
