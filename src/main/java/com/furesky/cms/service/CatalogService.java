package com.furesky.cms.service;

import java.util.List;

import com.furesky.cms.model.Catalog;

public interface CatalogService {
	public List<Catalog> getListByClassName(String className);
	public Catalog getById(String catalogId);	
	public int insert(Catalog catalog);
	public int update(Catalog catalog);
	public int deleteById(String catalogId);
}
