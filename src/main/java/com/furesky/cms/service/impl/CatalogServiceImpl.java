package com.furesky.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furesky.cms.dao.CatalogDao;
import com.furesky.cms.model.Catalog;
import com.furesky.cms.service.CatalogService;  
  
 
  
@Service
public class CatalogServiceImpl implements CatalogService {  
    @Autowired  
    private CatalogDao catalogDao;  
    
    @Override
	public List<Catalog> getListByClassName(String className) {
		return catalogDao.getListByClassName(className);
	}

	@Override
	public Catalog getById(String catalogId) {
		return catalogDao.getById(catalogId);
	}	

	@Override
	public int insert(Catalog catalog) {
		return catalogDao.insert(catalog);
	}

	@Override
	public int update(Catalog catalog) {
		return catalogDao.update(catalog);
	}

	@Override
	public int deleteById(String catalogId) {
		return catalogDao.deleteById(catalogId);
	}  
  
}  
