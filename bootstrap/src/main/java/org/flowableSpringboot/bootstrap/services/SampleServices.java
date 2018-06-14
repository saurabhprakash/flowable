package org.flowableSpringboot.bootstrap.services;

import java.io.IOException;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;


/*public class SampleServices {
	
	public Object activity1(DelegateExecution exec) throws IOException, InterruptedException, Exception {
		return exec;
	}
	
	public Object activity2(DelegateExecution exec) throws IOException, InterruptedException, Exception {
		return exec;
	}
	
	public Object activity3(DelegateExecution exec) throws IOException, InterruptedException, Exception {
		return exec;
	}
	
	public Object activity4(DelegateExecution exec) throws IOException, InterruptedException, Exception {
		return exec;
	}
    
}*/

public class SampleServices implements JavaDelegate {
	
	public void execute(DelegateExecution delegateExecution) {
 
		System.out.println("...................Executed process with name "+
							delegateExecution.getEventName()+
							" with process definition Id "+
							delegateExecution.getProcessDefinitionId()+
							" with process instance Id "+delegateExecution.getProcessInstanceId()+
							" and current task name is "+
							delegateExecution.getVariables());

	}
}
