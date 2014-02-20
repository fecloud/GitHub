package com.aspire.mose.shore.status;

import java.util.HashMap;

import android.util.Log;

class StateContainer
{
    private static final String TAG = "MOSE.STATE";

    private static StateContainer instance = new StateContainer();
    
    private HashMap<String,StateMachine> stateMachineMap = new HashMap<String,StateMachine>();
    
    private StateContainer()
    {
        
    }
    
    public static StateContainer getInstance()
    {
        return instance;
    }
    
    void addStateMachine(Class<?> StateMachineClazz)
    {
        addStateMachine(null,StateMachineClazz);
    }

    private void addStateMachine(String name,Class<?> StateMachineClazz)
    {
        try
        {
            StateMachine machine = (StateMachine)StateMachineClazz.newInstance();
            machine.init();
            if(name==null)
            {
                name = machine.getName();
            }
            Log.d(TAG, "init a state machine:["+name+","+machine+"]");
            stateMachineMap.put(name, machine);
        }
        catch(Throwable e)
        {
            throw new RuntimeException(e);
        }
    }
    
    StateMachine getStateMachine(String name)
    {
        return stateMachineMap.get(name);
    }

}
