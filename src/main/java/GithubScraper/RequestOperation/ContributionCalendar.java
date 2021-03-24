package GithubScraper.RequestOperation;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.svg.SvgRect;

import GithubScraper.Core.OperationModel;

public class ContributionCalendar extends ScrapeRequest {

	public ContributionCalendar() {
		opModel = new OperationModel("contribution-calendar");
	}

	//TODO: Return as JSON String
	static public List<String> getContributionCalendar(HtmlPage page) {

		DomNodeList<DomElement> svgs = page.getElementsByTagName("rect");
		for(DomElement svg: svgs) {
			SvgRect element =  svg.getFirstByXPath("self::node()[@class='ContributionCalendar-day'and @data-count]");
			if(element != null) {
				System.out.println(element.getAttribute("data-count"));
				
			}

		}

		return new ArrayList<String>();
	}

}
