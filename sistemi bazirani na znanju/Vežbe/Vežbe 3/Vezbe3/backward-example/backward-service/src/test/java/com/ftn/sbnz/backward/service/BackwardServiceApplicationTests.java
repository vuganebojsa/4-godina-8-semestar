package com.ftn.sbnz.backward.service;

import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.sbnz.backward.model.models.Location;

class BackwardServiceApplicationTests {

	@Test
	void contextLoads() {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer(); 
        KieSession ksession = kContainer.newKieSession();
		ksession.insert(new Location("S", "Sa"));
		ksession.insert(new Location("A", "Sa"));
		ksession.insert(new Location("B", "Sa"));
		ksession.insert(new Location("G S", "S"));
		ksession.insert(new Location("G B", "S"));
		ksession.insert(new Location("A B", "A"));
		ksession.insert(new Location("A a a", "A B"));
		ksession.insert(new Location("asfsa", "G B"));

		ksession.fireAllRules();
	}

}
