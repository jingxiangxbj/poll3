package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Question;
import com.briup.apps.poll.bean.QuestionExample;
import com.briup.apps.poll.bean.extend.QuestionVM;
import com.briup.apps.poll.dao.QuestionMapper;
import com.briup.apps.poll.dao.extend.QuestionVMMapper;
import com.briup.apps.poll.service.IQuestionService;

@Service
public class QuestionServiceImpl implements IQuestionService{
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private QuestionVMMapper questionVMMapper;

	@Override
	public List<Question> findAll() throws Exception {
		// TODO Auto-generated method stub
		QuestionExample example=new QuestionExample();
		return questionMapper.selectByExample(example);
	}
	
	@Override
	public Question findById(long id) throws Exception {
		// TODO Auto-generated method stub
		QuestionExample example=new QuestionExample();
		return questionMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Question> query(String keywords) throws Exception {
		// TODO Auto-generated method stub
		QuestionExample example = new QuestionExample();
		//添加一个条件，name属性中包含keywords关键字
		example.createCriteria().andNameLike("%"+keywords+"%");		
		return questionMapper.selectByExample(example);		
	}
	
	@Override
	public void saveOrUpdate(Question question) throws Exception {
		// TODO Auto-generated method stub
		if(question.getId()!=null){
			questionMapper.updateByPrimaryKey(question);
		}else{
			questionMapper.insert(question);
		}
		
	}
	
	@Override
	public void deleteById(long id) throws Exception {
		// TODO Auto-generated method stub
		questionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(Long[] ids) throws Exception {
		// TODO Auto-generated method stub
		for(long id:ids){
			questionMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public List<QuestionVM> findAllQuestionVM() throws Exception {
		// TODO Auto-generated method stub
		return questionVMMapper.selectAll();
	}
	
	

}
