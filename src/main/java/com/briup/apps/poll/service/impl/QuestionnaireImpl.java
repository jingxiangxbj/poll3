package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Questionnaire;
import com.briup.apps.poll.bean.QuestionnaireExample;
import com.briup.apps.poll.bean.extend.QuestionnaireVM;
import com.briup.apps.poll.dao.QuestionnaireMapper;
import com.briup.apps.poll.dao.extend.QuestionnaireVMMapper;
import com.briup.apps.poll.service.IQuestionnaireService;
@Service
public class QuestionnaireImpl implements IQuestionnaireService{
	@Autowired
	private QuestionnaireMapper questionnaireMapper;

	@Override
	public List<Questionnaire> findAll() throws Exception {
		// TODO Auto-generated method stub
		QuestionnaireExample example = new QuestionnaireExample();
		return questionnaireMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public Questionnaire findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return questionnaireMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Questionnaire> query(String keywords) throws Exception {
		// TODO Auto-generated method stub
		QuestionnaireExample example = new QuestionnaireExample();
		example.createCriteria().andNameLike(keywords);
		return questionnaireMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void saveOrUpdate(Questionnaire questionnaire) throws Exception {
		// TODO Auto-generated method stub
		if (questionnaire.getId() != null) {
			questionnaireMapper.updateByPrimaryKey(questionnaire);			
		}else{
			questionnaireMapper.insert(questionnaire);
		}				
	}

	@Override
	public void deleteById(long id) throws Exception {
		// TODO Auto-generated method stub
		questionnaireMapper.deleteByPrimaryKey(id);	
	}

	@Override
	public void batchDelete(Long[] ids) throws Exception {
		// TODO Auto-generated method stub
	for (long id : ids) {
		questionnaireMapper.deleteByPrimaryKey(id);
	}			 
	}
	@Autowired
	private QuestionnaireVMMapper questionnaireVMMapper;
	@Override
	public List<QuestionnaireVM> findAllQuestionnaireVM() throws Exception {
		// TODO Auto-generated method stub
		return questionnaireVMMapper.selectAll();
	}
}
