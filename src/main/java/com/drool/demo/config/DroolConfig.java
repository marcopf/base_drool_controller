package com.drool.demo.config;

import jakarta.annotation.PostConstruct;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class DroolConfig {

//    @PostConstruct
//    public void setup() {
//        KieServices kieServices = KieServices.Factory.get();
//
//        // Load rules from the file system dynamically
//        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//
//        // If the rule is in a custom folder (like /custom-drools)
//        File ruleFile = new File("rules/testDrool.drl");
//        kieFileSystem.write(kieServices.getResources().newFileSystemResource(ruleFile));
//
//        kieServices.newKieBuilder(kieFileSystem).buildAll();
//        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
//
//        // Use KieScanner to monitor the changes in the rule file and automatically reload them
//        KieScanner kieScanner = kieServices.newKieScanner(kieContainer);
//        kieScanner.start(10000L); // Poll every 10 seconds for changes
//    }

    @Bean
    public KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

        // If the rule is in a custom folder (like /custom-drools)
        File ruleFile = new File("rules/testDrool.drl");
        kieFileSystem.write(kieServices.getResources().newFileSystemResource(ruleFile));
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();

        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(DroolConfig.class); // Creates a logger for this configuration class
    }
}
