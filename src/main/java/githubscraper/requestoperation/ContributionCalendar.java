package githubscraper.requestoperation;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.svg.SvgRect;

import githubscraper.core.OperationModel;

public class ContributionCalendar extends ScrapeRequest {

	public ContributionCalendar() {
		opModel = new OperationModel("contribution-calendar");
	}

	//TODO: Return as JSON String
	static public ArrayNode getContributionCalendar(HtmlPage page) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		DomNodeList<DomElement> svgs = page.getElementsByTagName("rect");
		List<String> elements = new ArrayList<String>();
		for(DomElement svg: svgs) {
			SvgRect element =  svg.getFirstByXPath("self::node()[@class='ContributionCalendar-day'and @data-count]");
			if(element != null) {
				elements.add(element.getAttribute("data-count"));
							
			}

		}
		
		ArrayNode res = objectMapper.valueToTree(elements);
		
		return res;
	}

}
