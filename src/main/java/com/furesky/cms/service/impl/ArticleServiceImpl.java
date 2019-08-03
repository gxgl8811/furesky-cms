package com.furesky.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furesky.cms.dao.ArticleDao;
import com.furesky.cms.model.Article;
import com.furesky.cms.service.ArticleService;  
  
 
  
@Service
public class ArticleServiceImpl implements ArticleService {  
    @Autowired  
    private ArticleDao articleDao;

	@Override
	public Article getById(String articleId) {
		return articleDao.getById(articleId);
	}

	@Override
	public List<Article> getListByCatalogId(String catalogId) {
		return articleDao.getListByCatalogId(catalogId);
	}

	@Override
	public int insert(Article article) {
		return articleDao.insert(article);
	}

	@Override
	public int update(Article article) {
		return articleDao.update(article);
	}

	@Override
	public int deleteById(String articleId) {
		return articleDao.deleteById(articleId);
	}  
 
  
}  
