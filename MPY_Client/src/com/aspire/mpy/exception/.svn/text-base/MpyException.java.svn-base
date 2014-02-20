package com.aspire.mpy.exception;

public class MpyException extends Exception {
	 private static final long serialVersionUID = -5711286508800170181L;

	    protected int causeCode = 0;
	    
	    protected String causeMsg = "";

	    public MpyException() {
	    }

	    public MpyException(String msg, int code) {

	        super(msg);
	        this.causeCode = code;
	    }

	    public MpyException(String msg) {
	        super(msg);
	        this.causeMsg = msg;
	    }

	    public int getCauseCode() {
	        return this.causeCode;
	    }
	    
	    public String getCauseMsg()
	    {
	        return this.causeMsg;
	    }
}
