package com.furesky.cms.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.furesky.cms.model.Article;

@Mapper
public interface ArticleDao {
	public Article getById(String articleId);
	public List<Article> getListByCatalogId(String calalogId);
	public int insert(Article article);
	public int update(Article article);
	public int deleteById(String articleId);	
}
