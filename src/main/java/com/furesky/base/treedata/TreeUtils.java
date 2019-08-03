package com.furesky.base.treedata;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TreeUtils {

	public static <E extends Tree<E>> List<E> getTreeData(List<E> entityList) {
		//获取所有实体的索引
		Map<String, E> map=new HashMap<>();
		E entity;
		for (int i = entityList.size(); i > 0 ; i--) {
			entity=entityList.get(i-1);
			map.put(entity.peekNodeId(), entity);
		}		
		
		//按rank倒序
		Collections.sort(entityList,new TreeComparator<E>());
		//转为LinkedList，提高增删效率（待验证）
		LinkedList<E> list = new LinkedList<E>(entityList);
		
		
		//将子元素放入父对象
		E entity_parent;
		E entity_child;
		for (int i = 0; i < entityList.size(); i++) {
			entity_child=entityList.get(i);
			entity_parent=map.get(entity_child.peekParentId());
			if(entity_parent!=null){
				entity_parent.addChildNode(entity_child);
				
				//删除子元素
				list.remove(entity_child);
			}			
		}
		return list;	
	}	
}
