package com.jprogrammers.language;

import java.util.ResourceBundle;

public class LanguageUtil {
	
	private static ResourceBundle bundle;
	
	static {
        bundle = ResourceBundle.getBundle("com.jprogrammers.language.language-fa", new UTF8Control());
	}

	public static String get(String key){
		return bundle.getString(key);
	}

    public static String get(String key, String... values){
        String result = bundle.getString(key);
        for(String x : values){
            result = result.replace("x", x);
        }
        return result;
    }


}
