# Github Scraper

This library is created for scraping any user's profile in GitHub in an easy and fast way.

#### How to use:
```java
    	//Create commit count command object
    	ContributionCount cmtCount = new ContributionCount();
    	
    	//Create calendar count command object
    	ContributionCalendar cntCal = new ContributionCalendar();
    	
    	//Create new executor to execute these commands
    	ScrapeExecutor executor = new ScrapeExecutor("AdnanCigtekin");
    	
    	//Append these commands to execution queue
    	executor.addOperation(cmtCount);
    	executor.addOperation(cntCal);

    	//Execute all commands
    	String res = executor.executeAll();
    	
    	//Display result
    	System.out.println("RESULT:");
    	System.out.println(res);
```
> NOTE: Currently this library is in WIP so more feature will be added in the future.