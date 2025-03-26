package com.corneliadavis.temporal.app;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import com.corneliadavis.temporal.shared.MyWorkflow;

public class MyWorkflowImpl implements MyWorkflow {

    private static final Logger logger = LoggerFactory.getLogger(MyWorkflowImpl.class);

    // RetryOptions specify how to automatically handle retries when Activities fail
    private final RetryOptions retryoptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1)) // Wait 1 second before first retry
            .setMaximumInterval(Duration.ofSeconds(20)) // Do not exceed 20 seconds between retries
            .setBackoffCoefficient(2) // Wait 1 second, then 2, then 4, etc
            .setMaximumAttempts(7) // Fail after 5000 attempts
            .build();

    // ActivityOptions specify the limits on how long an Activity can execute before
    // being interrupted by the Orchestration service
    private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
            .setRetryOptions(retryoptions) // Apply the RetryOptions defined above
            .setStartToCloseTimeout(Duration.ofSeconds(2)) // Max execution time for single Activity
            .setScheduleToCloseTimeout(Duration.ofSeconds(500)) // Entire duration from scheduling to completion
                                                                // including queue time
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