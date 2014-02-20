package com.aspire.mose.frame.net.status;

import com.aspire.mose.shore.status.StateDEF;
import com.aspire.mose.shore.status.StateMachine;

public class NetStatus extends StateMachine
{
    public static final String name = "NET";

    public final static int OFF_LINE = 2;
    public final static int ON_LINE = 1;

    public NetStatus()
    {

    }
    
    public static NetStatus instance()
    {
        return (NetStatus)StateMachine.getStateMachine(name);
    }

    @Override
    protected StateDEF[] getStateDEF()
    {
        return new StateDEF[] 
               { 
                new StateDEF(ON_LINE, "网络在线"), 
                new StateDEF(OFF_LINE, "网络离线") 
               };
    }

    @Override
    protected String getNameDEF()
    {
        return name;
    }
}
