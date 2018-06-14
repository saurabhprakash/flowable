package org.flowableSpringboot.bootstrap;

import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoApplication {

    @Autowired
    private RuntimeService runtimeService;

    @RequestMapping(value="/start-my-process", method= RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public void startProcessInstance() {
        runtimeService.startProcessInstanceByKey("testProcess");
        //System.out.println("We have now" + runtimeService.createNativeProcessInstanceQuery().count() + " process instances !");
    }

}
