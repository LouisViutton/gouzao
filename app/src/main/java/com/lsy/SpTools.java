package com.lsy;

import android.content.Context;
import android.content.SharedPreferences;

public class SpTools {
    private final static String CONFIGFIILE="sp";

    public static void putString(String key, String value){
        SharedPreferences.Editor edit = getEdit();
        edit.putString(key,value);
        edit.apply();
    }

    public static String getString(String key,String defValue){
        SharedPreferences my_sp = getSharedPreferences();
        return my_sp.getString(key,defValue);
    }



    public static void putLong(String key,long value){
        SharedPreferences.Editor edit = getEdit();
        edit.putLong(key,value);
        edit.apply();
    }

    public static long getLong(String key,Long defValue){
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getLong(key,defValue);
    }



    public static void putInt(String key, int value) {
        SharedPreferences.Editor edit = getEdit();
        edit.putInt(key, value);
        edit.apply();
    }

    public static int getInt(String key, int defValue) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getInt(key,defValue);
    }



    public static void putFloat(String key, float value) {
        SharedPreferences.Editor edit = getEdit();
        edit.putFloat(key, value);
        edit.apply();
    }

    public static float getFloat(String key, float defValue) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getFloat(key,defValue);
    }



    public static void putBooleam(String key, boolean value) {
        SharedPreferences.Editor edit = getEdit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public static boolean getBoolean(String key, boolean devalue) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getBoolean(key, devalue);
    }



    public static SharedPreferences getSharedPreferences(){
        SharedPreferences sp = MyApp.app.getSharedPreferences(CONFIGFIILE,Context.MODE_PRIVATE);
        return sp;
    }

    public static SharedPreferences.Editor getEdit(){
        SharedPreferences my_sp = getSharedPreferences();
        SharedPreferences.Editor edit=my_sp.edit();
        return edit;
    }
}
