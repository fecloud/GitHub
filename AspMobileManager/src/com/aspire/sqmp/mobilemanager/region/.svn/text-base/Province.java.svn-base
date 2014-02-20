/**
 * @(#) Province.java Created on 2012-6-13
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.region;

import java.util.List;

/**
 * The class <code>Province</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class Province {

    /**
     * province name
     */
    private String name;

    /**
     * province code
     */
    private int code;

    /**
     * province have cities
     */
    private List<City> cities;

    public Province(String name, int code) {
        super();
        this.name = name;
        this.code = code;
    }

    public Province(String name, int code, List<City> cities) {
        super();
        this.name = name;
        this.code = code;
        this.cities = cities;
    }

    /**
     * Getter of name
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name
     * 
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of code
     * 
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Setter of code
     * 
     * @param code
     *            the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Getter of cities
     * 
     * @return the cities
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * Setter of cities
     * 
     * @param cities
     *            the cities to set
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name;
    }

    public String[] getCitiesNameArray() {
        final int size = getCities().size();
        final String[] arrary = new String[size];
        for (int i = 0; i < size; i++) {
            arrary[i] = getCities().get(i).getName();
        }
        return arrary;
    }

    public String[] getCitiesUnique_codeArray() {
        final int size = getCities().size();
        final String[] arrary = new String[size];
        for (int i = 0; i < size; i++) {
            arrary[i] = "" + getCities().get(i).getUnique_code();
        }
        return arrary;
    }

    public City getCityByUniqueCode(int code) {
        for (City c : cities) {
            if (c.getUnique_code()  == code)
                return c;
        }
        return null;
    }

    public City getCityByName(String name) {
        for (City c : cities) {
            if (c.getName().equals(name))
                return c;
        }
        return null;
    }
}
