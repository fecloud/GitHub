package com.android.test.provinceandcity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.test.provinceandcity.bean.City;
import com.android.test.provinceandcity.bean.Province;
import com.android.test.provinceandcity.parse.ProvinceParse;

public class ProvinceActivity extends Activity implements OnClickListener {

    private ProvinceParse parse;

    private Spinner spinner1, spinner2;

    private Province currentProvince;

    private City currentCity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
    }

    /**
     * 
     */
    private void init() {
        parse = ProvinceParse.build(this, R.raw.province, R.raw.cities);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        findViewById(R.id.button1).setOnClickListener(this);

        ArrayAdapter<Province> provinceAdapter = new ArrayAdapter<Province>(this, R.layout.simple_spinner_item,
                android.R.id.text1, parse.getProvinces());
        spinner1.setAdapter(provinceAdapter);

        spinner1.setOnItemSelectedListener(new ProvinceAdapter());
        spinner2.setOnItemSelectedListener(new CityAdapter());
    }

    public void onProvinChange(int position) {
        currentProvince = parse.getProvinces().get(position);
        ArrayAdapter<City> cityAdapter = new ArrayAdapter<City>(this, R.layout.simple_spinner_item, android.R.id.text1,
                currentProvince.getCities());
        spinner2.setAdapter(cityAdapter);
    }

    class ProvinceAdapter implements OnItemSelectedListener {

        /**
         * (non-Javadoc)
         * 
         * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView,
         *      android.view.View, int, long)
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            onProvinChange(position);
        }

        /**
         * (non-Javadoc)
         * 
         * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android.widget.AdapterView)
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    }

    final class CityAdapter extends ProvinceAdapter {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            currentCity = currentProvince.getCities().get(position);
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        Toast.makeText(this, "" + currentProvince + currentCity, Toast.LENGTH_SHORT).show();
    }

}
