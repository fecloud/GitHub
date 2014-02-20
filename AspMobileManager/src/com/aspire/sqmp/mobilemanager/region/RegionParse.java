/**
 * @(#) ProvinceParse.java Created on 2012-6-13
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.region;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



/**
 * The class <code>ProvinceParse</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class RegionParse {

    private static final String SPLIT_REGEX = ",";

    private static File provinceFile;

    private static File citiesFile;

    private List<Province> provinces;
    private static RegionParse instance;

    private RegionParse() {
    }

    public static RegionParse getInstance() {
        if(instance == null){
            instance = new RegionParse();
            provinceFile = new File(".\\data\\province");
            citiesFile = new File(".\\data\\cities");
            instance.parse();
        }
        return instance;
    }

    /**
     * parse from file
     */
    private void parse() {
        try {
            parseProvince();
            final List<City> cities = parseCity();
            List<City> tempCities = null;
            for (Province province : provinces) {
                tempCities = new ArrayList<City>();
                for (City city : cities) {
                    if (city.getProvince_code() == province.getCode()) {
                        tempCities.add(city);
                    }
                }
                province.setCities(tempCities);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseProvince() throws IOException {
        final List<String> strings = readLine(provinceFile);
        provinces = new ArrayList<Province>();
        Province province = null;
        String[] splitstr = null;
        for (String str : strings) {
            splitstr = str.split(SPLIT_REGEX);
            if (splitstr.length == 2) {
                province = new Province(splitstr[0], Integer.parseInt(splitstr[1]));
                provinces.add(province);
            }
        }
    }

    private List<City> parseCity() throws IOException {
        final List<String> strings = readLine(citiesFile);
        final List<City> cities = new ArrayList<City>();
        City city = null;
        String[] splitstr = null;
        for (String str : strings) {
            splitstr = str.split(SPLIT_REGEX);
            if (splitstr.length == 4) {
                city = new City(splitstr[1], Integer.parseInt(splitstr[0]), Integer.parseInt(splitstr[2]),
                        Integer.parseInt(splitstr[3]));
                cities.add(city);
            }
        }
        return cities;
    }

    /**
     * Getter of provinces
     * 
     * @return the provinces
     */
    public List<Province> getProvinces() {
        return provinces;
    }

    public Province getProvinceByName(String name) {
        for (Province p : getProvinces()) {
            if (p.getName().equals(name))
                return p;
        }
        throw new NullPointerException("not found by code int provinces");
    }

    public Province getProvinceByCode(int code) {
        for (Province p : getProvinces()) {
            if (p.getCode() == code)
                return p;
        }
        throw new NullPointerException("not found by code int provinces");
    }

    public String[] getProvincesNames() {
        final int size = getProvinces().size();
        final String[] arrays = new String[size];
        for (int i = 0; i < size; i++) {
            arrays[i] = getProvinces().get(i).getName();
        }
        return arrays;
    }

    public String[] getProvincesCodes() {
        final int size = getProvinces().size();
        final String[] arrays = new String[size];
        for (int i = 0; i < size; i++) {
            arrays[i] = "" + getProvinces().get(i).getCode();
        }
        return arrays;
    }

    /**
     * read file by read line
     * 
     * @param mContext
     * @param id
     * @return
     * @throws IOException
     */
    private static List<String> readLine(File f) throws IOException {
        final InputStream in = new FileInputStream(f);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        final List<String> strings = new ArrayList<String>();
        String line = null;
        while (null != (line = reader.readLine())) {
            strings.add(line);
        }
        reader.close();
        return strings;
    }

}
