package com.ftn.sbnz.service.tests;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.sbnz.model.events.TransactionEvent;



public class CEPConfigTest {

    @Test
    public void test() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer(); 
        KieSession ksession = kContainer.newKieSession("cepKsession");
        SessionPseudoClock clock = ksession.getSessionClock();
        TransactionEvent t1 = new TransactionEvent(1L, 850.00, "Samsung");
        TransactionEvent t2 = new TransactionEvent(1L, 880.00, "Samsung");
        TransactionEvent t3 = new TransactionEvent(1L, 890.00, "Samsung");
        TransactionEvent t4 = new TransactionEvent(1L, 890.00, "Samsung");
        t1.setExecutionTime(new Date(clock.getCurrentTime()));
        t2.setExecutionTime(new Date(clock.getCurrentTime()));
        t3.setExecutionTime(new Date(clock.getCurrentTime()));
   
        ksession.insert(t1);
        ksession.insert(t2);
        ksession.insert(t3);
        ksession.fireAllRules();

        clock.advanceTime(10, TimeUnit.MINUTES);
        t4.setExecutionTime(new Date(clock.getCurrentTime()));
        t4.setPhone("Tablet");
        ksession.insert(t4);
        ksession.fireAllRules();
        //clock.advanceTime(2, TimeUnit.HOURS);
      /**
       * Ukoliko je isti kupac kupio bar 3 
       * nova Samsung telefona u posljednjih 5 
       * minuta čija je cena veća od 800e, 
       * kreiraj kupon za 10% popusta
       * na sledeću kupovinu tableta. Ukoliko kupi tablet, na cenu
            se primenjuje kupon.
       */
    }
}
