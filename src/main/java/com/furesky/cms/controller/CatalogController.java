package com.furesky.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.furesky.base.Result;
import com.furesky.base.utils.IdGen;
import com.furesky.base.utils.LocalCache;
import com.furesky.cms.model.Catalog;
import com.furesky.cms.service.CatalogService;

@Controller
@RequestMapping(value = "/cms/catalog", method = RequestMethod.POST)
public class CatalogController {

	@Autowired
	private CatalogService catalogService;

	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public Object addCatalog() {
		return "catalog";
	}
	
	@ResponseBody
	@RequestMapping(value="/addCatalog",method=RequestMethod.POST)
	public Result<String> addCatalog(HttpServletRequest request) {
		String catalogName=request.getParameter("catalogName");
		String parentId=request.getParameter("parentId");
		String rank=request.getParameter("rank");
		String className=LocalCache.get("className");
		
		if(StringUtils.isEmpty(catalogName) || StringUtils.isEmpty(rank)){
			return Result.error("输入不能为空！");
		}

		String parentRank="";
		if(StringUtils.isNotEmpty(parentId)){
			parentRank=catalogService.getById(parentId).getRank();
		}else {
			parentId="0";
		}
		rank=parentRank+rank;		
		
		Catalog catalog=new Catalog();			
		catalog.setCatalogId(IdGen.uuid());	
		catalog.setCatalogName(catalogName);
		catalog.setRank(rank);
		catalog.setClassName(className);
		catalog.setParentId(parentId);
		int result=catalogService.insert(catalog);
		if(result==1){
			return Result.success();
		}
		return Result.error();
	}
	
	@ResponseBody
	@RequestMapping(value="/updateCatalog",method=RequestMethod.POST)
	public Result<String> updateCatalog(HttpServletRequest request) {
		String newCatalogName=request.getParameter("newCatalogName");
		String oldCatalogId=request.getParameter("oldCatalogId");
		
		if(StringUtils.isEmpty(oldCatalogId)){
			return Result.error("原目录名不能为空！");
		}
		if(StringUtils.isEmpty(newCatalogName)){
			return Result.error("新目录名不能为空！");
		}

		
		Catalog catalog=new Catalog();			
		catalog.setCatalogId(oldCatalogId);	
		catalog.setCatalogName(newCatalogName);
		int result=catalogService.update(catalog);
		if(result==1){
			return Result.success();
		}
		return Result.error();
	}
}
