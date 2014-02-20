/**
 * @(#) PerfMonitorConfig.java Created on 2009-3-26
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.perf;

import com.aspire.mobile.MobileConstants;

/**
 * The class <code>PerfMonitorConfig</code>
 *
 * @author Link Wang
 * @version 1.0
 */
public class PerfMonitorConfig {
	
	/**
	 * The interval of two times sampling. Unit: ms
	 */
	public static final int SAMPLE_INTERVAL_MIN = 1000;
	
	/**
	 * Minimum monitor last time, Unit: ms
	 */
	public static final int MONITOR_DURATION_MIN = 3000;
    
    /**
     * The interval of sampling.
     */
    protected int interval = SAMPLE_INTERVAL_MIN;
    
    /**
     * The monitor last time.
     */
    protected int duration = -1;
    
    /**
     * Monitor point.
     */
    protected int monitorPoint = 0;
    
    /**
	 * @return the interval
	 */
	public int getInterval() {
		return interval;
	}

	/**
	 * @param interval the interval to set
	 */
	public void setInterval(int interval) {
		if (interval > SAMPLE_INTERVAL_MIN) {
			this.interval = interval;
		}
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
	    if (duration > MONITOR_DURATION_MIN) {
	        this.duration = duration;
	    }
	}

	/**
	 * @return the monitorPoint
	 */
	public int getMonitorPoint() {
		return monitorPoint;
	}

	/**
	 * @param monitorPoint the monitorPoint to set
	 */
	public void setMonitorPoint(int monitorPoint) {
		this.monitorPoint = monitorPoint;
	}

	/**
     * Is cpu monitor on
     * @return
     */
    public boolean isMonitorCpu() {
        return (monitorPoint & MobileConstants.MONITOR_CPU_ON)
                == MobileConstants.MONITOR_CPU_ON;
    }
    
    /**
     * Is memory monitor on
     * @return
     */
    public boolean isMonitorMemory() {
        return (monitorPoint & MobileConstants.MONITOR_MEM_ON)
                == MobileConstants.MONITOR_MEM_ON;
    }
    
    /**
     * Is processes monitor on
     * @return
     */
    public boolean isMonitorProcess() {
        return (monitorPoint & MobileConstants.MONITOR_PROC_ON)
                == MobileConstants.MONITOR_PROC_ON;
    }
    
    /**
     * Is network monitor on
     * @return
     */
    public boolean isMonitorNetwork() {
        return (monitorPoint & MobileConstants.MONITOR_NET_ON)
                == MobileConstants.MONITOR_NET_ON;
    }
    
    /**
     * Is battery monitor on
     * @return
     */
    public boolean isMonitorBattery() {
        return (monitorPoint & MobileConstants.MONITOR_BAT_ON)
                == MobileConstants.MONITOR_BAT_ON;
    }
    
    /**
     * Set cpu monitor open or close.
     * @param bMonitorCpu
     */
    public void setMonitorCpu(boolean bOn) {
        this.monitorPoint = monitorPoint | (bOn ?
                MobileConstants.MONITOR_CPU_ON : MobileConstants.MONITOR_OFF);
    }
    
    /**
     * Set memory monitor open or close.
     * @param bMonitorCpu
     */
    public void setMonitorMemory(boolean bOn) {
        this.monitorPoint = monitorPoint | (bOn ?
                MobileConstants.MONITOR_MEM_ON : MobileConstants.MONITOR_OFF);
    }
    
    /**
     * Set processes monitor open or close.
     * @param bMonitorCpu
     */
    public void setMonitorProcess(boolean bOn) {
        this.monitorPoint = monitorPoint | (bOn ?
                MobileConstants.MONITOR_PROC_ON : MobileConstants.MONITOR_OFF);
    }
    
    /**
     * Set network monitor open or close.
     * @param bMonitorCpu
     */
    public void setMonitorNetwork(boolean bOn) {
        this.monitorPoint = monitorPoint | (bOn ?
                MobileConstants.MONITOR_NET_ON : MobileConstants.MONITOR_OFF);
    }
    
    /**
     * Set battery monitor open or close.
     * @param bMonitorCpu
     */
    public void setMonitorBattery(boolean bOn) {
        this.monitorPoint = monitorPoint | (bOn ?
                MobileConstants.MONITOR_BAT_ON : MobileConstants.MONITOR_OFF);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (!super.equals(obj)) return false;

        if (!(obj instanceof PerfMonitorConfig)) {
            return false;
        }

        PerfMonitorConfig _obj = (PerfMonitorConfig)obj;
        return _obj.interval == interval
                && _obj.duration == duration
                    && _obj.monitorPoint == monitorPoint;
    }
}
