


import com.githubscraper.core.ScrapeExecutor;
import com.githubscraper.requestoperation.ContributionCalendar;
import com.githubscraper.requestoperation.ContributionCount;
import com.githubscraper.requestoperation.CurrentJob;
import com.githubscraper.requestoperation.CurrentLocation;
import com.githubscraper.requestoperation.PinnedRepositories;




public class Main {

    
    public static void main(String[] args) {
    	
    	//Create commit count command object
    	ContributionCount cmtCount = new ContributionCount();
    	
    	//Create calendar count command object
    	ContributionCalendar cntCal = new ContributionCalendar();
    	
    	//Create current job command object
    	CurrentJob curJob = new CurrentJob();
    	
    	//Create current location command object
    	CurrentLocation curLoc = new CurrentLocation();
    	
    	//Create pinned repositories command object
    	PinnedRepositories curPins = new PinnedRepositories();

    	
    	//Create new executor to execute these commands
    	ScrapeExecutor executor = new ScrapeExecutor("AdnanCigtekin");
    	
    	//Append these commands to execution queue
    	executor.addOperation(cmtCount);
    	executor.addOperation(cntCal);
    	executor.addOperation(curJob);
    	executor.addOperation(curLoc);
    	executor.addOperation(curPins);
    	
    	//Execute all commands
    	String res = executor.executeAll();
    	
    	//Display result
    	System.out.println("RESULT:");
    	System.out.println(res);

    }
}
