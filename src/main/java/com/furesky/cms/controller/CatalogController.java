package com.furesky.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.furesky.base.ActionResult;
import com.furesky.base.utils.IdGen;
import com.furesky.base.utils.LocalCache;
import com.furesky.cms.model.Catalog;
import com.furesky.cms.service.CatalogService;

@Controller
@RequestMapping(value = "/cms/catalog", method = RequestMethod.POST)
public class CatalogController {

	@Autowired
	private CatalogService catalogService;

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public Object addCatalog() {
		return "catalog";
	}
	
	@ResponseBody
	@RequestMapping(value="/addCatalog",method=RequestMethod.POST)
	public ActionResult addCatalog(HttpServletRequest request) {
		String catalogName=request.getParameter("catalogName");
		String parentId=request.getParameter("parentId");
		String rank=request.getParameter("rank");
		String className=LocalCache.getLocalCache().get("className");
		
		if(StringUtils.isEmpty(catalogName) || StringUtils.isEmpty(rank)){
			return ActionResult.getError("输入不能为空！");
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
			return ActionResult.getSuccess("添加成功！");
		}
		return ActionResult.getError("添加失败！");
	}
}
