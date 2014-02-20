package com.aspire.mose.shore.status;

import java.util.ArrayList;

public abstract class StateMachine
{
    protected String nameDEF;
    protected int state = Integer.MIN_VALUE;
    
    private ArrayList<StateObserver> observerList;
    private Object observerListLock = new Object();
    private ArrayList<StateDEF> stateDEFList;
    
    public StateMachine()
    {
        observerList = new ArrayList<StateObserver>();
        stateDEFList = new ArrayList<StateDEF>();
    }
    
    void init()
    {
        //给容器调用的初始化方法
        this.nameDEF = this.getNameDEF();
        StateDEF[] stateDEFs = this.getStateDEF();
        for(StateDEF stateDEF:stateDEFs)
        {
            this.stateDEFList.add(stateDEF);
        }
    }
    
    public String getName()
    {
        return nameDEF;
    }

    public int getState()
    {
        return state;
    }

    public void updateState(int newState)
    {
        //通知所有观察者notifyAllObserver
        this.state = newState;
        this.notifyAllObserver(newState);
    }
    
    public void addObserver(StateObserver observer)
    {
        synchronized(observerListLock)
        {
            observerList.add(observer);
        }
    }

    public void removeObserver(StateObserver observer)
    {
        synchronized(observerListLock)
        {
            observerList.remove(observer);
        }
    }

    private void notifyAllObserver(int newState)
    {
        synchronized(observerListLock)
        {
            for(StateObserver observer:observerList)
            {
                observer.nofityState(newState);
            }
        }
    }
    
    protected static StateMachine getStateMachine(String name)
    {
        return StateContainer.getInstance().getStateMachine(name);
    }

    public static void addStateMachine(Class<?> StateMachineClazz)
    {
        StateContainer.getInstance().addStateMachine(StateMachineClazz);
    }    
    
    /**
     * 子类必须覆盖这个方法，用于定义状态机接受的各种状态
     * @return
     */
    protected abstract StateDEF[] getStateDEF();
    
    /**
     * 子类可以覆盖这个方法，用于定义状态机的名称
     * @return
     */
    protected abstract String getNameDEF();
    
}
