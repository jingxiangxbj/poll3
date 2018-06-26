package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Question;
import com.briup.apps.poll.service.IQuestionService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="题库相关接口")
@RequestMapping("/question")
@RestController
public class QuestionController {
	
	@Autowired
	private IQuestionService questionService;
	@ApiOperation(value="查询所有题库信息")
	@GetMapping("findAllQuestion")
	public MsgResponse findAllQuestion(){
		try {
			List<Question> list=questionService.findAll();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//返回失败
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="通过id查询题库信息")
	@GetMapping("findById")
	public MsgResponse findById(Long id){
		try {			
			return MsgResponse.success("success", questionService.findById(id));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//返回失败
			return MsgResponse.error(e.getMessage());
		}
	
	}
	@ApiOperation(value="通过id删除题库信息" )
	@GetMapping("deleteById")
	public MsgResponse deleteById(@RequestParam long id){
		try {	
			questionService.deleteById(id);
			return MsgResponse.success("success",null);
		} catch (Exception e) {
			// TODO: handle exceptionS
			e.printStackTrace();
			//返回失败
			return MsgResponse.error(e.getMessage());
		}
	
	}
	@ApiOperation(value="通过关键词查询题库信息" )
	@GetMapping("query")
	public MsgResponse query(String keywords){
		try {
			List<Question> list=questionService.query(keywords);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//返回失败
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="更新保存题库信息",notes="如果id为空，则保存信息，否则是更新信息 " )
	@PostMapping("saveOrUpdate")
	public MsgResponse saveOrUpdate(Question question){
		try {
			questionService.saveOrUpdate(question);
			return MsgResponse.success("success",null );
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//返回失败
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="批量删除题库内的信息",notes="批量删除时输入的id号之间用,隔开" )
	@GetMapping("batchDelete")
	public MsgResponse batchDelete(Long[] ids){
		try {
			questionService.batchDelete(ids);
			return MsgResponse.success("success",null );
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//返回失败
			return MsgResponse.error(e.getMessage());
		}
	}


}
