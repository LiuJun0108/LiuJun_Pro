package com.rying.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertiesUtil {
	private static final String PROPERTIES_PATH = "/conf.properties";
	private static Properties prop = new Properties();

	static {
		URL url = PropertiesUtil.class.getResource(PROPERTIES_PATH);
		if (url != null) {
			try {
				InputStream is = url.openStream();
				prop.load(is);
				is.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String get(String key) {
		if (key == null)
			throw new NullPointerException("key is null");

		return prop.getProperty(key);
	}

	public static void main(String[] args) {
		String s = get("upload_path");
		System.out.println(s);
	}
}
