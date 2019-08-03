package com.furesky.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.furesky.base.ActionResult;
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
	public ActionResult getArticleList(HttpServletRequest request) {
		String catalogId = request.getParameter("catalogId");
		if (StringUtils.isEmpty(catalogId)) {
			return ActionResult.getError("未找到相关目录");
		}
		List<Article> list = articleService.getListByCatalogId(catalogId);
		if(list==null){
			return ActionResult.getError("未找到相关文章");
		}
		return ActionResult.getSuccess(list);
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public Object addArticle() {
		return "article";
	}
	
	@ResponseBody
	@RequestMapping(value="/addArticle",method=RequestMethod.POST)
	public ActionResult addArticle(HttpServletRequest request) {
		String title=request.getParameter("title");
		String catalogId=request.getParameter("catalogId");
				
		if(StringUtils.isEmpty(title) || StringUtils.isEmpty(catalogId)){
			return ActionResult.getError("输入不能为空！");
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
			return ActionResult.getSuccess("添加成功！");
		}
		return ActionResult.getError("添加失败！");
	}
	
	@ResponseBody
	@RequestMapping(value="/get",method=RequestMethod.POST)
	public ActionResult getById(HttpServletRequest request) {
		String articleId=request.getParameter("articleId");
				
		if(StringUtils.isEmpty(articleId)){
			return ActionResult.getError("输入不能为空！");
		}
		
		Article article=articleService.getById(articleId);
		return ActionResult.getSuccess(article);
	}

	@ResponseBody
	@RequestMapping("/update")
	public ActionResult updateArticle(HttpServletRequest request) {
		String articleId=request.getParameter("articleId");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		if(StringUtils.isEmpty(articleId)){
			return ActionResult.getError("修改失败，文章信息不完整！");
		}
		
		Article article=new Article();
		article.setArticleId(articleId);
		article.setTitle(title);
		article.setContent(content);		
		int result= articleService.update(article);
		if(result==1){
			return ActionResult.getSuccess("修改成功！");
		}
		return ActionResult.getError("修改失败!");
	}
}
