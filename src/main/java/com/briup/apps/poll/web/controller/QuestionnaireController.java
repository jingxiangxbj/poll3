package com.briup.apps.poll.web.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.briup.apps.poll.bean.Questionnaire;
import com.briup.apps.poll.bean.extend.QuestionnaireVM;
import com.briup.apps.poll.service.IQuestionnaireService;
import com.briup.apps.poll.util.MsgResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="问卷相关接口")
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {
	@Autowired
	private IQuestionnaireService questionnaireService;
	
	@GetMapping("findAllQuestionnaireVM")
	public MsgResponse findAllQuestionnaireVM(){
		try {
			List<QuestionnaireVM> list=questionnaireService.findAllQuestionnaireVM();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//返回失败
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="查询所有")
	@GetMapping("findAll")
	public MsgResponse findAll(){
		try {
			List<Questionnaire> list = questionnaireService.findAll();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="通过id查找")
	@GetMapping("findById")
	public MsgResponse findById( long id){
		try {	
			return MsgResponse.success("success",questionnaireService.findById(id));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="通过关键字查询")
	@GetMapping("query")
	public MsgResponse query(String keywords){
		try {
			List<Questionnaire> list = questionnaireService.query(keywords);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="添加和修改", notes="保存不可输入id,修改id须和数据库一致")
	@PostMapping("saveOrUpdate")
	public MsgResponse saveOrUpdate(Questionnaire questionnaire){
		try {
			questionnaireService.saveOrUpdate(questionnaire);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="通过id删除")
	@GetMapping("deleteById")
	public MsgResponse deleteById(@RequestParam long id){
		try {
			questionnaireService.deleteById(id);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="批量删除",notes="输入id并用逗号隔开，如：1,2,3")
	@GetMapping("batchDelete") 
	public MsgResponse batchDelete( Long[] ids){
		try {
			questionnaireService.batchDelete(ids);;
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			return MsgResponse.error(e.getMessage());
		}
	}
}
