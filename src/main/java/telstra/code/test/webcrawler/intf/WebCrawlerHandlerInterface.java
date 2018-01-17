package telstra.code.test.webcrawler.intf;

import telstra.code.test.webcrawler.Node;

public interface WebCrawlerHandlerInterface {
	public Node getAllLinksOfCurrentURL(Node  node,int depth);
	public void setMaxDepth(int maxDepth);
}
