package GithubScraper.Core;

import java.util.ArrayList;
import java.util.List;

import GithubScraper.RequestOperation.ScrapeRequest;

public class ScrapeExecutor {

	private List<ScrapeRequest> operations = new ArrayList<ScrapeRequest>();
	
	public ScrapeExecutor() {
	}
	
	public void addOperation(ScrapeRequest operation) {
		operations.add(operation);
	}
	
	public void executeAll() {
		for(ScrapeRequest operation : operations) {
			switch (operation.getOperationModel().getOperationName()) {
				//TODO: Implement real execution
				case "commit-count": {
					System.out.println("Commit count request received");
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + operation.getOperationModel().getOperationName());
				}
		}
	}

	
	
}
