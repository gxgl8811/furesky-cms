package com.furesky.cms.service;

import java.util.List;

import com.furesky.cms.model.Article;

public interface ArticleService {
	public Article getById(String articleId);
	public List<Article> getListByCatalogId(String catalogId);
	public int insert(Article article);
	public int update(Article article);
	public int deleteById(String articleId);	
}
