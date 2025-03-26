package com.corneliadavis.temporal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.corneliadavis.temporal.shared.MyWorkflow;
import com.corneliadavis.temporal.shared.Shared;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class RESTController {

    private static final Logger logger = LoggerFactory.getLogger(RESTController.class);

    @RequestMapping(method = RequestMethod.POST, value = "/launchworkflow")
    public String launchWorkflow(@RequestBody String name, HttpServletResponse response) {

        // A WorkflowServiceStubs communicates with the Temporal front-end service.
        WorkflowServiceStubs serviceStub = WorkflowServiceStubs.newLocalServiceStubs();

        // A WorkflowClient wraps the stub.
        // It can be used to start, signal, query, cancel, and terminate Workflows.
        WorkflowClient client = WorkflowClient.newInstance(serviceStub);

        // Workflow options configure Workflow stubs.
        // A WorkflowId prevents duplicate instances, which are removed.
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(Shared.TASK_QUEUE)
                .setWorkflowId("say-hello")
                .build();

        // WorkflowStubs enable calls to methods as if the Workflow object is local
        // but actually perform a gRPC call to the Temporal Service.
        MyWorkflow workflow = client.newWorkflowStub(MyWorkflow.class, options);


        logger.info("invoking workflow to greet " + name);
        String greeting = workflow.sayHello(name);

        return greeting;

    }

}
