# java-spring-temporal

Setting up a Temporal application in java & spring boot means a lot of boilerplate.
There is the primary application which consists of worker, workflow and activity 
implementations. Then there is also the workflow launcher (though this is optional,
as you can launch workflows in other ways - cli or GUI). 

I have created the project so that I can just clone it any time I want to build up 
a new app. 

It has the following structure:
- At the root, it is a project that has three modules:
 - application - where the main Temporal artifacts are - workflow, activity and workers
 - controller - implemens a REST controller that when invoked launches a workflow
 - shared - holds the Workflow interface and other shared resources (i.e. Task queue name)

 The pom at the root level points to the `spring-boot-starter-parent` as its parent bringing
 all sorts of goodness to each of the project's modules (building fat jars by default, for 
 example).

Please feel free to use this template to start your Temporal apps. If you find this helpful
maybe just give it a star - no need to attribute in your projects; it's just boilerplate.