package com.aspire.mose.frame.config;

public class ConfigModule
{
    protected String name;
    protected int type;
    protected String persistence;
    protected ConfigModulePersistenter persistener;
    
    protected ConfigModule(String name,int type,String persistence)
    {
        this.name = name;
        this.type = type;
        this.persistence = persistence;
    }
    
    void setPersistenter(ConfigModulePersistenter persistener)
    {
        this.persistener=persistener;
    }

    public String getName()
    {
        return name;
    }

//    public int getType()
//    {
//        return type;
//    }
//
//    public String getPersistence()
//    {
//        return persistence;
//    }
    
    public void removeItem(String name)
    {
        persistener.removeItem(name);
    }
    public void setStringItem(String name,String value)
    {
        persistener.setStringItem(name, value);
    }
    public void setBooleanItem(String name,boolean value)
    {
        persistener.setBooleanItem(name, value);
    }
    public void setLongItem(String name,long value)
    {
        persistener.setLongItem(name, value);
    }
    public void setIntItem(String name,int value)
    {
        persistener.setIntItem(name, value);
    }
    
    public String getStringItem(String name,String defaultValue)
    {
        return persistener.getStringItem(name, defaultValue);
    }

    public boolean getBooleanItem(String name,boolean defaultValue)
    {
        return persistener.getBooleanItem(name, defaultValue);
    }

    public int getIntItem(String name,int defaultValue)
    {
        return persistener.getIntItem(name, defaultValue);    
    }

    public long getLongItem(String name,long defaultValue)
    {
        return persistener.getLongItem(name, defaultValue);
    }
    
}
