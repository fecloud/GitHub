/**
 * @(#) Station_Name.java Created on 2014-1-1
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.gohome.resouce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The class <code>Station_Name</code>
 * 
 * @author braver
 * @version 1.0
 */
public class Station_Name {

	StringBuffer strins = new StringBuffer();

	private List<Station> stations = new ArrayList<Station>();

	private static final Station_Name NAME = new Station_Name();

	private Station_Name() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(getClass()
					.getResourceAsStream("/station_name"), "UTF-8"));

			String line = null;

			while (null != (line = reader.readLine())) {
				strins.append(line);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		parse();
	}

	public void parse() {
		final String[] sttions = strins.toString().split("@");
		if (null != sttions) {
			Station station = null;
			for (String s : sttions) {
				if (null != s && !"".equals(s.trim())) {
					station = new Station();
					station.parse(s);
					stations.add(station);
				}
			}
		}
	}

	public static final Station_Name getInstance() {
		return NAME;
	}

	public List<Station> getStationZhCn(String name) {
		final List<Station> list = new ArrayList<Station>();
		for (Station s : stations) {
			if (s.mathersContain(name)) {
				list.add(s);
			}
		}
		return list;
	}

}
