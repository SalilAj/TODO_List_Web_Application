package ie.tcd.ajgaonks.DBWrapper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import ie.tcd.ajgaonks.DBWrapper.beans.MemberInfoObject;
import ie.tcd.ajgaonks.DBWrapper.beans.TaskDoc;
import ie.tcd.ajgaonks.DBWrapper.beans.TasksObject;
import ie.tcd.ajgaonks.DBWrapper.service.QueryService;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/db-wrapper-service")
public class DBWrapperServiceController {
	
	@Autowired
	QueryService queryService;
	
	@RequestMapping(value = "/addMemberData", method = RequestMethod.POST, produces = { "application/text" })
	@ResponseBody
	public boolean addMemberData(@RequestBody MemberInfoObject memInfoObj ) {
		queryService.addMemberData(memInfoObj);
		return true;
	};
	
	@RequestMapping(value = "/getMemberData", method = RequestMethod.GET, produces = { "application/text" })
	@ResponseBody
	public MemberInfoObject getMemberData(@RequestParam("email") String email) {
		
		MemberInfoObject memberInfo = queryService.getMemberData(email);
		return memberInfo;
		
	};
	
	
	@RequestMapping(value = "/getMemberTasks", method = RequestMethod.GET, produces = { "application/text" })
	@ResponseBody
	public TasksObject getMemberTasks(@RequestParam("memberId") String memberId) {
		
		TasksObject memberTasks = queryService.getMemberTasks(memberId);
		return memberTasks;
		
	}


	@RequestMapping(value = "/addMemberTask", method = RequestMethod.POST, produces = { "application/text" })
	@ResponseBody
	public boolean addMemberTask(@RequestParam("memberId") String memberId, @RequestBody TaskDoc taskObj) {
		queryService.addMemberTasks(memberId, taskObj);
		return true;
	};
	
	
	@RequestMapping(value = "/deleteMemberTask", method = RequestMethod.POST, produces = { "application/text" }) // MemberId TaskId
	@ResponseBody
	public boolean deleteMemberTask(@RequestParam("memberId") String memberId, @RequestParam("memberId") String taskId) {
		queryService.deleteTask(memberId, taskId);
		return true;
		};
	


}
