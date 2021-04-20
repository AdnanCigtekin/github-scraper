package githubscraper.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import githubscraper.requestoperation.ContributionCalendar;
import githubscraper.requestoperation.ContributionCount;
import githubscraper.requestoperation.ScrapeRequest;


//TODO: Add location, job, username and get the names and descriptions of the pinned repositories.
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

			for(ScrapeRequest operation : operations) {
				switch (operation.getOperationModel().getOperationName()) {
					case "contribution-count": {					
						String res = ContributionCount.getContributionCount(page);
						objectNode.put("contribution-count", res);
						break;
					}
					case "contribution-calendar":
						ArrayNode res = ContributionCalendar.getContributionCalendar(page);
						objectNode.putArray("contribution-calendar").addAll(res);
						break;
					case "current-job":
						throw new UnsupportedOperationException("Not implemented yet");
					case "current-location":
						throw new UnsupportedOperationException("Not implemented yet");
					case "pinned-repositories":
						throw new UnsupportedOperationException("Not implemented yet");
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
