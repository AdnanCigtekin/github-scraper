package com.githubscraper.pojo;

public class PinnedRepoPOJO {

	private String repoName = "";
	private String repoDesc = "";
	private String repoLang = "";
	private String repoStars = "";
	private String repoForks = "";
	
	public PinnedRepoPOJO() {
		
	}

	public String getRepoName() {
		return repoName;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	public String getRepoDesc() {
		return repoDesc;
	}

	public void setRepoDesc(String repoDesc) {
		this.repoDesc = repoDesc;
	}

	public String getRepoLang() {
		return repoLang;
	}

	public void setRepoLang(String repoLang) {
		this.repoLang = repoLang;
	}

	public String getRepoStars() {
		return repoStars;
	}

	public void setRepoStars(String repoStars) {
		this.repoStars = repoStars;
	}

	public String getRepoForks() {
		return repoForks;
	}

	public void setRepoForks(String repoForks) {
		this.repoForks = repoForks;
	}

	@Override
	public String toString() {
		return "repoName : " + repoName +
				"\nrepoDesc : " + repoDesc +
				"\nrepoLang : " + repoLang +
				"\nrepoStars : " + repoStars+
				"\nRepoForks : " + repoForks;
	}
	
	

}
