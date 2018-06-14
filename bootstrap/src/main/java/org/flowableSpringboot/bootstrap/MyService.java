package org.flowableSpringboot.bootstrap;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public void startProcess(String assignee) {
    	Person person = personRepository.findByUsername(assignee);

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("person", person);
        runtimeService.startProcessInstanceByKey("oneTaskProcess", variables);
        // System.out.println("We have now" + runtimeService.createNativeProcessInstanceQuery().count() + " process instances !");
        System.out.println("Created process - 1");
        runtimeService.startProcessInstanceByKey("oneTaskProcess");
        System.out.println("Created process - 2");
        runtimeService.startProcessInstanceByKey("testProcess");
        
        
        
        List<ProcessInstance> instanceList = runtimeService
    	      .createProcessInstanceQuery()
    	      .processDefinitionKey("testProcess")
    	      .list();
        System.out.println("Length of list=" + instanceList.size());
    	for (ProcessInstance queryProcessInstance : instanceList) {
    		 System.out.println("########### id=" + queryProcessInstance.getId());
    	}
    }

    @Transactional
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }
    
    public void createDemoUsers() {
        if (personRepository.findAll().size() == 0) {
            personRepository.save(new Person("jbarrez", "Joram", "Barrez", new Date()));
            personRepository.save(new Person("trademakers", "Tijs", "Rademakers", new Date()));
        }
    }


}
