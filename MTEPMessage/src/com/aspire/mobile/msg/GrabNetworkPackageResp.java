package com.aspire.mobile.msg;

import com.aspire.mobile.MobileConstants;
import com.aspire.util.ByteArray;
import com.aspire.util.ToolException;

/**
 * 
 * The class <code>GrabNetworkPackageResp</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class GrabNetworkPackageResp extends MobileRespBase {

    /**
     * The file field
     */
    protected String sFile;

    /**
     * Get the file value
     * 
     * @return
     */
    public String getFile() {
        return sFile;
    }

    /**
     * Set the file value
     * 
     * @param sFile
     */
    public void setFile(String sFile) {
        this.sFile = sFile;
        setVerify("FILE", true);
    }

    /**
     * Constructor
     * 
     * @param nMsgType
     * @param sMsgName
     */
    public GrabNetworkPackageResp() {
        super(MobileConstants.GRAB_NETWORKPACKAGE_RESP, "GrabNetworkPackageResp");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileRespBase#decode(byte[], int, int)
     */
    @Override
    public int decode(byte[] byMsg, int start, int length) throws ToolException {
        int nRead = super.decode(byMsg, start, length);
        int offset = start + nRead;

        if ((offset + 4) < length) {
            int nLen = ByteArray.bytesToInt(byMsg, offset);
            offset += 4;
            // Decodes the Data value field
            if ((offset + nLen) > length) {
                throw new ToolException("WapOperationReq decode error: no FILE in the record");
            }
            sFile = new String(byMsg, offset, nLen);
            offset += nLen;
            setPresent("FILE", true);
        }

        return offset - start;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.mobile.msg.MobileRespBase#encode(com.aspire.util.ByteArray)
     */
    @Override
    public int encode(ByteArray baMsg) throws ToolException {
        int nLen = baMsg.length();

        super.encode(baMsg);
        if (isPresent("FILE")) {
            baMsg.append(sFile.length());
            baMsg.append(sFile);
        }

        return baMsg.length() - nLen;
    }

}
