package com.githubscraper.requestoperation;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.githubscraper.core.OperationModel;

public class ContributionCalendar extends ScrapeRequest {

	public ContributionCalendar() {
		opModel = new OperationModel("contribution-calendar");
	}


	static public ArrayNode getContributionCalendar(HtmlPage page) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		DomNodeList<DomElement> svgs = page.getElementsByTagName("td");
		List<String> elements = new ArrayList<String>();
		for(DomElement svg: svgs) {

			HtmlTableCell element =  svg.getFirstByXPath("self::node()[@class='ContributionCalendar-day'and @data-level]");
			if(element != null) {
				elements.add(element.getAttribute("data-level"));
							
			}

		}
		
		ArrayNode res = objectMapper.valueToTree(elements);
		
		return res;
	}

}
