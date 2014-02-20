package com.san.fu.bean;

import org.apache.commons.httpclient.Cookie;

public class User {

    private String username;

    /**
     * 是否已注册
     */
    private boolean isReg;

    /**
     * 是不已登录
     */
    private boolean isLogin;

    /**
     * 是否已投票
     */
    private boolean isTp;

    private Cookie[] cookies;

    private String userAgent;
    
    /**
     * 操作过程中,错误超过10次，丢弃
     */
    public int errorcount;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isReg() {
        return isReg;
    }

    public void setReg(boolean isReg) {
        this.isReg = isReg;
    }

    public Cookie[] getCookies() {
        return cookies;
    }

    public void setCookies(Cookie[] cookies) {
        this.cookies = cookies;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isTp() {
        return isTp;
    }

    public void setTp(boolean isTp) {
        this.isTp = isTp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

}
