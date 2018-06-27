  package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Answers;
import com.briup.apps.poll.service.IAnswersService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 
 * 
 * @author luerlong
 *
 */
@Api(description="答题卡相关接口")
@RestController
@RequestMapping("/answers")
public class AnswersController {

	@Autowired
	private IAnswersService answersService;
	
	
	@GetMapping("findAllAnswers")
	@ApiOperation(value="查询所有课程")
	public MsgResponse findAllCourse(){
		try {
			List<Answers> list = answersService.findAll();
			//返回成功
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//返回失败
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@GetMapping("findById")
	@ApiOperation(value="根据id查找课程")
	public MsgResponse findById(@RequestParam long id) {
		// TODO Auto-generated method stub
		Answers answers = new Answers();
		 try {
			answers = answersService.findById(id);
			return MsgResponse.success("success", answers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MsgResponse.error("查找失败"+e.getMessage());
		}
		
	}
	
	
	@PostMapping("saveOrUpdate")
	@ApiOperation(value="保存或更新",notes="保存课程信息的时候无需输入id")
	public String saveOrUpdate(Answers answers){
		// TODO Auto-generated method stub
		String message =null;
		try {
			 message = answersService.saveOrUpdate(answers);
			return message;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "请将数据补充完整"+e.getMessage();
		}
	}
	
	@GetMapping("deleteById")	
	@ApiOperation(value="根据id删除课程")

	public String deleteById(@RequestParam long id){
		// TODO Auto-generated method stub
		try {
			answersService.deleteById(id);
			return "删除成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "删除失败"+e.getMessage();
		}
		
	}
	
	@GetMapping("batchDelete")
	@ApiOperation(value="批量删除课程")

	public String batchDelete(@RequestParam List<Long> ids) {
		// TODO Auto-generated method stub
		
		try {
			answersService.batchDelete(ids);
			return "删除成功";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "删除成功";

		}
		
	}
}
