package GithubScraper.Core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import GithubScraper.RequestOperation.ContributionCount;
import GithubScraper.RequestOperation.ScrapeRequest;

public class ScrapeExecutor {

	private List<ScrapeRequest> operations = new ArrayList<ScrapeRequest>();
	
	public ScrapeExecutor() {
	}
	
	public void addOperation(ScrapeRequest operation) {
		operations.add(operation);
	}
	
	public void executeAll() {
		try (final WebClient webClient = new WebClient()) {
        	webClient.getOptions().setCssEnabled(false);
        	webClient.getOptions().setJavaScriptEnabled(false);
            final HtmlPage page = webClient.getPage("https://github.com/AdnanCigtekin");
            
			for(ScrapeRequest operation : operations) {
				switch (operation.getOperationModel().getOperationName()) {
					case "contribution-count": {
						ContributionCount.getContributionCount(page);
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + operation.getOperationModel().getOperationName());
					}
			}
		} catch (FailingHttpStatusCodeException | IOException e) {

			e.printStackTrace();
		}
	}

	
	
}
