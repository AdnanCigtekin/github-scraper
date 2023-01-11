package com.githubscraper.requestoperation;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.svg.SvgRect;
import com.githubscraper.core.OperationModel;

public class ContributionCalendar extends ScrapeRequest {

	public ContributionCalendar() {
		opModel = new OperationModel("contribution-calendar");
	}


	static public ArrayNode getContributionCalendar(HtmlPage page) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		DomNodeList<DomElement> svgs = page.getElementsByTagName("rect");
		List<String> elements = new ArrayList<String>();
		System.out.println("svg count : " + svgs.size());
		for(DomElement svg: svgs) {

			SvgRect element =  svg.getFirstByXPath("self::node()[@class='ContributionCalendar-day'and @data-level and @data-date]");
			if(element != null) {
				System.out.println(svg);
				elements.add(element.getAttribute("data-level"));
							
			}

		}
		
		ArrayNode res = objectMapper.valueToTree(elements);
		System.out.println("res size : " + res.size());
		return res;
	}

}
