package com.inspurworld.msg.data;
/**
 * 
 * The class <code>ImageMessage</code>
 *
 * @author Fan Yang
 * @version 1.0
 */
public class ImageMessage {
    /**
     * the Total Package Counts
     */
    private int TotalPackageCount;
    /**
     * the Current Package SequenceNumber
     */
    private int CurrentPackageSequenceNumber;
    /**
     * the image data
     */
    private byte[] ImageData;
    /**
     * 
     * Constructor
     */
    public ImageMessage(){}
    /**
     * get the Total Package Counts
     * @return
     */
    public int getTotalPackageCount(){
        return TotalPackageCount;
    }
    /**
     * set the Total Package Counts
     * @param TotalPackageCount
     */
    public void setTotalPackageCount(int TotalPackageCount){
        this.TotalPackageCount = TotalPackageCount;
    }
    /**
     * get the Current Package SequenceNumber
     * @return
     */
    public int getCurrentPackageSequenceNumber(){
        return CurrentPackageSequenceNumber;
    }
    /**
     * set the Current Package SequenceNumber
     * @param CurrentPackageSequenceNumber
     */
    public void setCurrentPackageSequenceNumber(int CurrentPackageSequenceNumber){
        this.CurrentPackageSequenceNumber = CurrentPackageSequenceNumber;
    }
    /**
     * get the image data
     * @return
     */
    public byte[] getImageData(){
        return ImageData;
    }
    /**
     * set the image data
     * @param ImageData
     */
    public void setImageData(byte[] ImageData){
        this.ImageData = ImageData;
    }
}
