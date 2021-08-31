package com.githubscraper.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.githubscraper.requestoperation.ContributionCalendar;
import com.githubscraper.requestoperation.ContributionCount;
import com.githubscraper.requestoperation.CurrentJob;
import com.githubscraper.requestoperation.CurrentLocation;
import com.githubscraper.requestoperation.PinnedRepositories;
import com.githubscraper.requestoperation.ScrapeRequest;


//TODO: get the names and descriptions of the pinned repositories.
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
						String cc_res = ContributionCount.getContributionCount(page);
						objectNode.put("contributionCount", cc_res);
						break;
					}
					case "contribution-calendar":
						ArrayNode cc_res = ContributionCalendar.getContributionCalendar(page);
						objectNode.putArray("contributionCalendar").addAll(cc_res);
						break;
					case "current-job":
						String cj_res = CurrentJob.getCurrentJob(page);
						objectNode.put("currentJob", cj_res);
						break;
					case "current-location":
						String cl_res = CurrentLocation.getCurrentLocation(page);
						objectNode.put("currentLocation",cl_res);
						break;
					case "pinned-repositories":
						ArrayNode pr_res = PinnedRepositories.getPinnedRepos(page);
						objectNode.putArray("pinnedRepositories").addAll(pr_res);
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
