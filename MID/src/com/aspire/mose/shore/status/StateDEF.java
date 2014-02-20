package com.aspire.mose.shore.status;

public class StateDEF
{
    protected int stateValue;
    protected String stateDesc;
    
    public StateDEF(int stateValue,String stateDesc)
    {
        this.stateValue = stateValue;
        this.stateDesc = stateDesc;
    }

    public int getStateValue()
    {
        return stateValue;
    }

    public String getStateDesc()
    {
        return stateDesc;
    }

}
