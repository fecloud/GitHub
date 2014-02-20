package com.java.lrc;

public final class LRC implements Comparable<LRC>{
	
	private long startTime;
	
	private String content;
	
	public LRC() {
		super();
	}

	public LRC(long startTime, String content) {
		super();
		this.startTime = startTime;
		this.content = content;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return this.startTime + "=" + this.content;
	}

	@Override
	public int compareTo(LRC o) {
		int reInt = 0;
		if(this.startTime > o.startTime) {
			reInt = 1;
		}
		else if(this.startTime < o.startTime){
			reInt = -1;
		}
//		System.out.println(reInt);
		return reInt;
	}

}
