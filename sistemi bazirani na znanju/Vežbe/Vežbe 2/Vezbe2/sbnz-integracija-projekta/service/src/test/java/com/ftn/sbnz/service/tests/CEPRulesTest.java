package com.ftn.sbnz.service.tests;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.drools.core.event.DebugAgendaEventListener;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.sbnz.model.Flight;
import com.ftn.sbnz.model.TransactionEvent;

public class CEPRulesTest {

    @Test
    public void testCEPRules() {
    	KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();
        KieSession ksession = kc.newKieSession("cepKsession");
        Long customerId = 1L;
        ksession.insert(new TransactionEvent(customerId, 10.00));
        ksession.insert(new TransactionEvent(customerId, 10.00));
        ksession.insert(new TransactionEvent(customerId, 14.00));
        ksession.insert(new TransactionEvent(customerId, 10.50));
        ksession.insert(new TransactionEvent(customerId, 10.99));
        ksession.insert(new TransactionEvent(customerId, 9.00));
        ksession.insert(new TransactionEvent(customerId, 11.00));
        ksession.insert(new TransactionEvent(customerId, 15.00));
        ksession.insert(new TransactionEvent(customerId, 18.00));
        ksession.insert(new TransactionEvent(customerId, 201.00));
        long ruleFireCount = ksession.fireAllRules();
        System.out.println(ruleFireCount);
        ksession.insert(new TransactionEvent(customerId, 202.00));
        ruleFireCount = ksession.fireAllRules();
        System.out.println(ruleFireCount);
    }
    @Test
    public void TestFlights(){
        KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();
        KieSession ksession = kc.newKieSession("flightsSession");

        Flight f1 = new Flight(1L);
        Flight f2 = new Flight(2L);
        f1.setBoard(new Date());
        ksession.insert(f1);
        ksession.insert(f2);
        System.out.println(f1.isRedirected());

        long ruleFireCount = ksession.fireAllRules();
        System.out.println(ruleFireCount);
        System.out.println(f1.isRedirected());
        System.out.println(f2.isRedirected());


    }
    @Test
    public void testCustomersAndTransactions(){
        KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();
        KieSession ksession = kc.newKieSession("transSession");
        SessionPseudoClock clock = ksession.getSessionClock();

        TransactionEvent t1 = new TransactionEvent(1L, 40.00);
        TransactionEvent t2 = new TransactionEvent(1L, 40.00);
        TransactionEvent t3 = new TransactionEvent(1L, 50.00);

        t1.setExecutionTime(new Date(clock.getCurrentTime()));
        clock.advanceTime(10, TimeUnit.SECONDS);
        t2.setExecutionTime(new Date(clock.getCurrentTime()));
        ksession.insert(t1);
        ksession.insert(t2);
        System.out.println(t1.isDuplicate());
        System.out.println(t2.isDuplicate());
        long ruleFireCount = ksession.fireAllRules();
        System.out.println(ruleFireCount);
        System.out.println(t1.isDuplicate());
        System.out.println(t2.isDuplicate());


    }
}
