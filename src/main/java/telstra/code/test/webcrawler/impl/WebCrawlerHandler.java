package telstra.code.test.webcrawler.impl;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telstra.code.test.webcrawler.Node;
import telstra.code.test.webcrawler.intf.WebCrawlerHandlerInterface;

public class WebCrawlerHandler implements WebCrawlerHandlerInterface{
	
	private int maxDepth;	   
    private HashSet<String> links;
    private final Logger slf4jLogger = LoggerFactory.getLogger(WebCrawlerHandler.class);
    public WebCrawlerHandler() {   
        links = new HashSet<>();
    }
    
    
	 public Node getAllLinksOfCurrentURL(Node node,int depth) {
	       
		
		 if (!node.getUrl().trim().isEmpty() && !links.contains(node.getUrl()) && (depth < maxDepth)) {
	         
			 slf4jLogger.info("URL :"+node.getUrl());
			
			 try {
	                links.add(node.getUrl().trim());
	                Document document = Jsoup.connect(node.getUrl()).get();
	                Elements linksOnPage = document.select("a[href]");

	                depth++;
	                
	                for (Element page : linksOnPage) {
	                	Node childNode = new Node();
	                	
	                	 if(page.attr("abs:href").contains("#") && (page.attr("abs:href").lastIndexOf("#")==page.attr("abs:href").length()-1))
	     	            {
	                		 childNode.setUrl(page.attr("abs:href").substring(0, page.attr("abs:href").lastIndexOf("#")));
	     	            }else { 
	     	            	childNode.setUrl(page.attr("abs:href"));
	     	            }
	     	             
	                	childNode.setTitle(page.attr("title"));
	                	node.getChildNode().add(getAllLinksOfCurrentURL(childNode, depth));
	                	
	                }
	            } catch (IOException e) {
	            	slf4jLogger.error("Invalid URL ["+node.getUrl()+"]"+e.getMessage());
	            }
	            
	           
	             
	        }
		 return node;
	    }



	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}
}
