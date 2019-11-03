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
import com.furesky.cms.model.Label;
import com.furesky.cms.service.LabelService;

@Controller
@RequestMapping("/cms/label")
public class LabelController {

	@Autowired
	private LabelService labelService;

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public Object addLabel() {
		return "label";
	}
	
	@ResponseBody
	@RequestMapping(value="/addLabel",method=RequestMethod.POST)
	public Result<String> addLabel(HttpServletRequest request) {
		String labelName=request.getParameter("labelName");
		if(StringUtils.isEmpty(labelName)){
			return Result.error("请输入标签名！");
		}		
		
		Label label=labelService.getByLabelName(labelName);
		if(label!=null){
			return Result.error("标签已经存在！");
		}
		
		label=new Label();		
		label.setLabelId(IdGen.uuid());
		label.setLabelName(labelName);
		int result=labelService.insert(label);						
		if(result==1){
			return Result.success();
		}
		return Result.error();		
	}
}
