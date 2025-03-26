package com.corneliadavis.temporal.app;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corneliadavis.temporal.shared.MyWorkflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

public class MyWorkflowImpl implements MyWorkflow {

    private static final Logger logger = LoggerFactory.getLogger(MyWorkflowImpl.class);

    // ActivityOptions specify the limits on how long an Activity can execute before
    // being interrupted by the Orchestration service. At least one of StartToClose or 
    // ScheduleToClose must be set
    private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(2)) // Max execution time for single Activity
            .build();

    // ActivityStubs enable calls to methods as if the Activity object is local but
    // actually perform an RPC invocation
    private final MyActivities activityStub = Workflow
            .newActivityStub(MyActivities.class, defaultActivityOptions, null);

    public String sayHello(String name) {

        logger.info("going to greet " + name + " appropriately");

        String greeting = activityStub.getGreeting();

        return greeting + " " + name;
    }

}