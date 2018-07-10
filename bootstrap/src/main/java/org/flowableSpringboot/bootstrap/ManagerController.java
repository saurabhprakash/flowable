package org.flowableSpringboot.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;


@RestController
public class ManagerController {
	
	@Autowired
	private TaskService taskService;

    @RequestMapping(value="/manage-application/approve", method= RequestMethod.POST)
    public String manageApplication(@RequestBody HashMap<String, String> requestParams) {
        
    	System.out.println("taskId="+requestParams.get("taskId"));
    	String taskId = requestParams.get("taskId");
    	Map<String, Object> processVariables = taskService.getVariables(taskId);
		System.out.println("processVariables===>"+processVariables);
		processVariables.put("approved", true);
		System.out.println("processVariables===>"+processVariables);
		//taskService.complete(taskId);
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("approved", true);
		taskService.complete(taskId, variables);
    	
    	return "Done";
    }
    
    /* Get List of loan application id's present as task list
     * For loanManager group, Returns the list of taskIds 
     */
    @RequestMapping(value="/manage-application", method= RequestMethod.GET)
    public List<String> getManagerApplications() {
    	List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("loanManager").active().list();
    	List<String> taskIdList = new ArrayList<>();
    	System.out.println("You have " + tasks.size() + " tasks");
    	for (Task task: tasks) {
    		taskIdList.add(task.getId());
  		}    	
		return taskIdList;
    }
}
