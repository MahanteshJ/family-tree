package com.geeks.challenge.family.tree;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private Node parent;
	private Person byBloodRelated;
	private Person byMarriage;
	private List<Node> children;
	
	public Node(Person byBloodRelated) {
		this.byBloodRelated = byBloodRelated;
		this.children = new ArrayList<Node>();
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Person getByBloodRelated() {
		return byBloodRelated;
	}

	public Person getByMarriage() {
		return byMarriage;
	}

	public void setByMarriage(Person byMarriage) {
		this.byMarriage = byMarriage;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(Node childNode) {
		children.add(childNode);
	}
	
}
