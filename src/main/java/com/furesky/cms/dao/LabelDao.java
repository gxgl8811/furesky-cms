package com.furesky.cms.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.furesky.cms.model.Label;
@Mapper
public interface LabelDao {
	public List<Label> getList();
	public int insert(Label label);
	public int deleteById(String labelId);	
	
	public Label getByLabelName(String labelName);
}
