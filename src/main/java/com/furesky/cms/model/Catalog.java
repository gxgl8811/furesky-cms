package com.furesky.cms.model;

import java.util.ArrayList;
import java.util.List;

import com.furesky.base.treedata.Tree;

public class Catalog implements Tree<Catalog>{
	private String catalogId;
	private String catalogName;
	private String parentId;
	private String rank;
	private String className;	

	private List<Catalog> nodes;
	
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public String getText() {
		return catalogName;
	}
	public void setText(String text) {
		this.catalogName = text;
	}
	public List<Catalog> getNodes() {
		return nodes;
	}
	public void setNodes(List<Catalog> nodes) {
		this.nodes = nodes;
	}	
		
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String peekNodeId() {
		return catalogId+"";
	}
	@Override
	public String peekParentId() {
		return parentId+"";
	}
	@Override
	public String peekRank() {
		return rank;
	}
	@Override
	public List<Catalog> peekChildNodes() {
		return nodes;
	}
	@Override
	public void addChildNodes(List<Catalog> nodes) {
		this.nodes = nodes;
		
	}
	@Override
	public void addChildNode(Catalog node) {
		if(nodes==null){
			nodes=new ArrayList<>();
		}
		nodes.add(node);
	}
}
