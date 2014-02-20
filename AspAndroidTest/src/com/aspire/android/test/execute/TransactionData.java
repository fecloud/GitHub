/**
 * @(#) TransactionData.java Created on 2012-4-26
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The class <code>TransactionData</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class TransactionData implements Parcelable {

    /**
     * Constant
     * 
     * private static final String SEPARATOR = "||||||"; private static final String NULLVALUE = "nullnullnullnull";
     */
    /**
     * transaction name
     */
    protected String name;

    /**
     * transaction value
     */
    protected String value;

    /**
     * transaction attachment
     */
    protected String attachment;

    /**
     * startTime
     */
    protected long startTime;

    /**
     * endTime
     */
    protected long endTime;

    protected String result;

    public static final Parcelable.Creator<TransactionData> CREATOR = new Parcelable.Creator<TransactionData>() {

        public TransactionData createFromParcel(Parcel in) {
            return new TransactionData(in);
        }

        public TransactionData[] newArray(int size) {
            return new TransactionData[size];
        }

    };

    private TransactionData(Parcel in) {
        this.name = in.readString();
        this.value = in.readString();
        this.attachment = in.readString();
        this.startTime = in.readLong();
        this.endTime = in.readLong();
        this.result = in.readString();
    }

    /**
     * Constructor
     * 
     * @param name
     * @param value
     * @param attachment
     */
    public TransactionData(String name, String value, String attachment) {
        super();
        this.name = name;
        this.value = value;
        this.attachment = attachment;
    }

    /**
     * Constructor
     * 
     * @param name
     * @param value
     * @param startTime
     * @param endTime
     */
    public TransactionData(String name, String value, long startTime, long endTime) {
        super();
        this.name = name;
        this.value = value;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor
     * 
     * @param name
     * @param value
     * @param attachment
     * @param startTime
     * @param endTime
     */
    public TransactionData(String name, String value, String attachment, long startTime, long endTime) {
        super();
        this.name = name;
        this.value = value;
        this.attachment = attachment;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor
     * 
     * @param name
     * @param value
     * @param attachment
     * @param startTime
     * @param endTime
     * @param result
     */
    public TransactionData(String name, String value, String attachment, long startTime, long endTime, String result) {
        super();
        this.name = name;
        this.value = value;
        this.attachment = attachment;
        this.startTime = startTime;
        this.endTime = endTime;
        this.result = result;
    }

    /**
     * Constructor
     * 
     * @param name
     * @param value
     * @param attachment
     * @param startTime
     * @param endTime
     * @param result
     */
    public TransactionData(String name, String value, long startTime, long endTime, String result) {
        super();
        this.name = name;
        this.value = value;
        this.startTime = startTime;
        this.endTime = endTime;
        this.result = result;
    }

    /**
     * Getter of name
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name
     * 
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of value
     * 
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Setter of value
     * 
     * @param value
     *            the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Getter of attachment
     * 
     * @return the attachment
     */
    public String getAttachment() {
        return attachment;
    }

    /**
     * Setter of attachment
     * 
     * @param attachment
     *            the attachment to set
     */
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     * @Override public String toString() { String result = name == null ? NULLVALUE : name; result += SEPARATOR; result
     *           += value == null ? NULLVALUE : value; result += SEPARATOR; result += attachment == null ? NULLVALUE :
     *           attachment; result += SEPARATOR; result += Long.toString(startTime.getTime()); result += SEPARATOR;
     *           result += Long.toString(endTime.getTime()); return result; }
     */

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /**
     * from String
     * 
     * @param objectString
     *            string generate by toString
     * 
     *            public void fromString(String objectString) { String[] values = objectString.split(SEPARATOR); name =
     *            NULLVALUE.equals(values[0]) ? null : values[0]; value = NULLVALUE.equals(values[1]) ? null :
     *            values[1]; attachment = NULLVALUE.equals(values[2]) ? null : values[2];
     *            startTime.setTime(Long.parseLong(values[3])); endTime.setTime(Long.parseLong(values[4])); }
     */
    /**
     * (non-Javadoc)
     * 
     * @see android.os.Parcelable#describeContents()
     */
    public int describeContents() {
        return 0;
    }

    /**
     * (non-Javadoc)
     * 
     * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
     */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(value);
        dest.writeString(attachment);
        dest.writeLong(startTime);
        dest.writeLong(endTime);
        dest.writeString(result);
    }
}
