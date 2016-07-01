package com.system.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertyUtil {
	private static Map<String, String> map = new HashMap<String, String>();
	
	public static String getDisplayName(String key) {
		if (key == null)
			return null;
		String property = map.get(key);
		return property == null ? key : property;
	}
	
	public void init() {
		List<String> fileNames = new ArrayList<String>();
		fileNames.add("config.properties");
		InputStream is = null;
		try {
			for (String fileName : fileNames) {
				Properties displayName = new Properties();
				is = PropertyUtil.class.getResourceAsStream(fileName);
				displayName.load(is);
				Set<Object> set = displayName.keySet();
				for (Object object : set) {
					String key = object.toString();
					map.put(key, displayName.getProperty(key));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
