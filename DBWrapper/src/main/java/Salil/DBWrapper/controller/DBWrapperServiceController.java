package Salil.DBWrapper.controller;

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


import Salil.DBWrapper.beans.MemberInfoObject;
import Salil.DBWrapper.beans.MemberValObject;
import Salil.DBWrapper.beans.AuthMemberObj;
import Salil.DBWrapper.beans.ListObj;
import Salil.DBWrapper.beans.TaskObj;
import Salil.DBWrapper.beans.Tasks;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/db-wrapper-service")
public class DBWrapperServiceController {
	
	@RequestMapping(value = "/addUserData", method = RequestMethod.POST, produces = { "application/text" })
	@ResponseBody
	public boolean addMemberData(@RequestBody MemberInfoObject memInfoObj ) {return true;};
	
	@RequestMapping(value = "/authenticateUser", method = RequestMethod.POST, produces = { "application/text" })
	@ResponseBody
	public AuthMemberObj validateMember(@RequestBody MemberValObject memValObj) {return null;};
	
	@RequestMapping(value = "/getUsersTasks", method = RequestMethod.GET, produces = { "application/text" })
	@ResponseBody
	public Tasks getMemberTasks(@RequestParam("memberId") String memberId) {return null;};
	
	@RequestMapping(value = "/addUsersList", method = RequestMethod.POST, produces = { "application/text" })
	@ResponseBody
	public boolean addList(@RequestBody ListObj listObj) {return true;};
	
	@RequestMapping(value = "/addUsersTaskToList", method = RequestMethod.POST, produces = { "application/text" })
	@ResponseBody
	public boolean addTaskToList(@RequestBody TaskObj taskObj) {return true;};

}
