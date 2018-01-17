package telstra.code.test.webcrawler;


import com.google.gson.Gson;

import telstra.code.test.webcrawler.impl.WebCrawlerHandler;
import telstra.code.test.webcrawler.intf.WebCrawlerHandlerInterface;

public class WebCrawler{
   
	private WebCrawlerHandlerInterface handler;
	
    public static void main(String[] args) { 																																																																															
    	
    	//Construct the root node for web-crawling
    	Node rootNode = new Node();
    	rootNode.setUrl("http://rediff.com/");
    	rootNode.setTitle("rediff");
    	
    	//Inject WebCrawlerHandler to handle the request
    	WebCrawler webCrawler = new WebCrawler();
    	webCrawler.setHandler(new WebCrawlerHandler());
    	
    	//set maximum depth
    	webCrawler.getHandler().setMaxDepth(3);
    	Node node =webCrawler.getHandler().getAllLinksOfCurrentURL(rootNode, 0);
    	
    	webCrawler.constructFinalOutput(node);
    }
/*
 * this method consumes node as input and converts the object into JSON message
 */
	public void constructFinalOutput(Node node) {
		
		Gson gson = new Gson();
		String jsonInString = gson.toJson(node);
		System.out.println(jsonInString);
	}
	
	

	public WebCrawlerHandlerInterface getHandler() {
		return handler;
	}

	public void setHandler(WebCrawlerHandlerInterface handler) {
		this.handler = handler;
	}
}