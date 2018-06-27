package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Survey;
import com.briup.apps.poll.bean.SurveyExample;
import com.briup.apps.poll.bean.extend.SurveyVM;
import com.briup.apps.poll.dao.SurveyMapper;
import com.briup.apps.poll.dao.extend.SurveyVMMapper;
import com.briup.apps.poll.service.ISurveyService;
/**
 * 
 * @author luerlong
 *
 */
@Service
public class SurveyServiceImpl implements ISurveyService {

	
	@Autowired
	private SurveyMapper surveyMapper;
	@Autowired
	private SurveyVMMapper surveyVMMapper;
	
	@Override
	public List<Survey> findAll() throws Exception {
		SurveyExample example = new SurveyExample();
			
		return surveyMapper.selectByExample(example);
	}

	@Override
	public Survey findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return surveyMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Survey> query(String keywords) throws Exception {
		SurveyExample example = new SurveyExample();
		//添加一个条件，name属性中包含keywords关键字
		example.createCriteria().andSurveydateLike(keywords);
		
		
		return surveyMapper.selectByExample(example);
	}

	@Override
	public String saveOrUpdate(Survey survey) throws Exception {
		// TODO Auto-generated method stub

		if(survey.getId()!=null){
			//更新
			surveyMapper.updateByPrimaryKey(survey);
			return "更新成功";
		}else{
			//插入
			surveyMapper.insert(survey);
			return "添加成功";
		}
	}

	@Override
	public void deleteById(long id) throws Exception {
		// TODO Auto-generated method stub

		surveyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(List<Long> ids) throws Exception {
		// TODO Auto-generated method stub

		for(long id:ids){
			surveyMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public List<SurveyVM> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return surveyVMMapper.selectAll();
	}

}
