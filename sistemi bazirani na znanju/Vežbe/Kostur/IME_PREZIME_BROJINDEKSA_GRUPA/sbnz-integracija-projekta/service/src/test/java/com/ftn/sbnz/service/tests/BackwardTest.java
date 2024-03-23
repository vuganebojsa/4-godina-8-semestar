package com.ftn.sbnz.service.tests;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.sbnz.model.models.Phone;


public class BackwardTest {
    
    @Test
    public void TestBackward(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer(); 
        KieSession ksession = kContainer.newKieSession("bwKsession");

        ksession.insert(new Phone("S Serija", "Samsung"));
        ksession.insert(new Phone("A Serija", "Samsung"));

        ksession.insert(new Phone("Galaxy S10", "S Serija"));
        ksession.insert(new Phone("S10e", "Galaxy S10"));
        ksession.insert(new Phone("S10+", "Galaxy S10"));
        ksession.insert(new Phone("S10 Lite", "Galaxy S10"));

        ksession.insert(new Phone("Galaxy S20", "S Serija"));
        ksession.insert(new Phone("S20", "Galaxy S20"));
        ksession.insert(new Phone("S20+", "Galaxy S20"));
        ksession.insert(new Phone("S20 FE", "Galaxy S20"));

        ksession.insert(new Phone("Galaxy Ax0", "A Serija"));
        ksession.insert(new Phone("A10", "Galaxy Ax0"));
        ksession.insert(new Phone("A20", "Galaxy Ax0"));

        ksession.insert(new Phone("Galaxy Ax4", "A Serija"));
        ksession.insert(new Phone("A04e", "Galaxy Ax4"));
        ksession.insert(new Phone("A23", "Galary Ax4"));


        ksession.fireAllRules();
      
    }
}
