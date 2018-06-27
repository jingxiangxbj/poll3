package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Survey;
import com.briup.apps.poll.bean.extend.SurveyVM;
import com.briup.apps.poll.service.ISurveyService;
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
@RequestMapping("/survey")
public class SurveyController {
	
	@Autowired
	private ISurveyService surveySurvey;
	
	@GetMapping("findAllSurveyVM")
	@ApiOperation(value="查找所有课调")
	public MsgResponse findAllSurveyVM(){
		try {
			List<SurveyVM> list = surveySurvey.selectAll();
			//返回成功
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//返回失败
			return MsgResponse.error(e.getMessage());
		}
	}
	
	
	@GetMapping("findAllSurvey")
	@ApiOperation(value="查找所有课调")
	public MsgResponse findAllSurvey(){
		try {
			List<Survey> list = surveySurvey.findAll();
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
	@ApiOperation(value="根据id查找课调")

	public MsgResponse findById(@RequestParam long id) {
		// TODO Auto-generated method stub
		Survey survey = new Survey();
		 try {
			 survey = surveySurvey.findById(id);
			return MsgResponse.success("success", survey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MsgResponse.error("查找失败"+e.getMessage());
		}
		
	}
	
	
	@PostMapping("saveOrUpdate")
	@ApiOperation(value="添加或更新课调",notes="添加不需要输入id")

	public String saveOrUpdate(Survey survey){
		// TODO Auto-generated method stub
		String message =null;
		try {
			 message = surveySurvey.saveOrUpdate(survey);
			return message;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "请将数据补充完整"+e.getMessage();
		}
	}
	
	@GetMapping("query")
	@ApiOperation(value="根据surveydate查找课调")

	public List<Survey> query(String keywords)  {
		
		try {
			return surveySurvey.query(keywords);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping("deleteById")
	@ApiOperation(value="根据id删除课调")

	public String deleteById(@RequestParam long id){
		// TODO Auto-generated method stub
		try {
			surveySurvey.deleteById(id);
			return "删除成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "删除失败"+e.getMessage();
		}
		
	}
	
	@GetMapping("batchDelete")
	@ApiOperation(value="批量删除课调")

	public String batchDelete(@RequestParam List<Long> ids) {
		// TODO Auto-generated method stub
		
		try {
			surveySurvey.batchDelete(ids);
			return "删除成功";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "删除成功";

		}
		
	}

	
}
