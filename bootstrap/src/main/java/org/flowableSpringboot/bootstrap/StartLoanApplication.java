package org.flowableSpringboot.bootstrap;


import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class StartLoanApplication {
	
	@Autowired
    private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@RequestMapping(value="/get-loan-application", method= RequestMethod.POST)
    public void startProcessInstance(@RequestBody ApplicationData data) {
		System.out.println("Data====>"+data.getName()+data.getEmail()+data.getPhone());
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("name", data.getName());
		variables.put("email", data.getEmail());
		variables.put("phone", data.getPhone());
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("loanApplication", 
				variables);
		
		System.out.println("Tasks chekcing for loanmanageruser........");
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("loanManager").active().list();
		System.out.println("Tasks chekcing for loanmanageruser........"+tasks);
	    for (Task task : tasks) {
	      System.out.println("loanmanageruser:::::: " + task.getName());
	    }
	    for (int i=0; i<tasks.size(); i++) {
	    	Map<String, Object> processVariables = taskService.getVariables(tasks.get(i).getId());
	    	System.out.println((i+1) + ") " + tasks.get(i).getName() + ",  "+ processVariables.get("name") +
	    	",  "+ processVariables.get("email") + ",  "+ processVariables.get("phone")+", "+tasks.get(i).getId());
		}
		System.out.println("sample....."+processInstance.getProcessDefinitionId());
				
    }
}
