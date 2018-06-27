package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Answers;
/**
 * 
 * @author luerlong
 *
 */


public interface IAnswersService {

	List<Answers> findAll() throws Exception;
	
	Answers findById(long id) throws Exception;
	
	List<Answers> query(String keywords) throws Exception;
	
	String saveOrUpdate(Answers answers) throws Exception;
	
	void deleteById(long id) throws Exception;
	
	void batchDelete(List<Long> ids) throws Exception;
}
