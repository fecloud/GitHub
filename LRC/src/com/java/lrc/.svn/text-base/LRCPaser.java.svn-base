package com.java.lrc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LRCPaser {
	
private static final String TAGSTART = "\\[";
	
	private static final String TAGEND = "\\]";
	
	private static final String LRCTIMTTAG = TAGSTART + "\\d{2,5}:[0-5]{1}\\d{1}(\\.\\d{2})?" + TAGEND;
	
	private static final String NUMBERREGEX = "\\d+";
	
	private LRCContainer lrcContainer = new LRCContainer();
	
	public LRCContainer getLrcContainer() {
		return lrcContainer;
	}

	public void paserLRC(InputStream in) throws IOException{
		paserLRC(in, null);
	}
	
	public void paserLRC(InputStream in , String encoding) throws IOException{
		String tempEncoding = "GBK";
		if(null != encoding) tempEncoding = encoding;
		final BufferedReader reader = new BufferedReader(new InputStreamReader(in, tempEncoding));
		String line = null;
		while(null != (line = reader.readLine())){
			for(String s : getLineTimeTag(line)){
				final long time = paserTime(getTimeTagToTime(s));
				final String content = getLineContent(line);
				lrcContainer.addLRC(time , content);
			}
		}
		reader.close();
	}
	
	private static String getTimeTagToTime(String tag){
		if(null != tag){
			String time = tag.replaceAll(TAGSTART , "");
			time = time.replaceAll(TAGEND, "");
			return time;
		}
		return null;
	}
	
	private static long paserTime(String time){
		final int [] paserTimeArray = {60000 , 1000 , 1};
		int reLong = 0;
		if(null != time){
			final Matcher matcher = Pattern.compile(NUMBERREGEX).matcher(time);
			String tempNumber= null;
			int i = 0;
			while(matcher.find()){
				tempNumber = matcher.group();
				reLong += Integer.parseInt(tempNumber) * paserTimeArray[i];
				i++;
			}
		}
		return reLong;
	}
	
	private static List<String> getLineTimeTag(String line){
		final List<String> list = new ArrayList<String>();
		if(null != line && !"".equals(line.trim())){
			Matcher matcher = Pattern.compile(LRCTIMTTAG).matcher(line);
			while(matcher.find()){
				list.add(matcher.group());
			}
		}
		return list;
	}

	private static String getLineContent(String line){
		if(null != line){
			final String reStr = line.replaceAll(LRCTIMTTAG, "").trim();
			return reStr;
		}
		return null;
	}
	
}
