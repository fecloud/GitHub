package com.aspire.mose.frame.service;

import com.aspire.mose.shore.status.StateDEF;
import com.aspire.mose.shore.status.StateMachine;

public class MOSEStatus extends StateMachine
{
    public static final String name = "MOSE";
    public static final int STARTING = 1;//启动中
    public static final int SUCC = 0;//启动成功
    public static final int ERROR = 2;//启动失败
    
    //必须有一个无参数的构造方法
    public MOSEStatus()
    {
        
    }
    
    public static MOSEStatus instance()
    {
        return (MOSEStatus)StateMachine.getStateMachine(name);
    }

    @Override
    protected StateDEF[] getStateDEF()
    {
        StateDEF[] defs = { 
                new StateDEF(STARTING, "启动中"), 
                new StateDEF(SUCC, "启动成功"),
                new StateDEF(ERROR, "启动失败") 
                };
        return defs;
    }

    @Override
    protected String getNameDEF()
    {
        return name;
    }

}
