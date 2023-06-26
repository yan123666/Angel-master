package com.example.chat;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class SharedPreferenceUtil {
	private static final String TAG = "SharedPreferenceUtil";
	private static SharedPreferences sp;
	
	public static String savePreference(Context context , String key , Object value){
		SharedPreferences preferences = context.getSharedPreferences("tt_preference_file", MODE_PRIVATE);
		Editor editor = preferences.edit();
		if(value instanceof Integer)
		{
			editor.putInt(key, (Integer) value);
		}
		else if(value instanceof Long)
		{
			editor.putLong(key, (Long) value);
		}
		else if(value instanceof Float)
		{
			editor.putFloat(key, (Float) value);
		}
		else if(value instanceof Boolean)
		{
			editor.putBoolean(key, (Boolean) value);
		}
		else if(value instanceof String)
		{
			editor.putString(key, String.valueOf(value));
		}
		else
		{
			throw new IllegalArgumentException("不能存储这种类型");
		}
		editor.commit();
        return key;
    }
	
	public static Object getPreference(Context context , String key ,Object defaultValue){
		SharedPreferences preferences = context.getSharedPreferences("tt_preference_file", MODE_PRIVATE);
		if(defaultValue instanceof Integer)
		{
			return preferences.getInt(key, (Integer) defaultValue);
		}
		else if(defaultValue instanceof Long)
		{
			return preferences.getLong(key, (Long) defaultValue);
		}
		else if(defaultValue instanceof Float)
		{
			return preferences.getFloat(key, (Float) defaultValue);
		}
		else if(defaultValue instanceof Boolean)
		{
			return preferences.getBoolean(key, (Boolean) defaultValue);
		}
		else if(defaultValue instanceof String)
		{
			return preferences.getString(key, String.valueOf(defaultValue));
		}
		else
		{
			throw new IllegalArgumentException("不能得到这种类型");
		}
	}


	/**
	 * 4.存储账本bookBean的list
	 */
	public static void putSelectBean(Context context, List<Msg> phoneList, String key) {
		if (sp == null) {
			sp = context.getSharedPreferences("config", MODE_PRIVATE);
		}
		Editor editor = sp.edit();
		Gson gson = new Gson();
		String json = gson.toJson(phoneList);
		editor.putString(key, json);
		editor.commit();
	}


	/**
	 * 读取账本SelectPhone的list
	 */
	public static List<Msg> getSelectBean(Context context, String key) {
		if (sp == null) {
			sp = context.getSharedPreferences("config", MODE_PRIVATE);
		}
		Gson gson = new Gson();
		String json = sp.getString(key, null);
		Type type = new TypeToken<List<Msg>>() {
		}.getType();
		List<Msg> arrayList = gson.fromJson(json, type);
		return arrayList;
	}





}
