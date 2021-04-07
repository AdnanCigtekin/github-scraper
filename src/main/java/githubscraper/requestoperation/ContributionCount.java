package githubscraper.requestoperation;

import java.util.List;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import githubscraper.core.OperationModel;
import githubscraper.utility.StringOperations;

public class ContributionCount extends ScrapeRequest {

	public ContributionCount() {
		opModel = new OperationModel("contribution-count");
	}
	
	
	static public String getContributionCount(HtmlPage page) {
		//System.out.println("Contribution count request received");
		DomNodeList<DomElement> headers =  page.getElementsByTagName("h2");
		String contribCount = "";
		for(DomElement header : headers) {
        	HtmlElement element = (HtmlElement) header.getFirstByXPath("self::node()[@class='f4 text-normal mb-2']");
        	if(element != null) {
        		contribCount = element.asText();
        	}
        }
		List<String> result = StringOperations.returnMatchedPattern("[0-9]+", contribCount);
		//System.out.println(result.get(0));
		//System.out.println("Contribution count done!");
		return result.get(0);
	}

}
