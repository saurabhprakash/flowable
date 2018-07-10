package org.flowableSpringboot.bootstrap.services;

import java.io.IOException;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import org.flowableSpringboot.bootstrap.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.flowableSpringboot.bootstrap.ApplicationDao;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;


//public class GetLoanServices implements JavaDelegate {
//	
//	@Autowired
//	private ApplicationDataRepository applicationDataRepository;
//		
//	public void execute(DelegateExecution delegateExecution) {
//		System.out.println("...................This is test 3="+applicationDataRepository);
//		System.out.println("...................Executed process with name "+
//							delegateExecution.getEventName()+
//							" with process definition Id "+
//							delegateExecution.getProcessDefinitionId()+
//							" with process instance Id "+delegateExecution.getProcessInstanceId()+
//							" and current task name is "+
//							delegateExecution.getVariables()+"..."+delegateExecution.getVariable("name"));
//		
//		System.out.println("1="+ delegateExecution.getVariable("name").toString());
//		System.out.println("2="+ delegateExecution.getVariable("email").toString());
//		System.out.println("3="+ delegateExecution.getVariable("phone").toString());
//		ApplicationData application = new ApplicationData(delegateExecution.getVariable("name").toString(),
//				delegateExecution.getVariable("email").toString(), delegateExecution.getVariable("phone").toString());
//		System.out.println("...................This is test 1="+application);
//		System.out.println("...................This is test 2="+applicationDataRepository);
//		applicationDataRepository.save(application);
//		System.out.println("...................This is test ");
//	}
//}


@Component("GetLoanServices")
public class GetLoanServices {
	@Autowired
	private ApplicationDataRepository applicationDataRepository;

	public Object getLoanApplication(DelegateExecution delegateExecution) throws IOException, 
		InterruptedException, Exception {
		try {
			System.out.println("...................Executed process with name "+
								delegateExecution.getEventName()+
								" with process definition Id "+
								delegateExecution.getProcessDefinitionId()+
								" with process instance Id "+delegateExecution.getProcessInstanceId()+
								" and current task name is "+
								delegateExecution.getVariables()+"..."+delegateExecution.getVariable("name"));
			
			ApplicationData application = new ApplicationData(delegateExecution.getVariable("name").toString(),
					delegateExecution.getVariable("email").toString(), delegateExecution.getVariable("phone").toString());
			applicationDataRepository.save(application);
			return delegateExecution;
		} catch (Exception e) {
			throw new InterruptedException();
		}
	}
	
		
	public Object disburseLoanApplication(DelegateExecution delegateExecution) throws IOException, 
	InterruptedException, Exception {
		try {
			System.out.println("...................Executed process with name "+
								delegateExecution.getEventName()+
								" with process definition Id "+
								delegateExecution.getProcessDefinitionId()+
								" with process instance Id "+delegateExecution.getProcessInstanceId()+
								" and current task name is "+
								delegateExecution.getVariables()+"..."+delegateExecution.getVariable("name"));		
			return delegateExecution;
		} catch (Exception e) {
			throw new InterruptedException();
		}
	}
}

