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
import com.furesky.base.treedata.TreeUtils;
import com.furesky.base.utils.LocalCache;
import com.furesky.cms.model.Catalog;
import com.furesky.cms.service.CatalogService; 

@Controller
public class CommonController {
	
	@Autowired
    private CatalogService catalogService;
	
	@RequestMapping("/")
	public String index() {
	    return "/index";
	}
	
	@ResponseBody
	@RequestMapping(value="/cms/menus",method=RequestMethod.POST)
    public ActionResult getMemus(HttpServletRequest request){	
		String className=request.getParameter("className");
    	if(StringUtils.isEmpty(className)){
    		className=LocalCache.get("className");
    	}else {
    		LocalCache.put("className",className);
		}
    	List<Catalog> catalogs = catalogService.getListByClassName(className);
    	if(catalogs==null||catalogs.size()<1){
    		return ActionResult.getError("未找到相关内容");
    	}
    	catalogs=TreeUtils.getTreeData(catalogs);
        return ActionResult.getSuccess(catalogs);  
    }  
}
