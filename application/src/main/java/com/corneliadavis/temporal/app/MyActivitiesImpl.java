package com.corneliadavis.temporal.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MyActivitiesImpl implements MyActivities {

    private static final Logger logger = LoggerFactory.getLogger(MyActivitiesImpl.class);

    @Autowired
    public MyActivitiesImpl() {
    }

    @Override
    public String getGreeting() {

        logger.info("Figuring out the right greeting ");
        String greeting = "Hello";

        return greeting;
    }

}
