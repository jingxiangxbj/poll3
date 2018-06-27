package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Survey;
import com.briup.apps.poll.bean.extend.SurveyVM;
/**
 * 
 * @author luerlong
 *
 */
public interface ISurveyService {

	List<Survey> findAll() throws Exception;
	
	Survey findById(long id) throws Exception;
	
	List<Survey> query(String keywords) throws Exception;
	
	String saveOrUpdate(Survey survey) throws Exception;
	
	void deleteById(long id) throws Exception;
	
	void batchDelete(List<Long> ids) throws Exception;
	
	List<SurveyVM> selectAll() throws Exception;

}
