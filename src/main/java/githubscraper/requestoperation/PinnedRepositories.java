package githubscraper.requestoperation;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.svg.SvgRect;

import githubscraper.core.OperationModel;
import githubscraper.pojo.PinnedRepoPOJO;

public class PinnedRepositories  extends ScrapeRequest {

	public PinnedRepositories(){
		opModel = new OperationModel("pinned-repositories");
	}

	static public ArrayNode getPinnedRepos(HtmlPage page) {
		ObjectMapper objectMapper = new ObjectMapper();
		DomNodeList<DomElement> orderedLists = page.getElementsByTagName("ol");
		
		List<PinnedRepoPOJO> pinnedRepos = new ArrayList<PinnedRepoPOJO>(); 
		
		for(DomElement orderedList: orderedLists) {
			HtmlElement element =  (HtmlElement)orderedList.getFirstByXPath("self::node()[@class='d-flex flex-wrap list-style-none gutter-condensed mb-4 js-pinned-items-reorder-list']");
			
			if(element != null) {
				
				for(DomElement list : element.getChildElements()) {
					
					DomNodeList<HtmlElement> spans = list.getElementsByTagName("span");
					PinnedRepoPOJO curRepoProps = new PinnedRepoPOJO();
					
					for(HtmlElement span : spans) {
						
						// Get repo name
						HtmlElement repoName = (HtmlElement) span.getFirstByXPath("self::node()[@class='repo']");
						if(repoName != null) {
							curRepoProps.setRepoName(repoName.asText());
						}
						
						//Get repo lang
						HtmlElement repoLang = (HtmlElement) span.getFirstByXPath("self::node()[@itemprop='programmingLanguage']");
						if(repoLang != null) {
							curRepoProps.setRepoLang(repoLang.asText());
						}
						
					}
					
					DomNodeList<HtmlElement> paragraphs = list.getElementsByTagName("p");
					//Get repo descriptions
					for(HtmlElement paragraph : paragraphs) {
						HtmlElement repoDesc = (HtmlElement) paragraph.getFirstByXPath("self::node()[@class='pinned-item-desc color-text-secondary text-small d-block mt-2 mb-3']");
						if(repoDesc != null) {
							curRepoProps.setRepoDesc(repoDesc.asText());
						}
					}
					
					DomNodeList<HtmlElement> links = list.getElementsByTagName("a");
					
					for(HtmlElement link : links) {
						//Get repo stars
						HtmlElement repoStars = (HtmlElement) link.getFirstByXPath("self::node()[substring(@href,string-length(@href) - string-length('stargazers') + 1 ) = 'stargazers']");
						if(repoStars != null) {
							curRepoProps.setRepoStars(repoStars.asText());
						}
						
						//Get repo forks
						HtmlElement repoForks = (HtmlElement) link.getFirstByXPath("self::node()[substring(@href,string-length(@href) - string-length('network') + 1 ) = 'network']");
						if(repoForks != null) {
							curRepoProps.setRepoForks(repoForks.asText());
						}
						
					}		
					pinnedRepos.add(curRepoProps);
					//System.out.println(curRepoProps.toString());

				}
							
			}

		}
		ArrayNode res = objectMapper.valueToTree(pinnedRepos);
		return res;

	}
	
}
