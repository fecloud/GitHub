package com.java.lrc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LRCContainer {
	
	private int index;

	private List<LRC> lrcs = new ArrayList<LRC>();
	
	private boolean isSort = false;

	public List<LRC> getLrcs() {
		if(!isSort){
			Collections.sort(lrcs);
			isSort = true;
		}
		return lrcs;
	}

	public void addLRC(LRC lrc){
		this.lrcs.add(lrc);
		isSort = false;
	}
	
	public void addLRC(long time , String content){
		addLRC(new LRC(time , content));
	}
	
	public LRC getLRC(long time){
		if(!isSort) {
			Collections.sort(lrcs);
			isSort = true;
		}
		for(int i = 0 ; i < lrcs.size() ; i ++){
			if(i == 0 || i < lrcs.size() - 1){
				if(lrcs.get(i).getStartTime() <= time && lrcs.get(i + 1).getStartTime() >= time){
					index = i;
					return lrcs.get(i);
				}
			}else {
				if(lrcs.get(i).getStartTime() <= time){
					index = i;
					return lrcs.get(i);
				}
			}
		}
		return null;
	}
	
	public LRC getNextLRC(){
		if(index != lrcs.size() && index < lrcs.size() - 1){
			index += 1;
			return lrcs.get(index) ;
		}
		return null;
	}
	
}
