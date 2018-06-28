package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Options;
import com.briup.apps.poll.bean.OptionsExample;
import com.briup.apps.poll.bean.Question;
import com.briup.apps.poll.bean.QuestionExample;
import com.briup.apps.poll.bean.extend.QuestionVM;
import com.briup.apps.poll.dao.OptionsMapper;
import com.briup.apps.poll.dao.QuestionMapper;
import com.briup.apps.poll.dao.extend.QuestionVMMapper;
import com.briup.apps.poll.service.IQuestionService;

@Service
public class QuestionServiceImpl implements IQuestionService{
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private QuestionVMMapper questionVMMapper;
	@Autowired
	private OptionsMapper optionsMapper;
	@Override
	//查询所有题库信息
	public List<Question> findAll() throws Exception {
		// TODO Auto-generated method stub
		QuestionExample example=new QuestionExample();
		return questionMapper.selectByExample(example);
	}
	//通过id查询题库信息
	@Override
	public Question findById(long id) throws Exception {
		// TODO Auto-generated method stub
		QuestionExample example=new QuestionExample();
		return questionMapper.selectByPrimaryKey(id);
	}
	//通过关键字查询题库信息
	@Override
	public List<Question> query(String keywords) throws Exception {
		// TODO Auto-generated method stub
		QuestionExample example = new QuestionExample();
		//添加一个条件，name属性中包含keywords关键字
		example.createCriteria().andNameLike("%"+keywords+"%");		
		return questionMapper.selectByExample(example);		
	}
	//更新或保存题库信息
	@Override
	public void saveOrUpdate(Question question) throws Exception {
		// TODO Auto-generated method stub
		if(question.getId()!=null){
			questionMapper.updateByPrimaryKey(question);
		}else{
			questionMapper.insert(question);
		}
		
	}
	//通过id删除题库信息
	@Override
	public void deleteById(long id) throws Exception {
		// TODO Auto-generated method stub
		questionMapper.deleteByPrimaryKey(id);
	}
	//批量删除题库信息
	@Override
	public void batchDelete(Long[] ids) throws Exception {
		// TODO Auto-generated method stub
		for(long id:ids){
			questionMapper.deleteByPrimaryKey(id);
		}
	}
    //查询题库信息并且显示出选项
	@Override
	public List<QuestionVM> findAllQuestionVM() throws Exception {
		// TODO Auto-generated method stub
		return questionVMMapper.selectAll();
	}
	/*
	 * 保存或修改问题，判断是单选还是多选还是简答
	 * @see com.briup.apps.poll.service.IQuestionService#saveOrUpdate(com.briup.apps.poll.bean.extend.QuestionVM)
	 */
	
	//更新及保存，同时保存选项信息
	@Override
	public void saveOrUpdateVM(QuestionVM questionVM) throws Exception {
		// TODO Auto-generated method stub
		//1. 分离questionVM,从中获取到Question Options
 		List<Options> options = questionVM.getOptions();
 		Question question = new Question();
 		question.setId(questionVM.getId());
 		question.setName(questionVM.getName());
 		question.setQuestiontype(questionVM.getQuestionType());
 		//question 问题对象，options 所有问题的选项
 		//2. 判断保存还是修改
 		if(question.getId() == null){
 			//2.1 保存
 			if(question.getQuestiontype().equals("简答题")){
 				//2.1.1 保存简答题，只需要保存题目相关信息
 				questionMapper.insert(question);
 			} else {
 				//2.1.2 保存单选和多选题的时候需要先保存题目信息，再保存选项信息
 				questionMapper.insert(question);
 				//如何获取刚刚插入到问题的ID
 				long questionId = question.getId();
 				for(Options option : options){
 					//为每个option设置question_id
 					option.setQuestionId(questionId);
 					//保存选项
 					optionsMapper.insert(option);
 				}
  			}
  		} else {
  			//2.2 修改
 			//2.2.1 修改题目信息
 			questionMapper.updateByPrimaryKey(question);
 			//2.2.2 修改选项信息（添加新选项，删除旧选项，对原来选项修改）
			//1. 删除该题目原有的选项
 			OptionsExample example = new OptionsExample();
 			example.createCriteria().andQuestionIdEqualTo(question.getId());
 			optionsMapper.deleteByExample(example);
 			//2. 重新添加选项
 			long questionId = question.getId();
 			for(Options option : options){
 				//为每个option设置question_id
 				option.setQuestionId(questionId);
 				//保存选项
 				optionsMapper.insert(option);
 			}
  		}
       //3. 判断是否是简答
 		
 		/*
 		 * 保存
 		 * 1. 保存问题
 		 * 2. 保存选项
 		 */
	}

	
}
	
	


