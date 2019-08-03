package com.furesky.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furesky.cms.dao.LabelDao;
import com.furesky.cms.model.Label;
import com.furesky.cms.service.LabelService;  
  
 
  
@Service
public class LabelServiceImpl implements LabelService {  
    @Autowired  
    private LabelDao labelDao;
    
    @Override
	public List<Label> getList() {
		return labelDao.getList();
	}

	@Override
	public int insert(Label label) {
		return labelDao.insert(label);
	}

	@Override
	public int deleteById(String labelId) {
		return labelDao.deleteById(labelId);
	}

	@Override
	public Label getByLabelName(String labelName) {
		return labelDao.getByLabelName(labelName);
	}    
}  
