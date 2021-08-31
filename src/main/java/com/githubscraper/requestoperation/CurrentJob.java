package com.githubscraper.requestoperation;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.githubscraper.core.OperationModel;
import com.githubscraper.utility.StringOperations;

public class CurrentJob extends ScrapeRequest{

	public CurrentJob() {
		opModel = new OperationModel("current-job");
	}
	
	static public String getCurrentJob(HtmlPage page) {
		DomNodeList<DomElement> listItems =  page.getElementsByTagName("li");
		String currentJob = "";
		
		for(DomElement listItem : listItems) {
        	HtmlElement element = (HtmlElement) listItem.getFirstByXPath("self::node()[@itemprop='worksFor']");
        	if(element != null) {
        		currentJob = StringOperations.stripAriaLabel(element.getAttribute("aria-label"));
        		break;
        	}
		}
		
		
		return currentJob;
	}

}
