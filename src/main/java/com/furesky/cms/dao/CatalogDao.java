package com.furesky.cms.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.furesky.cms.model.Catalog;
@Mapper
public interface CatalogDao {
	public List<Catalog> getListByClassName(String className);
	public Catalog getById(String catalogId);	
	public int insert(Catalog catalog);
	public int update(Catalog catalog);
	public int deleteById(String catalogId);	
}
