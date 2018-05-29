package org.flowableSpringboot.bootstrap;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
    @Bean
    public CommandLineRunner init(final MyService myService) {

        return new CommandLineRunner() {
        	public void run(String... strings) throws Exception {
            	myService.createDemoUsers();
            }
        };
    }

}