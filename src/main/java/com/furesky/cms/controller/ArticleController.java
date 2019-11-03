package com.furesky.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.furesky.base.Result;
import com.furesky.base.utils.IdGen;
import com.furesky.cms.model.Article;
import com.furesky.cms.service.ArticleService;

@Controller
@RequestMapping(value = "/cms/article", method = RequestMethod.POST)
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@ResponseBody
	@RequestMapping("/getList")
	public Result<List<Article>> getArticleList(HttpServletRequest request) {
		String catalogId = request.getParameter("catalogId");
		if (StringUtils.isEmpty(catalogId)) {
			return Result.error("未找到相关目录");
		}
		List<Article> list = articleService.getListByCatalogId(catalogId);
		if(list==null){
			return Result.error("未找到相关文章");
		}
		return Result.success(list);
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public Object addArticle() {
		return "article";
	}
	
	@ResponseBody
	@RequestMapping(value="/addArticle",method=RequestMethod.POST)
	public Result<String> addArticle(HttpServletRequest request) {
		String title=request.getParameter("title");
		String catalogId=request.getParameter("catalogId");
				
		if(StringUtils.isEmpty(title) || StringUtils.isEmpty(catalogId)){
			return Result.error("输入不能为空！");
		}
		
		Article article=new Article();
		article.setArticleId(IdGen.uuid());
		article.setTitle("<h1>"+title+"</h1>");
		article.setCatalogId(catalogId);
		article.setAuthor("sys");
		article.setSummary("待编辑");
		article.setContent("待编辑");
		int result=articleService.insert(article);

		if(result==1){
			return Result.success();
		}
		return Result.error();
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public Object changeArticle() {
		return "update_article";
	}
	
	@ResponseBody
	@RequestMapping(value="/changeCatalog",method=RequestMethod.POST)
	public Result<String> changeCatalog(HttpServletRequest request) {
		String newCatalogId=request.getParameter("newCatalogId");
		String articleId=request.getParameter("articleId");
		
		if(StringUtils.isEmpty(newCatalogId) || StringUtils.isEmpty(articleId)){
			return Result.error("输入不能为空！");
		}
		
		Article article=new Article();
		article.setArticleId(articleId);
		article.setCatalogId(newCatalogId);
		int result=articleService.update(article);
		
		if(result==1){
			return Result.success();
		}
		return Result.error();
	}
	
	@ResponseBody
	@RequestMapping(value="/get",method=RequestMethod.POST)
	public Result<Article> getById(HttpServletRequest request) {
		String articleId=request.getParameter("articleId");
				
		if(StringUtils.isEmpty(articleId)){
			return Result.error("输入不能为空！");
		}
		
		Article article=articleService.getById(articleId);
		return Result.success(article);
	}

	@ResponseBody
	@RequestMapping("/edit")
	public Result<String> editArticle(HttpServletRequest request) {
		String articleId=request.getParameter("articleId");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		if(StringUtils.isEmpty(articleId)){
			return Result.error("修改失败，文章信息不完整！");
		}
		
		Article article=new Article();
		article.setArticleId(articleId);
		article.setTitle(title);
		article.setContent(content);		
		int result= articleService.update(article);
		if(result==1){
			return Result.success();
		}
		return Result.error();
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Result<String> deleteArticle(HttpServletRequest request) {
		String articleId=request.getParameter("del_articleId");
		if(StringUtils.isEmpty(articleId)){
			return Result.error("请选择要删除的文章！");
		}
		int result= articleService.deleteById(articleId);
		if(result==1){
			return Result.success();
		}
		return Result.error();
	}
}
