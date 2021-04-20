# Github Scraper

This library is created for scraping any user's profile in GitHub in an easy and fast way.


### Installation:
In order to use this library you need to install the following dependencies

[HtmlUnit - 2.47.1](https://mvnrepository.com/artifact/net.sourceforge.htmlunit/htmlunit/2.47.1)

[Jackson Core - 2.0.1](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core/2.0.1)

After that, you can import this library any way you want.

### How to use:
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
### Supported Commands
**ContributionCount** : A command object for getting total contribution amount when a user's page has loaded initially.

**ContrbutionCalendar** : A command which returns an array which includes everyday's contribution activity.

**CurrentJob** : A command which returns a string which contains user's current job.(If it is defined, else it returns empty string as value)

**CurrentLocation** : A command which returns a string which contains user's contains current location of the user.(If it is defined, else it returns empty string as value)

**PinnedRepositories** : A command which returns an object which contains user's pinned repositories' name, description, star amount and fork amount.


