package org.flowableSpringboot.bootstrap;


import java.util.HashMap;
import java.util.Map;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.form.api.FormInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RenderSampleForm {
	
	@Autowired
    private RuntimeService runtimeService;
	
	@RequestMapping(value="/form")
	@ResponseBody
	public String renderForm() {
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("isValidLeave", false);
		variables.put("approved", false);
		
		// Start a process instance
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("firstApplication", variables);
		
		FormInfo formModel = runtimeService
				.getStartFormModel(processInstance.getProcessDefinitionId(), processInstance.getId());
		System.out.println("Ouput......." + processInstance);
		System.out.println("Ouput2......." + formModel.toString() + "..." + formModel.getId());
		System.out.println("Ouput3......." + formModel.getName());
		System.out.println("Ouput4......." + formModel.getClass());
		System.out.println("Ouput5......." + formModel.getFormModel());
		
		processInstance.
		
		System.out.println("1=" + processInstance.getProcessVariables());
		System.out.println("2=" + processInstance.isSuspended());
		
        return "form.html";
    } 
}
