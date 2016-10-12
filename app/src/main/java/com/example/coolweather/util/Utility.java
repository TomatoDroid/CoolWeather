package com.example.coolweather.util;

import android.text.TextUtils;

import com.example.coolweather.db.CoolWeatherDB;
import com.example.coolweather.modle.City;
import com.example.coolweather.modle.County;
import com.example.coolweather.modle.Province;

/**
 * Created by 臻 on 2016/10/12.
 */

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     * */
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,
                                                               String response){
        if(!TextUtils.isEmpty(response)){
            String[] allProcinces = response.split(",");
            if(allProcinces!=null && allProcinces.length>0){
                for(String p:allProcinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的市级数据
     * */
    public  static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,
                                                String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if(allCities!=null && allCities.length>0){
                for(String c:allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    //将解析出来的数据保存到City表中
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的县级数据
     * */
    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,
                                                 String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            String[] allCountries = response.split(",");
            if(allCountries!=null && allCountries.length>0){
                for(String c:allCountries){
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
