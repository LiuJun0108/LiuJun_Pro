package com.rying.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/iconcls")
public class IconclsController {
	private static final String ICON_PATH = "jquery-easyui-1.3.6/themes/icon.css";

	@RequestMapping("/iconcls")
	public String iconcls(HttpServletRequest request, ModelMap model) {
		String fullpath = request.getSession().getServletContext().getRealPath("/") + ICON_PATH;
		File cssfile = new File(fullpath);
		String css = this.getCss(cssfile);
		List<String> iconList = this.getIconList(css);
		
		model.addAttribute("iconList", iconList);
		return "sys/iconcls";
	}

	private String getCss(File cssfile) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(cssfile)));

			StringBuilder sb = new StringBuilder();
			String temp;
			while ((temp = reader.readLine()) != null) {
				sb.append(temp);
			}
			reader.close();
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private List<String> getIconList(String css) {
		List<String> result = new ArrayList<>();
		
        Pattern pattern = Pattern.compile("(icon-)\\w+");  
        Matcher matcher = pattern.matcher(css);  
        while(matcher.find()){  
        	result.add(matcher.group());
        }  
		return result;
	}

	public static void main(String[] args) {
		new IconclsController().getIconList("");
	}
}
