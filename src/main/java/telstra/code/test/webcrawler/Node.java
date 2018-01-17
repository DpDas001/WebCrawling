package telstra.code.test.webcrawler;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	public Node() {
		
	}
	public Node(String url, String title, List<Node> nodes) {
		super();
		this.url = url;
		this.title = title;
		this.nodes = nodes;
	}
	private String url;
	private String title;
	private List<Node> nodes;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Node> getChildNode() {
		
		if(nodes== null) {
			nodes =  new ArrayList<>();
		}
		return nodes;
	}
	public void setChildNode(List<Node> nodes) {
		this.nodes = nodes;
	}
	@Override
	public String toString() {
		return "Node [url=" + url + ", title=" + title + ", nodes=" + nodes.toString() + "]";
	}
	
	
}
