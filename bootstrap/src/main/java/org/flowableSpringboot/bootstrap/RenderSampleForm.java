package org.flowableSpringboot.bootstrap;


import java.util.HashMap;
import java.util.Map;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.form.api.FormInfo;
import org.flowableSpringboot.bootstrap.MyRestController.StartProcessRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RenderSampleForm {
	
	@Autowired
    private RuntimeService runtimeService;
	
	/*
	 * curl -H "Content-Type: application/json" -d '{"username" : "jbarrez", "employeeid": 123, "enddate":"2018-08-08", "startdate":"2018-08-22"}' http://localhost:8090/get-start-data
	 * */
	@RequestMapping(value="/get-start-data", method= RequestMethod.POST)
    public void startProcessInstance(@RequestBody Employee employee) {
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("isValidLeave", false);
		variables.put("approved", false);
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("firstApplication");
		
    	Map<String, Object> formVariables = new HashMap<String, Object>();
    	formVariables.put("username", employee.username);
    	formVariables.put("employeeid", employee.employeeid);
    	formVariables.put("enddate", employee.enddate);
    	formVariables.put("startdate", employee.startdate);
		
		runtimeService.startProcessInstanceWithForm(processInstance.getProcessDefinitionId(), null,
				formVariables, processInstance.getProcessDefinitionName());
		
		System.out.println("sample....."+processInstance.getProcessDefinitionId());
				
    }
	
	@RequestMapping(value="/form", method= RequestMethod.POST)
	public String renderForm(@RequestBody Employee myPostVariable) {
		System.out.println("3=" +  myPostVariable.username);
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("isValidLeave", false);
		variables.put("approved", false);
		
		// Start a process instance
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("firstApplication", variables);
		
		FormInfo formModel = runtimeService
				.getStartFormModel(processInstance.getProcessDefinitionId(), processInstance.getId());
		System.out.println("Ouput......." + processInstance);
		System.out.println("Ouput2......." + formModel.toString() + "..." + formModel.getId() );
		System.out.println("Ouput3......." + formModel.getName());
		System.out.println("Ouput4......." + formModel.getClass());
		System.out.println("Ouput5......." + formModel.getFormModel());
		
		System.out.println("1=" + processInstance.getProcessVariables());
		System.out.println("2=" + processInstance.isSuspended());
		
		System.out.println("2a=" + myPostVariable.getUsername());
		
        return "form.html";
    } 
	
	
	static class Employee {
        private String username;
        private int employeeid;
        private String enddate;
        private String startdate;

        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public int getEmployeeid() {
            return employeeid;
        }
        public void setEmployeeid(int id) {
            this.employeeid = id;
        }
        public String getEnddate() {
            return enddate;
        }
        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }
        public String getStartdate() {
            return startdate;
        }
        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }
    }
}
