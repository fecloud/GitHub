package com.java.lrc.test;

import java.io.IOException;

import com.java.lrc.LRC;
import com.java.lrc.LRCContainer;
import com.java.lrc.LRCPaser;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		LRCPaser lrcPaser = new LRCPaser();
		lrcPaser.paserLRC(Main.class.getResourceAsStream("/test.lrc"));
		final LRCContainer container = lrcPaser.getLrcContainer();
//		for(LRC c : container.getLrcs()){
//			System.out.println(c);
//		}
		new Thread(new Runnable() {
			
			long time = 0;
			
			@Override
			public void run() {
				LRC lrc = container.getLRC(time);
				LRC next = null ;
				while(null != (next = container.getNextLRC())){
					System.out.println(lrc);
					try {
						Thread.sleep(next.getStartTime() - lrc.getStartTime());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					lrc = next;
				}
			}
		}).start();
	}

}
