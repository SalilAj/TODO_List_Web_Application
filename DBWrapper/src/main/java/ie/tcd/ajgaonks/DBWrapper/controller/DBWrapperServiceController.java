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

	private static final Logger LOGGER = LoggerFactory.getLogger(DBWrapperServiceController.class);

	@Autowired
	QueryService queryService;

	@RequestMapping(value = "/addMemberData", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public boolean addMemberData(@RequestBody MemberInfoObject memInfoObj) {

		LOGGER.info("Request {}", memInfoObj);
		boolean response = false;

		try {
			if (memInfoObj == null) {
				throw new Exception("Invalid MemberInfoObject value");
			} else {
				queryService.addMemberData(memInfoObj);
				response = true;
			}
		} catch (Exception e) {
			LOGGER.error("Exception message:{}", e.getMessage());
		}
		return response;
	};

	@RequestMapping(value = "/getMemberData", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public MemberInfoObject getMemberData(@RequestParam("email") String email) {

		LOGGER.info("Request {}", email);
		MemberInfoObject memberInfo = null;

		try {
			if (email == null || email.trim().isEmpty()) {
				throw new Exception("Invalid Email value");
			} else {
				memberInfo = queryService.getMemberData(email);
			}
		} catch (Exception e) {
			LOGGER.error("Exception message:{}", e.getMessage());
		}

		return memberInfo;

	};

	@RequestMapping(value = "/getMemberTasks", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public TasksObject getMemberTasks(@RequestParam("memberId") String memberId) {
		LOGGER.info("Request {}", memberId);
		TasksObject memberTasks = null;

		try {
			if (memberId == null || memberId.trim().isEmpty()) {
				throw new Exception("Invalid MemberId value");
			} else {
				memberTasks = queryService.getMemberTasks(memberId);
			}
		} catch (Exception e) {
			LOGGER.error("Exception message:{}", e.getMessage());
		}
		return memberTasks;

	}

	@RequestMapping(value = "/addMemberTask", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public boolean addMemberTask(@RequestParam("memberId") String memberId, @RequestBody TaskDoc taskObj) {
		LOGGER.info("Request {}", memberId, taskObj);

		boolean response = false;
		try {
			if (memberId == null || taskObj == null || memberId.trim().isEmpty()) {
				throw new Exception("Invalid memberId/taskObj value");
			} else {
				queryService.addMemberTasks(memberId, taskObj);
				response = true;
			}
		} catch (Exception e) {
			LOGGER.error("Exception message:{}", e.getMessage());
		}

		return response;
	};

	@RequestMapping(value = "/deleteMemberTask", method = RequestMethod.POST, produces = { "application/json" }) // TaskId
	@ResponseBody
	public boolean deleteMemberTask(@RequestParam("memberId") String memberId, @RequestParam("taskId") String taskId) {
		LOGGER.info("Request {}", memberId, taskId);

		boolean response = false;
		try {
			if (memberId == null || taskId == null || taskId.trim().isEmpty() || memberId.trim().isEmpty()) {
				throw new Exception("Invalid MemberId/TaskId value");
			} else {
				queryService.deleteTask(memberId, taskId);
				response = true;
			}
		} catch (Exception e) {
			LOGGER.error("Exception message:{}", e.getMessage());
		}
		return response;
	};

}
