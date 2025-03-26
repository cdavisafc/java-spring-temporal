package com.corneliadavis.temporal.app;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface MyActivities {

    @ActivityMethod
    String getGreeting();

}
