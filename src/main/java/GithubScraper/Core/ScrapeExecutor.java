package GithubScraper.Core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import GithubScraper.RequestOperation.ContributionCalendar;
import GithubScraper.RequestOperation.ContributionCount;
import GithubScraper.RequestOperation.ScrapeRequest;
import GithubScraper.Utility.StringOperations;

public class ScrapeExecutor {

	private List<ScrapeRequest> operations = new ArrayList<ScrapeRequest>();
	private String currentUser = "";
	
	public ScrapeExecutor(String currentUser) {
		this.currentUser = currentUser;
	}
	
	public void addOperation(ScrapeRequest operation) {
		operations.add(operation);
	}
	
	public String executeAll() {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode objectNode = objectMapper.createObjectNode();
		try (final WebClient webClient = new WebClient()) {
        	webClient.getOptions().setCssEnabled(false);
        	webClient.getOptions().setJavaScriptEnabled(false);
            final HtmlPage page = webClient.getPage("https://github.com/" + currentUser);

            //TODO: Find a structure which stores every output in a lightweight and programmatic way.
			for(ScrapeRequest operation : operations) {
				switch (operation.getOperationModel().getOperationName()) {
					case "contribution-count": {
						//System.out.println("Getting Contribution Count");					
						String res = ContributionCount.getContributionCount(page);
						objectNode.put("contribution-count", res);
			
			
						break;
					}
					case "contribution-calendar":
						ContributionCalendar.getContributionCalendar(page);
						objectNode.put("contribution-calendar", "test");
						break;
					default:
						throw new IllegalArgumentException("Unexpected value: " + operation.getOperationModel().getOperationName());
					}
			}
		} catch (FailingHttpStatusCodeException | IOException e) {

			objectNode = objectMapper.createObjectNode();
			e.printStackTrace();
		}

		return objectNode.toString();
		
		
	}

	
	
}
