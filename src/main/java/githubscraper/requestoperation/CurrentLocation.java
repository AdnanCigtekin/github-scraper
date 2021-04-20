package githubscraper.requestoperation;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import githubscraper.core.OperationModel;
import githubscraper.utility.StringOperations;

public class CurrentLocation extends ScrapeRequest{

	public CurrentLocation() {
		opModel = new OperationModel("current-location");
	}
	
	static public String getCurrentLocation(HtmlPage page) {
		DomNodeList<DomElement> listItems =  page.getElementsByTagName("li");
		String currentLocation = "";
		
		for(DomElement listItem : listItems) {
        	HtmlElement element = (HtmlElement) listItem.getFirstByXPath("self::node()[@itemprop='homeLocation']");
        	if(element != null) {
        		currentLocation = StringOperations.stripAriaLabel(element.getAttribute("aria-label"));
        		break;
        	}
		}
		
		return currentLocation;
	}

}
