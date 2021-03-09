package GithubScraper.RequestOperation;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import GithubScraper.Core.OperationModel;

public class ContributionCalendar extends ScrapeRequest {

	public ContributionCalendar() {
		opModel = new OperationModel("contribution-calendar");
	}
	
	static public List<String> getContributionCalendar(HtmlPage page) {
		return new ArrayList<String>();
	}

}
