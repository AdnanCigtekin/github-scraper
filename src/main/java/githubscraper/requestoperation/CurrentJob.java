package githubscraper.requestoperation;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import githubscraper.core.OperationModel;

public class CurrentJob extends ScrapeRequest{

	public CurrentJob() {
		opModel = new OperationModel("current-job");
	}
	
	static public String getCurrentJob(HtmlPage page) {
		DomNodeList<DomElement> spans =  page.getElementsByTagName("span");
		String currentJob = "";
		
		for(DomElement span : spans) {
        	HtmlElement element = (HtmlElement) span.getFirstByXPath("self::node()[@class='p-org']");
        	if(element != null) {
        		currentJob = element.asText();
        		break;
        	}
		}
		

		
		return currentJob;
	}

}
