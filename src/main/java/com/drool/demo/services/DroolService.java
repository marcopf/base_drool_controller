package com.drool.demo.services;

import com.drool.demo.object.Order;
import com.drool.demo.object.PriceCompare;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DroolService {
    PriceCompare priceCompare;

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

    public Integer calculateDiscount(Order order){
        KieSession kieSession = kieContainer().newKieSession();
        kieSession.insert(order);
        kieSession.insert(priceCompare);
        kieSession.fireAllRules();
        kieSession.dispose();
        return 0;
    }

    public DroolService(KieContainer kieContainer, PriceCompare priceCompare) {
        this.priceCompare = priceCompare;
    }
}
