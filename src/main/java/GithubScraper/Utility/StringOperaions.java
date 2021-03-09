package GithubScraper.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringOperaions {

	static public List<String> returnMatchedPattern(String pattern, String input) {
		Pattern r = Pattern.compile(pattern);
		
		Matcher m = r.matcher(input);
		if(m.find()) {
			int groupCount = m.groupCount();
			List<String> resultList = new ArrayList<String>(); 
			resultList.add(m.group());
			return resultList;
		}
		return new ArrayList<String>();
	}

}
