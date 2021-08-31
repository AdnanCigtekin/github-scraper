package com.githubscraper.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringOperations {

	static public List<String> returnMatchedPattern(String pattern, String input) {
		Pattern r = Pattern.compile(pattern);
		
		Matcher m = r.matcher(input);
		if(m.find()) {
			List<String> resultList = new ArrayList<String>(); 
			resultList.add(m.group());
			return resultList;
		}
		return new ArrayList<String>();
	}
	
	static public String stripAriaLabel(String input) {
		return input.split(":")[1].split(" ")[1];
	}
	
	

}
