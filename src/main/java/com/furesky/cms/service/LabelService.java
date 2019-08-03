package com.furesky.cms.service;

import java.util.List;

import com.furesky.cms.model.Label;

public interface LabelService {
	public List<Label> getList();
	public int insert(Label label);
	public int deleteById(String labelId);	
	
	public Label getByLabelName(String labelName);
}
