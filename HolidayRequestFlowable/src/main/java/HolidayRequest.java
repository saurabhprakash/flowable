//package org.flowable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

public class HolidayRequest {

    public static void main(String[] args) {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        ProcessEngine processEngine = cfg.buildProcessEngine();
        
        // Deploy a process definition to the Flowable engine,  
        // Using the RepositoryService, a new Deployment is created by passing the location of the XML file 
        // and calling the deploy() method
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
          .addClasspathResource("holiday-request.bpmn20.xml")
          .deploy();
        
        // Verify that the process definition is known to the engine
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
        		  .deploymentId(deployment.getId())
        		  .singleResult();
        System.out.println("Found process definition : " + processDefinition.getName());
        		
        
        // Input some data on the command line
		Scanner scanner= new Scanner(System.in);

		System.out.println("Who are you?");
		String employee = scanner.nextLine();

		System.out.println("How many holidays do you want to request?");
		Integer nrOfHolidays = Integer.valueOf(scanner.nextLine());

		System.out.println("Why do you need them?");
		String description = scanner.nextLine();
		
		
		
		RuntimeService runtimeService = processEngine.getRuntimeService();

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employee", employee);
		variables.put("nrOfHolidays", nrOfHolidays);
		variables.put("description", description);
		
		// Start a process instance through the RuntimeService
		// The process instance is started using a key. This key matches the id attribute 
		// that is set in the BPMN 2.0 XML file, in this case holidayRequest
		ProcessInstance processInstance =
		  runtimeService.startProcessInstanceByKey("holidayRequest", variables);
		
		
		// Get the actual task list, Configure the query to only return the tasks for the managers group
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
		System.out.println("You have " + tasks.size() + " tasks:");
		for (int i=0; i<tasks.size(); i++) {
		  System.out.println((i+1) + ") " + tasks.get(i).getName());
		}
		
		
		// Get the specific process instance variables and show on the screen the actual request
		System.out.println("Which task would you like to complete?");
		int taskIndex = Integer.valueOf(scanner.nextLine());
		Task task = tasks.get(taskIndex - 1);
		Map<String, Object> processVariables = taskService.getVariables(task.getId());
		System.out.println(processVariables.get("employee") + " wants " +
		    processVariables.get("nrOfHolidays") + " of holidays. Do you approve this?");
		
		// Manager can now complete the task
		boolean approved = scanner.nextLine().toLowerCase().equals("y");
		variables = new HashMap<String, Object>();
		variables.put("approved", approved);
		taskService.complete(task.getId(), variables);
		
		
		// Historical data for all the process instances
		HistoryService historyService = processEngine.getHistoryService();
		List<HistoricActivityInstance> activities =
		  historyService.createHistoricActivityInstanceQuery()
		   .processInstanceId(processInstance.getId())
		   .finished()
		   .orderByHistoricActivityInstanceEndTime().asc()
		   .list();

		for (HistoricActivityInstance activity : activities) {
		  System.out.println(activity.getActivityId() + " took "
		    + activity.getDurationInMillis() + " milliseconds");
		}

    }

}