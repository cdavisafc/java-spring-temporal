package com.corneliadavis.temporal.shared;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface MyWorkflow {

    // This workflow will be invoked via the ConnectionsPostsController
    @WorkflowMethod
    String sayHello(String name);
}