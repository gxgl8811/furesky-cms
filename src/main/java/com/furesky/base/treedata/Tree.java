package com.furesky.base.treedata;

import java.util.List;

/**
 * 树形结构，数据实体抽象类
 * @author jianda
 *
 */
public interface Tree<E>{
	
	/**
	 * 获取节点id
	 * @return
	 */
	public String peekNodeId();
	
	/**
	 * 获取父节点id
	 * @return
	 */
	public String peekParentId();
	
	/**
	 * 获取排序
	 * @return
	 * jianda
	 * 2019年4月29日
	 */
	public String peekRank();
	
	/**
	 * 获取子节点集合
	 * @param nodes
	 * jianda
	 * 2019年4月29日
	 */
	public List<E> peekChildNodes();
	
	/**
	 * 设置子节点集合
	 * @param nodes
	 * jianda
	 * 2019年4月29日
	 */
	public void addChildNodes(List<E> nodes);			
	
	/**
	 * 添加单个子节点
	 * @param node
	 * jianda
	 * 2019年4月29日
	 */
	public void addChildNode(E node);
}
