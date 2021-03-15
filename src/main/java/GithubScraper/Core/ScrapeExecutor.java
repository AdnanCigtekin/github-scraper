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

import GithubScraper.RequestOperation.ContributionCount;
import GithubScraper.RequestOperation.ScrapeRequest;

public class ScrapeExecutor {

	private List<ScrapeRequest> operations = new ArrayList<ScrapeRequest>();
	private String currentUser = "";
	
	public ScrapeExecutor(String currentUser) {
		this.currentUser = currentUser;
	}
	
	public void addOperation(ScrapeRequest operation) {
		operations.add(operation);
	}
	
	public void executeAll() {
		try (final WebClient webClient = new WebClient()) {
        	webClient.getOptions().setCssEnabled(false);
        	webClient.getOptions().setJavaScriptEnabled(false);
            final HtmlPage page = webClient.getPage("https://github.com/" + currentUser);
            ObjectMapper objectMapper = new ObjectMapper();

            
            //TODO: Find a structure which stores very output in a lightweight and programmatic way.
			for(ScrapeRequest operation : operations) {
				switch (operation.getOperationModel().getOperationName()) {
					case "contribution-count": {
						System.out.println("Getting Contribution Count");
						ObjectNode objectNode = objectMapper.createObjectNode();
						String res = ContributionCount.getContributionCount(page);
						objectNode.put("contribution-count", res);
						System.out.println("GOt contribution count as JSON!");
						break;
					}
					case "contribution-calendar":
						break;
					default:
						throw new IllegalArgumentException("Unexpected value: " + operation.getOperationModel().getOperationName());
					}
			}
		} catch (FailingHttpStatusCodeException | IOException e) {

			e.printStackTrace();
		}
		
	}

	
	
}
